package com.looqbox.pokeapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClient {
    private companion object {
        const val pokeapiUrl = "https://pokeapi.co/api/v2/"
    }

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    fun restClient(builder: WebClient.Builder): WebClient {
        return builder
            .baseUrl(pokeapiUrl)
            .build()
    }

}