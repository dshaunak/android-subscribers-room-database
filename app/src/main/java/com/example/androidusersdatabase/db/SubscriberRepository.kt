package com.example.androidusersdatabase.db

class SubscriberRepository(private val dao : SubscriberDAO) {

    // No need to explicitly use coroutine to get the data from the database as this operation automatically happens using coroutines
    val subscribers = dao.getAllSubscribers()

    //All other database manipulation functions will need to be of type 'suspend' as we use coroutines for it.
    suspend fun insert(subscriber: Subscriber):Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber):Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber):Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll():Int{
        return dao.deleteAll()
    }


}