package com.looqbox.pokeapi.controller

import com.looqbox.pokeapi.dto.HighlightPokemonResponse
import com.looqbox.pokeapi.dto.SearchPokemonResponse
import com.looqbox.pokeapi.model.SortingMode
import com.looqbox.pokeapi.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon")
class PokemonController(val pokemonService: PokemonService) {

    @GetMapping
    suspend fun searchPokemon(
        @RequestParam(required = false) query: String = "",
        @RequestParam(required = false) sort: SortingMode = SortingMode.ALPHABETICAL
    ): SearchPokemonResponse = this.pokemonService.searchPokemon(query, sort)

    @GetMapping("/highlight")
    suspend fun highlightPokemon(
        @RequestParam(required = false) query: String = "",
        @RequestParam(required = false) sort: SortingMode = SortingMode.ALPHABETICAL
    ): HighlightPokemonResponse = this.pokemonService.highlightPokemon(query, sort)
}