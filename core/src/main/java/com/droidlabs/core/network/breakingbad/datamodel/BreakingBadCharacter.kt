package com.droidlabs.core.network.breakingbad.datamodel

data class BreakingBadCharacter(
    val char_id : Int,
    val name : String,
    val birthday : String,
    val occupation : List<String>,
    val img : String,
    val status : String,
    val nickname : String,
    val appearance : List<Int>,
    val portrayed : String,
    val category : String,
    val better_call_saul_appearance : List<String>
)