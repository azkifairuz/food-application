package com.javfairuz.foodapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.javfairuz.foodapplication.R

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME ="extra_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvNamaUser: TextView = findViewById(R.id.tvNama)

        var name = intent.getStringExtra(EXTRA_NAME)

        var text = "Selamat Datang $name"

        tvNamaUser.text = text
    }
}