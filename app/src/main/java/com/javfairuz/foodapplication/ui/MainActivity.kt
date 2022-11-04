package com.javfairuz.foodapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.adapter.HomeAdapter
import com.javfairuz.foodapplication.databinding.ActivityMainBinding
import com.javfairuz.foodapplication.models.DataProduk
import com.javfairuz.foodapplication.models.Produk
import com.javfairuz.foodapplication.ui.fragment.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.action_home -> replaceFragment(HomeFragment())
                R.id.action_chat -> replaceFragment(chatFragment())
                R.id.action_pembayaran -> replaceFragment(PembayaranFragment())
                R.id.action_pengiriman -> replaceFragment(PengirimanFragment())
                else->{


                }

            }
            true
        }



//
//
//        showProduk()

    }

    private fun replaceFragment(fragment: Fragment) {
        val FragmentManager = supportFragmentManager
        val fragmentTransaction = FragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }


//    private fun showProduk() {
//
//        db = FirebaseFirestore.getInstance()
//        db.collection("product").
//                addSnapshotListener(object : EventListener<QuerySnapshot>{
//                    override fun onEvent(
//                        value: QuerySnapshot?,
//                        error: FirebaseFirestoreException?
//                    ) {
//                        if(error != null ){
//                            Log.e("Firestore Error",error.message.toString())
//                            return
//                        }
//                        for (dc :DocumentChange in value?.documentChanges!!){
//                            if(dc.type == DocumentChange.Type.ADDED){
//                                produkArayList.add(dc.document.toObject(Produk::class.java))
//
//                            }
//                        }
//
//
//                        adapter.setData(produkArayList)
//
//                    }
//
//
//
//                })
//    }
}