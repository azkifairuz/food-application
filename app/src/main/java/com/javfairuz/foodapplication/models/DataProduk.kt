package com.javfairuz.foodapplication.models

import com.javfairuz.foodapplication.R

object DataProduk {
        private val produkName = arrayOf(
            "Ayam Goreng Bumbu",
            "Ayam Malbi",
            "Tumis Kangkung",
            "Tongseng Kabing",
            "Mujair Goreng"
       )

        private val hargaProduk = arrayOf(

            "8000",
            "10000",
            "6000",
            "12000",
            "8000"
        )

    private val imgProduk = arrayOf(
        R.drawable.photo_1,
        R.drawable.catering_indonesia_paket_catering_nasi_kotak_2_1,
        R.drawable.photo_1,
        R.drawable.catering_indonesia_paket_catering_nasi_kotak_2_1,
        R.drawable.photo_1

    )

    val listProduk: ArrayList<Produk>get() {
        val list = arrayListOf<Produk>()
        for(position in  produkName.indices){
            val produk = Produk()
            produk.produk = produkName[position]
            produk.harga = hargaProduk[position]
            produk.image = imgProduk[position]
            list.add(produk)
        }

        return list
    }

}