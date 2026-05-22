package com.looqbox.pokeapi.service

import org.springframework.stereotype.Service

@Service
class HighlightService {
    fun highlightSubstring(name: String, query: String): String {
        if (query.isBlank()) return name
        return name.replaceFirst(query, "<pre>$query</pre>")
    }
}