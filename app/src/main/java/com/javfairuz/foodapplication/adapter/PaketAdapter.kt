package com.javfairuz.foodapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.models.Produk
import com.javfairuz.foodapplication.ui.DetailActivity

class PaketAdapter(private val context: Context?) :RecyclerView.Adapter<PaketAdapter.MyViewHolder>() {
    private val produkList = ArrayList<Produk>()
    class MyViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

        val produkName : TextView = itemView.findViewById(R.id.namaProduk)
        val hargaProduk : TextView = itemView.findViewById(R.id.hargaProduk)
        val imgProduk : ImageView = itemView.findViewById(R.id.imgProduk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_product,parent,false
        )
        return PaketAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduk = produkList[position]

        Glide.with(holder.itemView.context)
            .load(currentProduk.image)
            .apply(RequestOptions().override(107,107))
            .into(holder.imgProduk)
        holder.itemView.setOnClickListener{
            val goToDetail = Intent(context, DetailActivity::class.java)
            goToDetail.putExtra(DetailActivity.EXTRA_NAME,currentProduk.produk)
            goToDetail.putExtra(DetailActivity.EXTRA_HARGA,currentProduk.harga)
            goToDetail.putExtra(DetailActivity.EXTRA_IMG,currentProduk.image)

            context?.startActivity(goToDetail)
        }
        holder.produkName.text = currentProduk.produk
        holder.hargaProduk.text = currentProduk.harga.toString()
    }

    fun addProdukList(produkList : List<Produk>){
        this.produkList.clear()
        this.produkList.addAll(produkList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = produkList.size

}