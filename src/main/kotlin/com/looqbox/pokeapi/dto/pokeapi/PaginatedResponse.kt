package com.looqbox.pokeapi.dto.pokeapi

import java.net.URI

data class PaginatedResponse<T>(
    val count: Int,
    val next: URI?,
    val previous: URI?,
    val results: List<T>,
)