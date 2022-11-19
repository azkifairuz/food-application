package com.javfairuz.foodapplication.repository

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.javfairuz.foodapplication.models.Produk
import org.w3c.dom.Text

class ProdukRepository {
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("products")

    @Volatile private var INSTANCE : ProdukRepository ? = null

    fun getInstance() : ProdukRepository{
        return INSTANCE ?: synchronized(this){
            val instance = ProdukRepository()
            INSTANCE = instance
            instance
        }

    }

    fun loadProduk(produkList : MutableLiveData<List<Produk>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    val _produkList : List<Produk> = snapshot.children.map { dataSnapshot ->
                            dataSnapshot.getValue(Produk::class.java)!!

                    }
                    produkList.postValue(_produkList)

            }

            override fun onCancelled(error: DatabaseError) {
               Log.w(TAG,"gagal baca data",error.toException())
            }

        })
    }
}