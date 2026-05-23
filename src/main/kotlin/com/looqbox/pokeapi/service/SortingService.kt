package com.looqbox.pokeapi.service

import org.springframework.stereotype.Service

enum class SortingMode {
    ALPHABETICAL,
    LENGTH
}

/*
* Quick Sort
*
* Input:
* - lista desordenada
* - modo (alphabetical/length)
* - lambda q retorna string pra ser avaliada
*
* Output:
* - lista ordenada
* */

@Service
class SortingService {
    fun <T> quickSort(
        list: List<T>,
        mode: SortingMode,
        accessor: (T) -> String
    ): List<T> {
        if (list.isEmpty() || list.count() == 1) return list

        val pivot = list.random()

        val left = mutableListOf<T>()
        val equal = mutableListOf<T>()
        val right = mutableListOf<T>()

        list.forEach {
            when {
                compare(accessor(it), accessor(pivot), mode) > 0 -> right.add(it)
                compare(accessor(it), accessor(pivot), mode) < 0 -> left.add(it)
                compare(accessor(it), accessor(pivot), mode) == 0 -> equal.add(it)
            }
        }

        val sortedLeft = quickSort(left, mode, accessor)
        val sortedRight = quickSort(right, mode, accessor)

        return sortedLeft + equal + sortedRight
    }

    fun compare(a: String, b: String, mode: SortingMode): Int {
        return when (mode) {
            SortingMode.ALPHABETICAL -> a.compareTo(b)
            SortingMode.LENGTH -> a.length - b.length
        }
    }
}