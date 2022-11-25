package com.javfairuz.foodapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.SharedPreference
import com.javfairuz.foodapplication.`interface`.Comunicator
import com.javfairuz.foodapplication.databinding.ActivityMainBinding
import com.javfairuz.foodapplication.ui.fragment.*

class MainActivity : AppCompatActivity(),Comunicator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref : SharedPreference
    private lateinit var layout : View

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SharedPreference(this@MainActivity)
        auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        layout = layoutInflater.inflate(R.layout.nav_header,null)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


        var displayName = binding.tvUsernameProfil
        displayName.text = user!!.displayName ?:"gada username"
        var displayEmail = binding.tvEmailProfil
        displayEmail.text = user!!.email ?:"tidak ada email"
//        val closeNav = layout.findViewById<Button>(R.id.closeIcon)
        binding.closeIcon.setOnClickListener {
            binding.navView.visibility = View.GONE

        }




        binding.profilNav.setOnClickListener {
            binding.navView.visibility = View.VISIBLE
        }


        binding.btnLogout.setOnClickListener {
            sharedPref.clearSession()
            auth.signOut()
            Toast.makeText(this, "berhasil Logout", Toast.LENGTH_SHORT).show()
            val logoutIntent = Intent(this@MainActivity,FirstPageActivity::class.java)
            startActivity(logoutIntent)
            finish()
        }



        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.action_home ->{ replaceFragment(HomeFragment())
                    binding.header.visibility = View.VISIBLE
                }
                R.id.action_chat -> {replaceFragment(chatFragment())
                    binding.header.visibility = View.GONE
                }
                R.id.action_pembayaran -> { replaceFragment(PembayaranFragment())
                    binding.header.visibility = View.GONE
                }

                R.id.action_pengiriman -> { replaceFragment(PengirimanFragment())
                binding.header.visibility = View.GONE
                }

                else->{


                }

            }
            true
        }



//
//
//        showProduk()

    }
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,"tekan sekali lagi untuk keluar",Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false },2000)

    }

    private fun replaceFragment(fragment: Fragment) {
        val FragmentManager = supportFragmentManager
        val fragmentTransaction = FragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }

    override fun goToCart() {
        startActivity(Intent(this@MainActivity,CartActivity::class.java))
    }

    override fun goToDetail() {
        val goToDetail = Intent(this@MainActivity,DetailActivity::class.java)

        startActivity(goToDetail)
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