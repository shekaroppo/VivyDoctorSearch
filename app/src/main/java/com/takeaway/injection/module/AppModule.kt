package com.takeaway.injection.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.takeaway.TakeawayApplication
import com.takeaway.data.TakeawayPreferences
import com.takeaway.data.db.RestaurantDao
import com.takeaway.data.db.RestaurantDatabase
import com.takeaway.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [(NetworkModule::class), (AppModuleBinds::class)])
class AppModule {
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): RestaurantDatabase {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `"+Constants.TABLE_RESTAURANTSFTS+"` USING FTS4(" + "`name` TEXT,  content=`"+Constants.TABLE_RESTAURANTS+"`)")
                database.execSQL("INSERT INTO "+Constants.TABLE_RESTAURANTSFTS +"(`name`) " + "SELECT `name`  FROM "+Constants.TABLE_RESTAURANTS+"")

            }
        }
        return Room.databaseBuilder(application, RestaurantDatabase::class.java, Constants.DATABASE_NAME)
                .addMigrations(MIGRATION_1_2).build()

    }

    @Provides
    @Singleton
    internal fun provideDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    internal fun provideRestaurantDao(restaurantDatabase: RestaurantDatabase): RestaurantDao {
        return restaurantDatabase.restaurantDao()
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(application: Application): TakeawayPreferences {
        val pref = application.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        return TakeawayPreferences(pref)
    }
}

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun provideApplication(context: TakeawayApplication): Application
}