package com.example.igrerijeci.viewmodel

import androidx.lifecycle.ViewModel
import com.example.igrerijeci.model.Polje
import com.example.igrerijeci.model.SlovoState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.igrerijeci.data.wordleRijeci



class WordleViewModel : ViewModel() {
    var targetWord = wordleRijeci.random()
    var igraGotova by mutableStateOf(false)
        private set
    var pogodjena by mutableStateOf(false)
        private set
    val targetWordPublic get() = targetWord

    var board by mutableStateOf(
        List(6) {
            MutableList(5) { Polje() }
        }
    )
        private set

    var red = 0
        private set

    var rijecNijeValidna by mutableStateOf(false)
        private set


    fun updatePolje(i: Int, j: Int, novo: String) {
        if (i != red || novo.length > 1 || !novo.all { it.isLetter() }) return

        val novoSlovo = novo.uppercase()
        board = board.toMutableList().also { novaLista ->
            val redLista = novaLista[i].toMutableList()
            redLista[j] = redLista[j].copy(slovo = novoSlovo)
            novaLista[i] = redLista
        }
    }

    fun submitRijec() {
        if (red >= 6) return

        val guess = board[red].joinToString("") { it.slovo }

        if (guess.length != 5) return

        if (!wordleRijeci.contains(guess)) {
            rijecNijeValidna = true
            return
        }

        val target = targetWord.toCharArray()
        val guessChars = guess.toCharArray()
        val rezultat = MutableList(5) { SlovoState.Netacno }
        val targetUsed = BooleanArray(5) { false }

        // tacna mjesta
        for (i in 0..4) {
            if (guessChars[i] == target[i]) {
                rezultat[i] = SlovoState.Tacno
                targetUsed[i] = true
            }
        }

        // netacna mjesta
        for (i in 0..4) {
            if (rezultat[i] == SlovoState.Tacno) continue
            for (j in 0..4) {
                if (!targetUsed[j] && guessChars[i] == target[j]) {
                    rezultat[i] = SlovoState.PogresnoMjesto
                    targetUsed[j] = true
                    break
                }
            }
        }

        // postavljanje boja
        board = board.toMutableList().also { nova ->
            val novaRed = board[red].mapIndexed { i, polje ->
                polje.copy(state = rezultat[i])
            }.toMutableList()
            nova[red] = novaRed
        }

        val sveTacno = board[red].all { it.state == SlovoState.Tacno }

        if (sveTacno || red == 5) {
            igraGotova = true
            pogodjena = sveTacno
        }

        red++
    }


    fun restartGame() {
        targetWord = wordleRijeci.random()
        board = List(6) { MutableList(5) { Polje() } }
        red = 0
        igraGotova = false
        pogodjena = false
    }

    fun resetujValidaciju() {
        rijecNijeValidna = false
    }



}
