package com.javfairuz.foodapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.SharedPreference
import com.javfairuz.foodapplication.SharedPreference.Companion.PREF_IS_LOGIN
import com.javfairuz.foodapplication.SharedPreference.Companion.PREF_PASSWORD
import com.javfairuz.foodapplication.SharedPreference.Companion.PREF_USERNAME
import com.javfairuz.foodapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var  sharedPref : SharedPreference
    private lateinit var showpassword : CheckBox
    private lateinit var edtPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPref = SharedPreference(this@LoginActivity)

        auth = FirebaseAuth.getInstance()

        binding.tvDaftar.setOnClickListener{
            val intentToRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentToRegister)
            finish()
        }

        showpassword = findViewById(R.id.cbPasswordLogin)
        edtPassword = findViewById(R.id.edtPasswordLogin)

        //hidden passoword
        showpassword.setOnClickListener{
            if(showpassword.isChecked){
                showpassword.background = resources.getDrawable(R.drawable.open_eye)
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            }else{
                showpassword.background = resources.getDrawable(R.drawable.close_eye)
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        binding.loginButton.setOnClickListener{
            val email = binding.edtEmailLogin.text.toString().trim()
            val password = binding.edtPasswordLogin.text.toString()
            //validasi email
            //kalo email kosong usaer dipaksa buat masukin email
            if (email.isEmpty()){
                binding.edtEmailLogin.error = "Email Harus diisi"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //validasi jika bukan email
            //kalo formatnya bukan email juga gabisa
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailLogin.error = "email tidak valid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
            //valiasi password
            //ini kalo password nya kosing juga user bkl dipaksa buat ngisi
            if (password.isEmpty()){
                binding.edtPasswordLogin.error = "password tidak boleh kosong "
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }

    }


    override fun onStart() {

        super.onStart()
        if(auth.currentUser != null){
            val intentToMain = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }
    }
    //function buat ngecek apakah username yg ditulis sama user itu adaa difirebase
    fun LoginFirebase(email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    //kalo akunya ada di firebase dia bkl langsung masuk
                    if (it.isSuccessful){

                        sharedPref.setSessionString(PREF_USERNAME, binding.edtEmailLogin.text.toString())
                        sharedPref.setSessionString(PREF_PASSWORD, binding.edtPasswordLogin.text.toString())
                        sharedPref.setSessionBool(PREF_IS_LOGIN, true)
                        Toast.makeText(this,"selamat datang $email", Toast.LENGTH_SHORT).show()
                        val intentToMain = Intent(this, MainActivity::class.java)
                        startActivity(intentToMain)
                        finish()

                    }else{
                        //kalo ngga ada dia bkl nampilin pesan alasan knp si user gabisa masuk?
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
    }
}