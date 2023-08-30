package com.example.barbershop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.presentation.Home
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.buttomLogin.setOnClickListener {

            val nome = binding.edtNome.text.toString()
            val senha = binding.edtSenha.text.toString()

            when {

                nome.isEmpty() -> {

                    mensage(it, "Coloque seu nome aqui!")

                }
                senha.isEmpty() -> {

                    mensage(it, "Preencha a senha!")

                }
                senha.length <= 5 -> {

                    mensage(it, "A senha precisa ter no mínimo 6 caracteres!")

                }
                else -> {

                    startHomeActivity(nome)

                }

            }
        }


    }


    private fun mensage(view: View, mensage: String) {

        val snackbar = Snackbar.make(view, mensage, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF6347"))
        snackbar.setTextColor(Color.parseColor("#FFFFFFFF"))
        snackbar.show()
    }

    private fun startHomeActivity(name: String) {

        val intent = Intent(this, Home::class.java)
        intent.putExtra("name", name)
        startActivity(intent)


    }


}