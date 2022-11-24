package com.javfairuz.foodapplication.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.adapter.CartAdapter
import com.javfairuz.foodapplication.databinding.ActivityCartBinding
import com.javfairuz.foodapplication.databinding.ActivityDetailBinding
import com.javfairuz.foodapplication.models.CartStorage

class CartActivity : AppCompatActivity() {

    private lateinit var ref :DatabaseReference
    lateinit var adapterCart: CartAdapter
    lateinit var auth: FirebaseAuth
    lateinit var myRv :RecyclerView
    var subTotal: Int = 0
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mengkoneksikan dengan database
        ref = FirebaseDatabase.getInstance("https://food-application-af312-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        //mendefinisikan adapter
        adapterCart = CartAdapter()



        //definisi auth
        auth = FirebaseAuth.getInstance()
        getDataCart()
        //defini recyvler view
        myRv = findViewById(R.id.rvCart)
        myRv.setHasFixedSize(true)
        myRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        myRv.adapter = adapterCart


        var totalPrice = findViewById<TextView>(R.id.total)

        binding.btnPayNow.setOnClickListener {
            val payIntent = Intent(this@CartActivity,ActivityPayment::class.java)
            payIntent.putExtra(ActivityPayment.EXTRA_NAME, subTotal + 20000)
            payIntent.putExtra(ActivityPayment.EXTRA_HARGA,subTotal + 20000)

            startActivity(payIntent)
        }




    }

    //fungsi ambil data dri database
    fun getDataCart() {
        val uid = auth.currentUser?.uid.toString()
        Log.e("T", "berhasil1")
        //mengambil data kategori regular
        val da = ref.child("cart").child(uid).get()
        da.addOnSuccessListener {
            if (!it.exists()) {
                Log.e("T", "gada")
            }
            val data = it.children.map { dataSnapshot ->
                Log.e("T", dataSnapshot.toString())

                dataSnapshot.getValue(CartStorage::class.java)!!

            }
            subTotal = 0
            var tvTotalPrice :TextView = findViewById(R.id.subTotal)
            val dec = DecimalFormat("#,###.##")
            for (i in data.indices) {
                subTotal += data[i].harga


            }


            var subTotalFormat = dec.format(subTotal)
            var totalFormat =  dec.format(subTotal + 20000)
            var totalPrice = findViewById<TextView>(R.id.total)
            totalPrice.text = "Rp " + totalFormat
            tvTotalPrice.text = "Rp " + subTotalFormat.toString()
        adapterCart.addCartList(data)
        }.addOnFailureListener {
            Log.e("T", "berhasil3 ${it.message}")
        }
    }


}