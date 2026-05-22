package com.looqbox.pokeapi.service

import com.looqbox.pokeapi.dto.pokeapi.PaginatedResponse
import com.looqbox.pokeapi.model.pokeapi.PokemonData
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientException
import org.springframework.web.reactive.function.client.awaitBody

@Service
class PokeapiService (
    val pokeClient: WebClient
) {
    suspend fun getAllPokemon(): PaginatedResponse<PokemonData>?  {
        return try {
            pokeClient
                .get()
                .uri{ uriBuilder ->
                    uriBuilder
                        .path("/pokemon")
                        .queryParam("limit", "3000")
                        .build()
                }
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<PaginatedResponse<PokemonData>>()
        } catch (e: WebClientException){
            null
        }
    }
}