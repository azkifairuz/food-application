package com.javfairuz.foodapplication.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javfairuz.foodapplication.repository.ProdukRepository

class ProdukViewModel :ViewModel() {

    private val repository: ProdukRepository
    private val _allproduk = MutableLiveData<List<Produk>>()
    val allProduk : LiveData<List<Produk>> = _allproduk

    init {
        repository = ProdukRepository().getInstance()
        repository.loadProduk(_allproduk)
    }
}