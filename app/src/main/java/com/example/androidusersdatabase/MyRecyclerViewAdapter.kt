package com.example.androidusersdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidusersdatabase.databinding.ListItemBinding
import com.example.androidusersdatabase.db.Subscriber

class MyRecyclerViewAdapter(private val clickListener:(Subscriber) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {

    private val subscribersList = ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listBinding : ListItemBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)
        return MyViewHolder(listBinding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    fun setList(subscribers: List<Subscriber>){
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber : Subscriber, clickListener:(Subscriber) -> Unit){
        binding.nameTV.text = subscriber.name
        binding.emailTV.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}