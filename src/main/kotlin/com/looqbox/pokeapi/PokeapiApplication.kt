package com.looqbox.pokeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokeapiApplication

fun main(args: Array<String>) {
	runApplication<PokeapiApplication>(*args)
}
