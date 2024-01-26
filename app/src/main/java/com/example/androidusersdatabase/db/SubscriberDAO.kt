package com.example.androidusersdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriberDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll() : Int

    // In get Query using SELECT _ FROM, the returned data can be return as LiveData values.
    // The get Query is always automatically run on a Background thread by Room,
    // hence we don't need to use "suspend" or define it as async.
    // These types of Queries are call Asynchronous Queries.
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers() : LiveData<List<Subscriber>> // Can replace this with Flow<List<Subscriber>> if we are using Flow

}