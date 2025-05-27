package com.example.igrerijeci.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.igrerijeci.model.WordItem
import com.example.igrerijeci.data.sveGrupe

class ConnectionsViewModel : ViewModel() {

    private val _words = mutableStateListOf<WordItem>()
    val words: List<WordItem> get() = _words

    private val selectedWords = mutableStateListOf<WordItem>()

    var brojPokusaja by mutableStateOf(0)
        private set

    var igraGotova by mutableStateOf(false)
        private set

    init {
        novaIgra()
    }

    val brojPronadjenihGrupa: Int
        get() = words.map { it.group }
            .distinct()
            .count { grupa -> words.count { it.group == grupa && it.isFound } == 4 }

    fun toggleSelection(word: WordItem) {
        if (word.isFound) return

        if (selectedWords.any { it.text == word.text }) {
            selectedWords.removeIf { it.text == word.text }
        } else if (selectedWords.size < 4) {
            selectedWords.add(word)
        }

        _words.replaceAll {
            it.copy(isSelected = selectedWords.any { w -> w.text == it.text })
        }

        if (selectedWords.size == 4) checkSelection()
    }


    private fun checkSelection() {
        val group = selectedWords.first().group
        val isCorrect = selectedWords.all { it.group == group }

        if (isCorrect) {
            _words.replaceAll {
                if (selectedWords.any { s -> s.text == it.text })
                    it.copy(isFound = true, isSelected = false)
                else it
            }
        } else {
            _words.replaceAll { it.copy(isSelected = false) }
            brojPokusaja++
        }

        selectedWords.clear()

        if (brojPronadjenihGrupa == 4 || brojPokusaja >= 4) {
            igraGotova = true
        }
    }

    fun restartGame() {
        brojPokusaja = 0
        igraGotova = false
        novaIgra()
    }

    private fun novaIgra() {
        selectedWords.clear()
        _words.clear()

        val izabraneGrupe = sveGrupe.shuffled().take(4)

        val normalizovane = izabraneGrupe.mapIndexed { index, grupa ->
            grupa.map { it.copy(group = index, isSelected = false, isFound = false) }
        }.flatten().shuffled()

        _words.addAll(normalizovane)
    }
}
