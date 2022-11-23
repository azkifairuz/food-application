package com.javfairuz.foodapplication.ui

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.ActivityDetailBinding
import com.javfairuz.foodapplication.databinding.ActivityMainBinding
import com.javfairuz.foodapplication.models.CartStorage
import java.math.RoundingMode

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMG = "extra_img"
        const val EXTRA_HARGA = "extra_harga"
    }
    lateinit var ref: DatabaseReference
    lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref = FirebaseDatabase.getInstance("https://food-application-af312-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        auth = FirebaseAuth.getInstance()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var tvProdukName = binding.tvProdukName
        var tvToping = binding.tvToping
        binding.rbAyam.setOnClickListener{
            if (binding.rb1.isChecked == false){
                binding.rb1.isChecked = true
               tvToping.text = "${tvToping.text} + ${binding.tvNamaLauk1.text}"
            }else{
                binding.tvToping.text = ""
                binding.rb1.isChecked = false
            }


        }
        binding.rbKankung.setOnClickListener{
            if (binding.rb2.isChecked == false){
                binding.rb2.isChecked = true
                tvToping.text = "${tvToping.text} + ${binding.tvNamaLauk2.text}"
            }else{
                binding.tvToping.text = ""
                binding.rb2.isChecked = false
            }


        }
        binding.rbTempe.setOnClickListener{
            if (binding.rb3.isChecked == false){
                binding.rb3.isChecked = true
                tvToping.text = "${tvToping.text} + ${binding.tvNamaLauk3.text}"
            }else{
                binding.tvToping.text = ""
                binding.rb3.isChecked = false
            }


        }
        binding.rBebek.setOnClickListener{
            if (binding.rb4.isChecked == false){
                binding.rb4.isChecked = true
                tvToping.text = "${tvToping.text} + ${binding.tvNamaLauk4.text}"
            }else{
                binding.tvToping.text = ""
                binding.rb4.isChecked = false
            }


        }


        val tvHarga = binding.tvHargaProduk
        val imgProduk = binding.imgProdukDetail

        var produkName = intent.getStringExtra(EXTRA_NAME)
        var hargaProduk = intent.getIntExtra(EXTRA_HARGA,10)
        var imageProduk = intent.getStringExtra(EXTRA_IMG)
        val dec = DecimalFormat("#,###.##")
        val harga = dec.format(hargaProduk)
        tvProdukName.text = produkName
        tvHarga.text = "Rp " + harga.toString()
        Glide.with(this)
            .load(imageProduk)
            .into(imgProduk)

        binding.btnCart.setOnClickListener {

            pushData(produkName!!,imageProduk!!,hargaProduk!!)
        }

    }

    fun pushData(produk: String,image : String, Harga :Int) {
        val uid = auth.currentUser?.uid.toString()
        val CartData =  CartStorage(produk,image,Harga)

        if (produk == "") {
            Toast.makeText(this, "Cannot be Null!", Toast.LENGTH_LONG).show()
        } else {
            Log.e("cek", "clicked")
            ref.child("cart").child(uid).child(produk).setValue(CartData).addOnCompleteListener() {
                Log.e("push data", "succes")
                Toast.makeText(this@DetailActivity, "Add Data Succes!", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@DetailActivity, "${it.message.toString()}", Toast.LENGTH_LONG).show()
                Log.e("error", it.message.toString())
            }
        }
    }


}
