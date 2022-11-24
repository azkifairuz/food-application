package com.javfairuz.foodapplication.ui

import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.ActivitySuccessBinding
import java.time.LocalDate

class SuccessActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_METHOD = "extra_name"
        const val EXTRA_SUBTOTAL = "extra_subtotal"
        const val EXTRA_TOTAL   = "extra_total"
    }
    private lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val current = LocalDate.now()
        binding.tvTanggal.text = current.toString()
        binding.tvPayMethod.text = intent.getStringExtra(EXTRA_METHOD)
        var formatST = intent.getIntExtra(EXTRA_SUBTOTAL,0)
        val dec = DecimalFormat("#,###.##")
        var totalPrice = intent.getIntExtra(EXTRA_TOTAL,0)
        binding.tvST.text ="Rp " + dec.format(formatST)
        binding.tvTotalPayment.text = "Rp " +  dec.format(totalPrice)


    }
}