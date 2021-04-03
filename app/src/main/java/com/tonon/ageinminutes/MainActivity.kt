package com.tonon.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tonon.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botao = binding.btnDatePicker
        
        botao.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }
    
    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->
            Toast.makeText(this, "A data escolhida foi $SelectedDayOfMonth / $SelectedMonth / $SelectedYear", Toast.LENGTH_LONG).show()

            val selectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
            val textData = binding.dataSelecionada

            textData.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
            val theDate = sdf.parse(selectedDate)


            // Minutos
            val dateInMinute = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentToMinutes = currentDate!!.time / 60000

            val difference = currentToMinutes - dateInMinute

            val selectedDateInMinute = binding.idadeMinutos
            selectedDateInMinute.setText(difference.toString())


            //Dias
            val selectedDateInDay = theDate!!.time / (60000 * 60 * 24)
            val currentDay = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToDay = currentDay!!.time/ (60000 * 60 * 24)
            val differenceDay = currentDateToDay - selectedDateInDay

            val selectedDay = binding.idadeDias
            selectedDay.setText(differenceDay.toString())

        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}