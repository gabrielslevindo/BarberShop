package com.example.barbershop.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barbershop.Adapter.ServicesAdapter
import com.example.barbershop.R
import com.example.barbershop.data.Services
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.databinding.ActivityMainBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var Adapeter: ServicesAdapter
    private val ListServices: MutableList<Services> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        val name = intent.extras?.getString("name")
        binding.txtUserName.text = "Bem-vindo(a), $name"

        val rvServices = binding.rvServices
        rvServices.layoutManager = GridLayoutManager(this,2)
        Adapeter = ServicesAdapter(this, ListServices)
        rvServices.setHasFixedSize(true)
        rvServices.adapter = Adapeter
        getServices()


        binding.btnSchedule.setOnClickListener {

            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("name", name)
            startActivity(intent)


        }


    }




    fun getServices(){

        val service1 = Services(R.drawable.tesoura, "Corte de Cabelo")
        ListServices.add(service1)

        val service2 = Services(R.drawable.bigode, "Corte de Barba")
        ListServices.add(service2)

        val service3 = Services(R.drawable.cabeca, "Lavagem de Cabelo")
        ListServices.add(service3)

        val service4 = Services(R.drawable.pintura, "Pintura de Cabelo")
        ListServices.add(service4)


    }



}