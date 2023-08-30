package com.example.barbershop.presentation

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: java.util.Calendar = java.util.Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        FirebaseApp.initializeApp(this)



        val nome = intent.extras?.getString("name").toString()

        val dataPicker = binding.datePicker
        dataPicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(java.util.Calendar.YEAR, year)
            calendar.set(java.util.Calendar.MONTH, monthOfYear)
            calendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)

            var day = dayOfMonth.toString()
            val month: String

            if (dayOfMonth < 10) {

                day = "0$dayOfMonth"
                month = (monthOfYear + 1).toString()

            } else if (monthOfYear < 10) {

                month = " " + (monthOfYear + 1)


            } else {


                month = (monthOfYear + 1).toString()


            }

            data = "$day / $month / $year"


        }

        binding.TimePicker.setOnTimeChangedListener { _, hourOfDay, minute ->


            var minutetime: String

            if (minute < 10) {


                minutetime = "0$minute"


            } else {

                minutetime = minute.toString()


            }





            hora = "$hourOfDay:$minutetime"


        }


        binding.TimePicker.setIs24HourView(true)


        binding.BtnAgendar.setOnClickListener {


            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            when {

                hora.isEmpty() -> {

                    mensage(it, "Preencha o horário.", "#FF0000")

                }

                hora < "8:00" && hora > "19:00" -> {

                    mensage(
                        it,
                        "fora do horário de funcionamento - horário de atendimento das 8:00 as 19:00.",
                        "#FF0000"
                    )
                }

                data.isEmpty() -> {

                    mensage(it, "Preencha a data.", "#FF0000")
                }

                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                   saveSchedule(it, nome, "Juliano Flores", data, hora)

                }

                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                    saveSchedule(it, nome, "Erick Januario", data, hora)

                }

                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                    saveSchedule(it, nome, "Luiz Soares", data, hora)

                }

                else -> {

                    mensage(it, "Escolha um barbeiro.", "#FF0000")

                }


            }


        }


    }

    private fun mensage(view: View, mensage: String, color: String) {

        val snackbar = Snackbar.make(view, mensage, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(color))
        snackbar.setTextColor(Color.parseColor("#FFFFFFFF"))
        snackbar.show()

    }

    private fun saveSchedule(
        view: View, client: String, barber: String, date: String, hour: String
    ) {



        val db = FirebaseFirestore.getInstance()
        val dataUser = hashMapOf(

            "client" to client, "barber" to barber, "date" to date, "hour" to hour


        )

        db.collection("agendamento").document(client).set(dataUser).addOnCompleteListener {

            mensage(view, "Agendamento realizado com sucesso.", "#FF03DAC5")


        }.addOnFailureListener {

            mensage(view, "Server Error.", "#FF0000")

        }


    }

}