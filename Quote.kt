package com.example.cryptocurrencypricereminder

import com.google.gson.annotations.SerializedName

class Quote {
    @SerializedName("USD")
    val usdQuote: QuoteDetail? = null

    @SerializedName("BTC")
    private val btcQuote: QuoteDetail? = null // Getters and setters
}