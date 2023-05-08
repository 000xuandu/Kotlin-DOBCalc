package dulx.tutorials.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDateBtn = findViewById<Button>(R.id.selectDateBtn)
        selectDateBtn.setOnClickListener {
            onPressSelectDateBtn()
        }
    }

    private fun onPressSelectDateBtn() {
        val selectedDateTextView = findViewById<TextView>(R.id.selectedDate)
        val selectedDateInMinutesTextView = findViewById<TextView>(R.id.dateToMinutes)
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, dayOfMonth ->
                val dateString = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"
                selectedDateTextView.text = dateString;
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDateInMinutes = (sdf.parse(dateString)?.time ?: 1) / 60000
                val currentDateInMinutes = (sdf.parse(sdf.format(System.currentTimeMillis()))?.time ?: 1) / 60000
                selectedDateInMinutesTextView.text = (currentDateInMinutes - selectedDateInMinutes).toString()
            },
            year,
            month,
            day,
        )
//        dpd.datePicker.maxDate =
        dpd.show()
    }
}
