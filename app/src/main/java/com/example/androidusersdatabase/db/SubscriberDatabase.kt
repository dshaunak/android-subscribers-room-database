package com.example.androidusersdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Important to extend the abstract class as 'RoomDatabase()' and
// add the @Database Annotation.
// Here we must pass the entities to @Database and the version number.
// Version number will be used during database migrations.
@Database(entities = [Subscriber::class] , version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    // Now we declare an abstract reference for the DAO Interface
    abstract val subscriberDAO : SubscriberDAO

    // In one project we should try to use only 1 instance of a Database object, to avoid unexpected errors
    //Hence create a singleton companion object
    companion object{
        //@Volatile makes below field immediately visible to other threads. Hence only 1 instance is manipulated even via multiple threads.
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null
        fun getInstance(context: Context) : SubscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}