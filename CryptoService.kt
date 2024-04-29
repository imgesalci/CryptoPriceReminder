package com.example.cryptocurrencypricereminder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CryptoService {
    @GET("/v1/cryptocurrency/listings/latest")
    fun getCoinPrices(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("start") start: Int,
        @Query("limit") limit: Int,
        @Query("convert") convert: String
    ): Call<CoinPriceResponse>
}