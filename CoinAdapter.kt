package com.example.cryptocurrencypricereminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinAdapter(private val coins: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        holder.bind(coin)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(coin: Coin) {
            nameTextView.text = coin.name
            symbolTextView.text = coin.symbol
            priceTextView.text = coin.quote?.usdQuote?.price.toString() // Assuming you want USD price
        }
    }
}
