package com.looqbox.pokeapi.dto

import com.looqbox.pokeapi.model.PokemonHighlight

data class HighlightPokemonResponse(
    val result: List<PokemonHighlight>
)
