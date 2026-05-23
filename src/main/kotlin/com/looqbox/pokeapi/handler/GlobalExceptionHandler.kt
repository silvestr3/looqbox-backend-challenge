package com.looqbox.pokeapi.handler

import com.looqbox.pokeapi.dto.error.GeneralError
import com.looqbox.pokeapi.model.exception.ApiException
import com.looqbox.pokeapi.service.SortingMode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e : ApiException): ResponseEntity<GeneralError> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(GeneralError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message ?: "Unknown error occurred"))

    }

    @ExceptionHandler(IllegalArgumentException::class)
        fun handleArgumentException(e: IllegalArgumentException): ResponseEntity<GeneralError> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(GeneralError(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid sorting mode. Available modes are ${SortingMode.entries.map { it }}")
            )
    }
}