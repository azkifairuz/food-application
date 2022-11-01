package com.javfairuz.foodapplication.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.javfairuz.foodapplication.R
import com.javfairuz.foodapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var showpassword : CheckBox
    private lateinit var edtPassword : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        showpassword = findViewById(R.id.cbPassword)
        edtPassword = findViewById(R.id.edtPasswordRegister)
        showpassword.setOnClickListener{
            if(showpassword.isChecked){
                showpassword.background = resources.getDrawable(R.drawable.open_eye)
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            }else{
                showpassword.background = resources.getDrawable(R.drawable.close_eye)
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        binding.tvRegister.setOnClickListener{
            val intentToLogin = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intentToLogin)
        }
        binding.registerButton.setOnClickListener{
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()
            val username = binding.edtUsernameRegister.text.toString()
            //validasi email

            if (email.isEmpty()){
                binding.edtEmailRegister.error = "Email Harus diisi"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }

            //validasi jika bukan email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailRegister.error = "email tidak valid"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            //valiasi password
            if (password.isEmpty()){
                binding.edtPasswordRegister.error = "password tidak boleh kosong "
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            //validasoi panjang password
            if(password.length < 8){
                binding.edtPasswordRegister.error = " password minimal 8 angka "
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,password)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Daftar berhasil",Toast.LENGTH_SHORT).show()
                    val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(loginIntent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }
    }


}