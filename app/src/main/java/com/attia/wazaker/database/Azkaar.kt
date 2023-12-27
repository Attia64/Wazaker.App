package com.attia.wazaker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Azkaar_Table")
data class Azkaar(
    @PrimaryKey(autoGenerate = true)
    val zekrId: Long = 0L,

    @ColumnInfo(name = "Zekr_Name")
    val zekr: String
)