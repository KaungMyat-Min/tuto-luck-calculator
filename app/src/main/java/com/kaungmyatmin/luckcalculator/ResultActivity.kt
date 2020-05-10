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
