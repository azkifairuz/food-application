package com.javfairuz.foodapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.models.CartStorage
import com.javfairuz.foodapplication.models.Produk
import com.javfairuz.foodapplication.ui.DetailActivity

class CartAdapter: RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private val produkList = ArrayList<CartStorage>()
    class MyViewHolder (itemView : View): RecyclerView.ViewHolder(itemView) {
        val produkName : TextView = itemView.findViewById(R.id.tvTitle)
        val hargaProduk : TextView = itemView.findViewById(R.id.hargaCart)
        val imgProduk : ImageView = itemView.findViewById(R.id.imageCart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_cart,parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduk = produkList[position]

        Glide.with(holder.itemView.context)
            .load(currentProduk.image)
            .apply(RequestOptions().override(93,82))
            .into(holder.imgProduk)

        holder.produkName.text = currentProduk.produk
        holder.hargaProduk.text = currentProduk.harga.toString()
    }

    fun addCartList(produkList : List<CartStorage>){
        this.produkList.clear()
        this.produkList.addAll(produkList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = produkList.size


}