package com.javfairuz.foodapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.javfairuz.foodapplication.R

class FirstPageActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_page_activity)

        var btnFirst: Button = findViewById(R.id.firstButton)
        btnFirst.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.firstButton->{
                var moveToLoginPage = Intent(this@FirstPageActivity, LoginActivity::class.java)
                startActivity(moveToLoginPage)
            }
        }
    }
}


