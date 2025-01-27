package com.example.alarmtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.amulatortest.Alarm
import java.time.DayOfWeek
import java.time.LocalTime
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val intent = Intent(this, SubActivity::class.java)

        var createAlarmBtn = findViewById<Button>(R.id.btn_create)
        var alarmInfo = findViewById<TextView>(R.id.tv_alarm_info)

        var wt = LocalTime.now().plusSeconds(15)
        var ads = setOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
        var a = Alarm(
            startTime = wt,
            activeDays = ads,
            repeatCount = 5,
            repeatInterval = LocalTime.of(0, 1)
        )

        createAlarmBtn.setOnClickListener {
            println("created an alarm")
            alarmInfo.text = "id: ${a.getId()}\nwake time: ${a.startTime}\nactive days: ${a.activeDays}"
        }

        lifecycleScope.launch { // 코루틴을 이용하여 매 초마다 알람이 울릴 시간인지 체크
            while (true) {
                delay(1000) // 1초마다 실행
                if (a.checkNow()) {
                    startActivity(intent)
                }
            }
        }
    }
}