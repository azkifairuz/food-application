package com.javfairuz.foodapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.models.Produk

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private val produkList = ArrayList<Produk>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_product,parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduk = produkList[position]

        Glide.with(holder.itemView.context)
            .load(currentProduk.image)
            .apply(RequestOptions().override(107,107))
            .into(holder.imgProduk)
        holder.produkName.text = currentProduk.produk
        holder.hargaProduk.text = currentProduk.harga
    }

    override fun getItemCount() = produkList.size

    fun addProdukList(produkList : List<Produk>){
        this.produkList.clear()
        this.produkList.addAll(produkList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

        val produkName : TextView = itemView.findViewById(R.id.namaProduk)
        val hargaProduk : TextView = itemView.findViewById(R.id.hargaProduk)
        val imgProduk : ImageView = itemView.findViewById(R.id.imgProduk)
    }

}