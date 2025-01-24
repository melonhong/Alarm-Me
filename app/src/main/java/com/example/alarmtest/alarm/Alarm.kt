package com.example.amulatortest

import java.time.DayOfWeek
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.UUID

interface AlarmSound { // 알람 사운드에 관한 인터페이스(알람 클래스가 구현)
    var soundPath: String
    var volume: Float

    fun playSound()
    fun stopSound()
}

class Alarm ( // 본격적인 알람 클래스
    private val id: UUID = UUID.randomUUID(),
    var startTime: LocalTime,
    var activeDays: Set<DayOfWeek>,
    var repeatCount: Int,
    var repeatInterval: LocalTime
) : AlarmSound {
    // AlarmSound를 오버 라이드 하는 부분
    override var soundPath = "res/sounds/default.mp3"
    override var volume = 0.5f
        set(value) {
            field = value.coerceIn(0.0f, 1.0f) // 0.0~1.0 범위로 제한
        }

    override fun playSound() {
        println("${ this.soundPath } is ringing")
    }

    override fun stopSound() {
        println("stop ${ this.soundPath }")
    }

    // Alarm의 멤버 함수
    fun getId(): UUID { return this.id }

    fun checkNow() { // 현재 시간이 알람을 울릴 시간인지 체크
        val currentTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS) // 나노초 제거
        val alarmTime = this.startTime.truncatedTo(ChronoUnit.SECONDS)    // 나노초 제거

        if (alarmTime== currentTime) {
            startAlarm()
        }
    }

    fun startAlarm() {
        if (repeatCount-- > 0) {
            println("start ${this.getId()} alarm")
            playSound()
        }
    }

    fun stopAlarm() {
        println("stop the alarm")
        stopSound()
    }

    fun stopAllAlarm() {
        println("stop all alarms")
        this.repeatCount = 0
    }
}