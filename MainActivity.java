package com.example.cryptocurrencypricereminder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CoinAdapter coinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pro-api.coinmarketcap.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CryptoService cryptoService = retrofit.create(CryptoService.class);

        Call<CoinPriceResponse> call = cryptoService.getCoinPrices("a6dd73c7-8e6b-4a12-8c27-019c624f0f1f", 1, 5, "USD"); // Fetch first 10 coins in USD
        call.enqueue(new Callback<CoinPriceResponse>() {
            @Override
            public void onResponse(Call<CoinPriceResponse> call, Response<CoinPriceResponse> response) {
                if (response.isSuccessful()) {
                    CoinPriceResponse coinPriceResponse = response.body();

                    if (coinPriceResponse != null) {
                        List<Coin> coins = coinPriceResponse.getData();
                        coinAdapter = new CoinAdapter(coins);
                        recyclerView.setAdapter(coinAdapter);

                        StringBuilder stringBuilder = new StringBuilder("Coin Prices: ");
                        for (Coin coinPrice : coins) {
                            stringBuilder.append(coinPrice.toString()).append(", ");
                        }
                        String logMessage = stringBuilder.toString();
                        Log.d("API Response", logMessage);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CoinPriceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}