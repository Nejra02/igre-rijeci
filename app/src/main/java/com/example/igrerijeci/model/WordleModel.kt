package com.example.igrerijeci.model

enum class SlovoState {
    Tacno,
    PogresnoMjesto,
    Netacno
}

data class Polje(
    var slovo: String = "",
    var state: SlovoState? = null
)
