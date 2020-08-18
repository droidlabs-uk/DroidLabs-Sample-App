package com.blockchain.breakingbad.ui.adapter.delegates

import android.widget.ImageView
import android.widget.TextView
import com.blockchain.breakingbad.R
import com.blockchain.breakingbad.ui.adapter.BreakingBadActions
import com.blockchain.breakingbad.ui.adapter.BreakingBadItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun getCharacters(clicks: (BreakingBadActions) -> Unit) =
    adapterDelegate<BreakingBadItem.Character, BreakingBadItem>(R.layout.bb_character_feature) {

        val characterName: TextView = findViewById(R.id.bb_character_feature_name)
        val characterBackground: ImageView = findViewById(R.id.bb_character_feature_background)

        bind {
//        characterName.setText(R.string.cuvvaFeature)
            itemView.setOnClickListener {
//            clicks(MainActions.CuvvaFeatureClicked)
            }
        }
    }
