package com.javfairuz.foodapplication.adapter

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.`interface`.Comunicator
import com.javfairuz.foodapplication.models.Produk
import com.javfairuz.foodapplication.ui.DetailActivity
import com.javfairuz.foodapplication.ui.fragment.HomeFragment


class HomeAdapter(private val context: Context?  ): RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private val produkList = ArrayList<Produk>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_product,parent,false
        )

        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduk = produkList[position]
        val dec = DecimalFormat("#,###.##")
        val harga = dec.format(currentProduk.harga)
        Glide.with(holder.itemView.context)
            .load(currentProduk.image)
            .apply(RequestOptions().override(107,107))
            .into(holder.imgProduk)
        holder.itemView.setOnClickListener{
            val goToDetail = Intent(context,DetailActivity::class.java)
            goToDetail.putExtra(DetailActivity.EXTRA_NAME,currentProduk.produk)
            goToDetail.putExtra(DetailActivity.EXTRA_HARGA,currentProduk.harga)
            goToDetail.putExtra(DetailActivity.EXTRA_IMG,currentProduk.image)

            context?.startActivity(goToDetail)
        }
        holder.produkName.text = currentProduk.produk
        holder.hargaProduk.text ="Rp " + harga.toString()
    }

    override fun getItemCount() = produkList.size
    //function tambah data
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