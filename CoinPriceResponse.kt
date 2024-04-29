package com.example.cryptocurrencypricereminder

import com.google.gson.annotations.SerializedName


class CoinPriceResponse {
    @SerializedName("data") val data: List<Coin>? = null
}
