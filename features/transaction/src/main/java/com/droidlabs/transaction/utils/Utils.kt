package com.droidlabs.transaction.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

fun prettyDateString(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss z", Locale.UK).apply { timeZone = TimeZone.getTimeZone("UTC") }
    return formatter.format(Date(milliseconds))
}

fun convertSatoshiToBTC(satoshi: Int): BigDecimal =
    BigDecimal(satoshi.toDouble() / 100000000).setScale(8, RoundingMode.HALF_EVEN)

fun Int.isNegative() = this < 0
