package com.looqbox.pokeapi.service

import com.looqbox.pokeapi.dto.HighlightPokemonResponse
import com.looqbox.pokeapi.dto.SearchPokemonResponse
import com.looqbox.pokeapi.model.PokemonHighlight
import com.looqbox.pokeapi.model.exception.ApiException
import com.looqbox.pokeapi.model.pokeapi.PokemonData
import org.springframework.stereotype.Service

@Service
class PokemonService(
    val pokeApiService: PokeapiService,
    val highlightService: HighlightService,
    val sortingService: SortingService
) {
    suspend fun searchPokemon(
        query: String,
        sort: SortingMode
    ): SearchPokemonResponse {
        val pokemonListApi =  this.pokeApiService.getAllPokemon()
            ?:throw ApiException("Failed to fetch pokemon list")

        val result = pokemonListApi
            .results
            .filterAndTransform(query) { it.name }

        val sortedPokemonList = this.sortingService.quickSort(
            result,
            sort,
        ) { it }

        return SearchPokemonResponse(result = sortedPokemonList)
    }

    suspend fun highlightPokemon(
        query: String,
        sort: SortingMode
    ): HighlightPokemonResponse {
        val pokemonListApi = this.pokeApiService.getAllPokemon()
            ?:throw ApiException("Failed to fetch pokemon list")

        val result = pokemonListApi
            .results
            .filterAndTransform(query) {
                PokemonHighlight(it.name, this.highlightService.highlightSubstring(it.name, query))
            }

        val sortedPokemonList = this.sortingService.quickSort(
            result,
            sort,
        ) { it.name }

        return HighlightPokemonResponse(result = sortedPokemonList)
    }

    fun <T> List<PokemonData>.filterAndTransform(substring: String, transform: (PokemonData) -> T): List<T> {
        val result = mutableListOf<T>()

        for (item in this) {
            if (item.name.contains(substring, ignoreCase = true)) {
                result.add(transform(item))
            }
        }

        return result
    }
}