package com.javfairuz.foodapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.FragmentPembayaranBinding
import com.javfairuz.foodapplication.ui.fragment.Text1Fragment
import com.javfairuz.foodapplication.ui.fragment.Text2Fragment
import com.javfairuz.foodapplication.ui.fragment.Text3Fragment

class FirstPageActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_page_activity)
        //autentikasi user
        auth = FirebaseAuth.getInstance()


        var btnFirst: Button = findViewById(R.id.firstButton)
        btnFirst.setOnClickListener(this)
        //cek udh ada user atau belum
        if (auth.currentUser == null){
            Toast.makeText(this, "selamat datang ", Toast.LENGTH_SHORT).show()
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        val vp:ViewPager2 = findViewById(R.id.vpFirstPage)
        val adapter = MyViewPagerAdapter(this.supportFragmentManager,lifecycle)
        vp.adapter = adapter
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.firstButton->{
                var moveToLoginPage = Intent(this@FirstPageActivity, LoginActivity::class.java)
                startActivity(moveToLoginPage)

            }
        }
    }



    class MyViewPagerAdapter(manager :FragmentManager,lifecycle: Lifecycle)  : FragmentStateAdapter(manager,lifecycle){
       private val fragmentList : MutableList<Fragment> = ArrayList()
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0->{
                    Text1Fragment()

                }
                1->{
                    Text2Fragment()
                }
                2->{
                    Text3Fragment()
                }else->{
                    Text2Fragment()
                }
            }
        }


    }
}


