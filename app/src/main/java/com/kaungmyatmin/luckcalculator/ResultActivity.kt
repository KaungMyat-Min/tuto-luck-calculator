package com.kaungmyatmin.luckcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        btnButton.setOnClickListener {
            //trigger clicking on back key on phone
            onBackPressed()
        }

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        val result = calculate(year, month, day)
        val resultId = when (result) {
            1 -> R.string.dob_result_1
            2 -> R.string.dob_result_2
            3 -> R.string.dob_result_3
            4 -> R.string.dob_result_4
            5 -> R.string.dob_result_5
            6 -> R.string.dob_result_6
            7 -> R.string.dob_result_7
            8 -> R.string.dob_result_8
            else -> R.string.dob_result_9
        }

        tvResultNumber.text = result.toString()
        tvResult.text = getString(resultId)

    }


    //region helper methods

    private fun calculate(year: Int, month: Int, day: Int): Int {
        var total = sum(year)
        total += sum(month)
        total += sum(day)
        while (total > 9) {
            total = sum(total)
        }
        return total
    }

    private fun sum(number: Int): Int {
        var total = 0
        for (i in number.toString()) {
            total += Character.getNumericValue(i)
        }
        return total
    }
    //endregion
}
