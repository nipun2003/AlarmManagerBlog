package com.nipunapps.alarmmanagerblog

import java.time.LocalDateTime

data class AlarmItem(
    val alarmTime : LocalDateTime,
    val message : String
)
