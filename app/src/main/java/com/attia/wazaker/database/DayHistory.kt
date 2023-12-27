package com.attia.wazaker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Azkaar_History_Table")
data class DayHistory(
    @PrimaryKey(autoGenerate = true)
    var zekrId: Long = 0L,

    @ColumnInfo(name = "Zekr_Name")
    val zekrName: String,

    @ColumnInfo(name = "Local_Date_Time")
    val localDateTime: LocalDateTime
)
