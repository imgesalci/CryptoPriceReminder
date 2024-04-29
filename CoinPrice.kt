package com.example.cryptocurrencypricereminder

import com.google.gson.annotations.SerializedName


data class CoinPrice(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: Double
)
