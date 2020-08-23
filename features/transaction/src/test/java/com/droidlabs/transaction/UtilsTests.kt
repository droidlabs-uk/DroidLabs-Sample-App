package com.droidlabs.transaction

import com.droidlabs.transaction.ui.transactionsFragmentRx.convertSatoshiToBTC
import com.droidlabs.transaction.ui.transactionsFragmentRx.isNegative
import com.droidlabs.transaction.ui.transactionsFragmentRx.prettyDateString
import org.junit.Assert.*
import org.junit.Test

class UtilsTests {

    @Test
    fun `verifyPrettyString() returns correct date string`() {
        //GIVEN
        val milliseconds = 0L
        val expectedDateString = "01/01/1970\n00:00:00 UTC"

        //WHEN
        val prettyDateString = prettyDateString(milliseconds)

        //THEN
        assertEquals(expectedDateString, prettyDateString)
    }

    @Test
    fun `test convertSatoshiToBTC conversion correct`(){
        //GIVEN
        val satoshi = 100000000
        val expectedBTC = "1.00000000"

        //WHEN
        val btc = convertSatoshiToBTC(satoshi)

        //THEN
        assertEquals(expectedBTC, btc.toPlainString())
    }

    @Test
    fun `isNegative() correct`(){
        assertFalse(10.isNegative())
        assertFalse(0.isNegative())

        assertTrue((-1).isNegative())
    }
}