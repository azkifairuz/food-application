package com.javfairuz.foodapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.adapter.HomeAdapter
import com.javfairuz.foodapplication.models.Produk

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var produkRecyclerview :RecyclerView
    lateinit var adapter: HomeAdapter
    private  var list: ArrayList<Produk> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
        lateinit var ref:DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        produkRecyclerview = view.findViewById(R.id.rvTop)
        produkRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        produkRecyclerview.setHasFixedSize(true)
        ref = FirebaseDatabase.getInstance("https://food-application-af312-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        adapter = HomeAdapter()
        produkRecyclerview.adapter = adapter

        val rvMenuPaket :RecyclerView = view.findViewById(R.id.rvMenuPaket)
        rvMenuPaket.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        rvMenuPaket.setHasFixedSize(true)
        rvMenuPaket.adapter = adapter

        val rvAndalanKost :RecyclerView = view.findViewById(R.id.rvAndalanKost)
        rvAndalanKost.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        rvAndalanKost.setHasFixedSize(true)
        rvAndalanKost.adapter = adapter

        val rvMenuLauk :RecyclerView = view.findViewById(R.id.rvMenuLauk)
        rvMenuLauk.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        rvMenuLauk.setHasFixedSize(true)
        rvMenuLauk.adapter = adapter


        //list.addAll(DataProduk.listProduk)
        getData()
//       adapter.addProdukList(list)
    }

    fun getData(){
        Log.e("T","berhasil1")
        val da =ref.child("products").orderByChild("category")
            .equalTo("regular").get()

        da.addOnSuccessListener {
            if(!it.exists()){
                Log.e("T","gada")
            }
           val data =  it.children.map{ dataSnapshot ->
               Log.e("T",dataSnapshot.toString())

               dataSnapshot.getValue(Produk::class.java)!!

            }
            adapter.addProdukList(data)
        }.addOnFailureListener {
            Log.e("T","berhasil3 ${it.message}")
        }
    }
}