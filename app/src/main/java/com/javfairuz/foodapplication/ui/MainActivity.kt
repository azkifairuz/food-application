package com.javfairuz.foodapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.SharedPreference
import com.javfairuz.foodapplication.databinding.ActivityMainBinding
import com.javfairuz.foodapplication.ui.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref : SharedPreference
    private lateinit var layout : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SharedPreference(this@MainActivity)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

//        layout = layoutInflater.inflate(R.layout.nav_header,null)
//        val closeNav = layout.findViewById<Button>(R.id.closeIcon)
        binding.closeIcon.setOnClickListener {
            binding.navView.visibility = View.GONE
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        }

        binding.profilNav.setOnClickListener {
            binding.navView.visibility = View.VISIBLE
        }


        binding.btnLogout.setOnClickListener {
              sharedPref.clearSession()
            Toast.makeText(this, "berhasil Logout", Toast.LENGTH_SHORT).show()
            val logoutIntent = Intent(this@MainActivity,FirstPageActivity::class.java)
            startActivity(logoutIntent)
        }

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