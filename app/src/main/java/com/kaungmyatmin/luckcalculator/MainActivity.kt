package com.kaungmyatmin.luckcalculator

import android.app.DatePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //region global objects
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var mediaPlayer: MediaPlayer
    //endregion


    //region global variables
    private var currentPlayerPosition = 0

    private var year = 0
    private var month = 0
    private var day = 0
    //endregion


    //region activity override methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMediaPlayer()
        currentPlayerPosition = savedInstanceState?.getInt("currentPosition", 0) ?: 0
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        resumePlayer(currentPlayerPosition)
    }

    override fun onPause() {
        super.onPause()
        pausePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPosition", currentPlayerPosition)
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
    //endregion


    //region set up listeners
    private fun setListeners() {

        lblDob.setOnClickListener {
            datePickerDialog.show()
        }

        tvDob.setOnClickListener {
            datePickerDialog.show()
        }

        btnCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)
            startActivity(intent)
        }
    }
    //endregion

    //region helper methods
    private fun createMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music)
    }

    private fun pausePlayer() {
        currentPlayerPosition = mediaPlayer.currentPosition
        mediaPlayer.pause()
    }

    private fun resumePlayer(position: Int) {
        mediaPlayer.seekTo(position)
        mediaPlayer.start()
    }

    private fun releasePlayer() {
        mediaPlayer.release()
    }


    private fun createDatePicker() {
        datePickerDialog = DatePickerDialog(this)
        datePickerDialog.setOnDateSetListener { datePicker, pickedYear, pickedMonth, pickedDay ->
            year = pickedYear

            //the month is zero-base index instead of one,i.e January return as 0
            month = pickedMonth + 1

            day = pickedDay

            tvDob.text = "$day/$month/$year"
        }
    }
    //endregion
}
