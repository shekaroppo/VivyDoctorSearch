package com.takeaway.injection.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.takeaway.TakeawayApplication
import com.takeaway.data.Where2EatPreferences
import com.takeaway.data.db.LocalDataSource
import com.takeaway.data.db.RestaurantDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(NetworkModule::class), (AppModuleBinds::class)])
class AppModule {
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): RestaurantDatabase {
        return Room.databaseBuilder(application, RestaurantDatabase::class.java, "restaurant").build()
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(application: Application): Where2EatPreferences {
        val pref = application.getSharedPreferences("where2eatPref", Context.MODE_PRIVATE)
        return Where2EatPreferences(pref)
    }

    @Provides
    @Singleton
    internal fun provideLocalDataSource(restaurantDatabase: RestaurantDatabase, where2EatPreferences: Where2EatPreferences): LocalDataSource {
        return LocalDataSource(restaurantDatabase, where2EatPreferences)
    }
}

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun provideApplication(context: TakeawayApplication): Application
}