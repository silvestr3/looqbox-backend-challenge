package com.looqbox.pokeapi.service

import org.springframework.stereotype.Service

@Service
class HighlightService {
    fun highlightSubstring(name: String, query: String): String {
        return name.replace(query, "<pre>$query</pre>")
    }
}