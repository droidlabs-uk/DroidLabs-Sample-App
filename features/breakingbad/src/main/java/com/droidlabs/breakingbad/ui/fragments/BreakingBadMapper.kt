package com.droidlabs.breakingbad.ui.fragments

import com.droidlabs.breakingbad.ui.adapter.BreakingBadItem
import com.droidlabs.core.network.breakingbad.datamodel.BreakingBadCharacter

data class BreakingBadCharacterUIM(val char_id : Int, val name: String, val imageUrl: String)

fun List<BreakingBadCharacter>?.toUIM() = this?.map { it.toUIM() }

fun BreakingBadCharacter.toUIM() = BreakingBadItem.CharacterItem(
    BreakingBadCharacterUIM(
        char_id = char_id,
        name = name,
        imageUrl = img
    )
)

fun List<BreakingBadCharacter>.toItems(): List<BreakingBadItem> = map { it.toUIM() }