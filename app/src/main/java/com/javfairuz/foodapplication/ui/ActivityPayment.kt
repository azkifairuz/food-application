package com.javfairuz.foodapplication.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.ActivityPaymentBinding

class ActivityPayment : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_HARGA = "extra_harga"
    }
    private lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var totalPay = intent.getIntExtra(EXTRA_NAME,0)
        val harga = intent.getIntExtra(EXTRA_HARGA,0)
        val dec = DecimalFormat("#,###.##")
         binding.totalPayment.text ="Rp " + dec.format(totalPay)
        var totalFinal = totalPay + 1000


        var metodePembayaran = binding.tvDana.text
        //creditMethod
        binding.creditMethod.setOnClickListener {
            if (!binding.rbMasterCard.isChecked){
                binding.rbMasterCard.isChecked = true
                binding.rbBca.isChecked =false
                binding.rbBri.isChecked =false
                binding.rbMandiri.isChecked =false
                binding.rbDana.isChecked =false
                metodePembayaran = binding.tvCredit.text
            }else{
                binding.rbMasterCard.isChecked = false
            }
        }
        //bca method
        binding.bcaMethod.setOnClickListener {
            if (!binding.rbBca.isChecked){
                binding.rbMasterCard.isChecked =false
                binding.rbBca.isChecked = true
                binding.rbBri.isChecked =false
                binding.rbMandiri.isChecked =false
                binding.rbDana.isChecked =false
                metodePembayaran = binding.tvBca.text
            }else{
                binding.rbBca.isChecked = false
            }
        }
        //briMethod
        binding.briMethod.setOnClickListener {
            if (!binding.rbBri.isChecked){
                binding.rbMasterCard.isChecked = false
                binding.rbBca.isChecked =false
                binding.rbBri.isChecked =true
                binding.rbMandiri.isChecked =false
                binding.rbDana.isChecked =false
                metodePembayaran = binding.tvBri.text
            }else{
                binding.rbBri.isChecked = false
            }
        }
        //mandiriMethod
        binding.mandiriMethpd.setOnClickListener {
            if (!binding.rbMandiri.isChecked){
                binding.rbMasterCard.isChecked = false
                binding.rbBca.isChecked =false
                binding.rbBri.isChecked =false
                binding.rbMandiri.isChecked =true
                binding.rbDana.isChecked =false
                metodePembayaran = binding.tvMandiri.text
            }else{
                binding.rbMandiri.isChecked = false
            }
        }
        //danaMethod
        binding.danaclick.setOnClickListener {
            if (!binding.rbDana.isChecked){
                binding.rbMasterCard.isChecked = false
                binding.rbBca.isChecked =false
                binding.rbBri.isChecked =false
                binding.rbMandiri.isChecked =false
                binding.rbDana.isChecked =true
                metodePembayaran = binding.tvDana.text
            }else{
                binding.rbDana.isChecked = false
            }
        }

        binding.bankMethod.setOnClickListener   {
            if (binding.expand.visibility == View.VISIBLE){
                binding.expand.visibility = View.GONE
                binding.collapse.visibility = View.VISIBLE
                binding.methodChoose.visibility = View.VISIBLE
            }else{
                binding.collapse.visibility = View.GONE
                binding.methodChoose.visibility = View.GONE
                binding.expand.visibility = View.VISIBLE
            }


        }
        binding.btnPay.setOnClickListener {
            val paymentSucces =  Intent(this@ActivityPayment,SuccessActivity::class.java)
            paymentSucces.putExtra(SuccessActivity.EXTRA_METHOD,metodePembayaran)
            paymentSucces.putExtra(SuccessActivity.EXTRA_SUBTOTAL,harga)
            paymentSucces.putExtra(SuccessActivity.EXTRA_TOTAL,totalFinal)
            startActivity(paymentSucces)
            finish()
        }
    }


}