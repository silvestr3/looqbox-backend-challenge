package com.looqbox.pokeapi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClient(
    @Value($$"${pokemon.baseUrl}")
    val pokeapiUrl: String
) {
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    fun restClient(builder: WebClient.Builder): WebClient {
        return builder
            .baseUrl(this.pokeapiUrl)
            .build()
    }

}