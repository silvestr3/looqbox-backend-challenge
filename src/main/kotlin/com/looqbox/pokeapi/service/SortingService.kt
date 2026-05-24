package com.looqbox.pokeapi.service

import com.looqbox.pokeapi.model.SortingMode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

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
    suspend fun <T> quickSort(
        list: List<T>,
        mode: SortingMode,
        accessor: (T) -> String
    ): List<T> {
        if (list.isEmpty() || list.size == 1) return list

        val pivot = list.random()

        val left = mutableListOf<T>()
        val equal = mutableListOf<T>()
        val right = mutableListOf<T>()

        list.forEach {
            val comparison = compare(accessor(it), accessor(pivot), mode)
            when  {
                comparison > 0 -> right.add(it)
                comparison < 0 -> left.add(it)
                else -> equal.add(it)
            }
        }

        return if (list.size > 1000) {
            coroutineScope {
                val sortedLeft = async { quickSort(left, mode, accessor) }
                val sortedRight = async { quickSort(right, mode, accessor) }

                sortedLeft.await() + equal + sortedRight.await()
            }
        } else {
            quickSort(left, mode, accessor) + equal + quickSort(right, mode, accessor)
        }
    }

    fun compare(a: String, b: String, mode: SortingMode): Int {
        return when (mode) {
            SortingMode.ALPHABETICAL -> a.compareTo(b)
            SortingMode.LENGTH -> a.length - b.length
        }
    }
}