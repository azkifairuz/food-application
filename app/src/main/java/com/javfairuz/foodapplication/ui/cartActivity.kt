package com.javfairuz.foodapplication.ui

import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.adapter.CartAdapter
import com.javfairuz.foodapplication.models.CartStorage
import com.javfairuz.foodapplication.models.Produk

class cartActivity : AppCompatActivity() {

    private lateinit var ref :DatabaseReference
    lateinit var adapterCart: CartAdapter
    lateinit var auth: FirebaseAuth
    lateinit var myRv :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)



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

        val CartList = ArrayList<CartStorage>()
        var subTotal = 0
        var tvTotalPrice :TextView = findViewById(R.id.subTotal)
        for (i in CartList.indices) {
            subTotal += CartList[i].harga


        }
        tvTotalPrice.text = subTotal.toString()





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
        adapterCart.addCartList(data)
        }.addOnFailureListener {
            Log.e("T", "berhasil3 ${it.message}")
        }
    }
}