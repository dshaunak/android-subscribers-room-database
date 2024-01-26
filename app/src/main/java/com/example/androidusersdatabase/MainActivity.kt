package com.example.androidusersdatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidusersdatabase.databinding.ActivityMainBinding
import com.example.androidusersdatabase.db.Subscriber
import com.example.androidusersdatabase.db.SubscriberDatabase
import com.example.androidusersdatabase.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter : MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            val dao = SubscriberDatabase.getInstance(application).subscriberDAO
            val repository = SubscriberRepository(dao)
            val factory = SubscriberViewModelFactory(repository)
            subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
            binding.myViewModel = subscriberViewModel
            binding.lifecycleOwner = this

            initRecyclerView()

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(){
       binding.subscriberRV.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter { selectedSubscriber: Subscriber ->
            listItemClicked(selectedSubscriber)
        }
        binding.subscriberRV.adapter = adapter
        displaySubscribersList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        //Toast.makeText(this, "Selected name is ${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscriberViewModel.iniUpdateAndDelete(subscriber)
    }

}