package com.looqbox.pokeapi.dto.error

import org.springframework.http.HttpStatus

data class GeneralError(
    val code : Int,
    val message : String
)
