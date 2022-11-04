package com.javfairuz.foodapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    private lateinit var showpassword : CheckBox
    private lateinit var edtPassword : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.tvDaftar.setOnClickListener{
            val intentToRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }

        showpassword = findViewById(R.id.cbPasswordLogin)
        edtPassword = findViewById(R.id.edtPasswordLogin)
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
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()
            //validasi email

            if (email.isEmpty()){
                binding.edtEmailLogin.error = "Email Harus diisi"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //validasi jika bukan email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailLogin.error = "email tidak valid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
            //valiasi password
            if (password.isEmpty()){
                binding.edtPasswordLogin.error = "password tidak boleh kosong "
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,password)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this,"selamat datang $email", Toast.LENGTH_SHORT).show()
                        val intentToMain = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intentToMain)
                    }else{
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
    }
}