package com.looqbox.pokeapi.service

import com.looqbox.pokeapi.dto.SearchPokemonResponse
import com.looqbox.pokeapi.exception.ApiException
import org.springframework.stereotype.Service

@Service
class PokemonService(
    val pokeApiService: PokeapiService
) {
    suspend fun searchPokemon(): SearchPokemonResponse {
        val pokemonListApi =  this.pokeApiService.getAllPokemon()
            ?:throw ApiException("Failed to fetch pokemon list")

        val result = pokemonListApi.results.map { it.name }

        return SearchPokemonResponse(result = result)
    }
}