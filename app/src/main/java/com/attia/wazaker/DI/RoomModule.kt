package com.attia.wazaker.DI

import android.content.Context
import androidx.room.Room
import com.attia.wazaker.database.AzkaarDatabaseDao
import com.attia.wazaker.database.WazakerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun ProvideWazakerDatabase(@ApplicationContext context: Context): WazakerDatabase {
        return Room.databaseBuilder(
            context,
            WazakerDatabase::class.java,
            "Wazaker.DB"
        ).build()
    }

    @Provides
    fun ProvideAzkaarDao(wazakerdb: WazakerDatabase): AzkaarDatabaseDao {
        return wazakerdb.azkaarDatabaseDao
    }
}