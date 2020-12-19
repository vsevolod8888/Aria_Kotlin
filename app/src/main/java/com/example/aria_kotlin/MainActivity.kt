package com.example.aria_kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aria_kotlin.R
import java.util.*


class MainActivity : AppCompatActivity() {
    var play: Button? = null
    var tvScore: TextView? = null
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    @SuppressLint("RestrictedApi", "WrongConstant")
    private fun initialize() {
        play = findViewById<View>(R.id.button_start_to_play) as Button
        tvScore = findViewById(R.id.tvScore)
        val animZoomIn = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)
        play!!.startAnimation(animZoomIn)
        play!!.setOnClickListener {
            finish()
            val startGame1 = Intent(baseContext, PlayActivity::class.java)
            startActivity(startGame1)
        }
        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(this)
        // Long countBring = sharedPreferences.getLong(String.va);
        val countBring = sharedPreferences.getLong(KEY.toString(), 0)
        updateOurCountDownText(countBring)
    }

    private fun updateOurCountDownText(m: Long) {
        val minutes =
            (m / 1000).toInt() / 60 // из миллисекунд, кот.остались до конца получаем минуты
        val seconds = (m / 1000).toInt() % 60 //получаем секунды, напр.2%60 =2,
        val timeLeftFormatted = String.format(Locale.getDefault(), "%2d:%02d", minutes, seconds)
        tvScore!!.text = "Рекорд : $timeLeftFormatted"
    }

    companion object {
        private const val SHARED_PREFS = "sharedPrefs"
        private const val KEY = 10
    }
}