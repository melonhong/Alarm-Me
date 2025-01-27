package com.example.alarmtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sub)

        var stopBtn = findViewById<Button>(R.id.btn_stop)
        val intent = Intent(this, MainActivity::class.java)
        stopBtn.setOnClickListener {
            startActivity(intent)
        }
    }
}