package com.looqbox.pokeapi.controller

import com.looqbox.pokeapi.dto.SearchPokemonResponse
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
        @RequestParam query: String?
    ): SearchPokemonResponse = this.pokemonService.searchPokemon(query)
}