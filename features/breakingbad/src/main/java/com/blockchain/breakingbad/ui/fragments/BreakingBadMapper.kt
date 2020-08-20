package com.blockchain.breakingbad.ui.fragments

import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import com.blockchain.core.network.breakingbad.datamodel.BreakingBadCharacter

data class BreakingBadCharacterUIM(val name: String, val imageUrl: String)

fun List<BreakingBadCharacter>?.toUIM() = this?.map { it.toUIM() }

fun BreakingBadCharacter.toUIM() = BreakingBadItem.CharacterItem(
    BreakingBadCharacterUIM(
        name = name,
        imageUrl = img
    )
)