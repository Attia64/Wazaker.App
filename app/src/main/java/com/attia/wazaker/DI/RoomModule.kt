package com.attia.wazaker.DI

import android.content.Context
import android.widget.GridLayout.Spec
import androidx.room.Room
import com.attia.wazaker.database.AzkaarDatabaseDao
import com.attia.wazaker.database.HistoryDatabaseDao
import com.attia.wazaker.database.WazakerDatabase
import com.attia.wazaker.ui.fragments.azkaar.specificazkaar.SpecficAzkaarType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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
    @Named("DaoAzkaar")
    fun ProvideAzkaarDao(wazakerdb: WazakerDatabase): AzkaarDatabaseDao {
        return wazakerdb.azkaarDatabaseDao
    }

    @Provides
    @Named("DaoHistory")
    fun ProvideHistoryDao(wazakerdb: WazakerDatabase): HistoryDatabaseDao {
        return wazakerdb.historyDatabaseDao
    }
}