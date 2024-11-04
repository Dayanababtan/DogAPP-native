package com.example.dogapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dogsList: ArrayList<Dogs>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item2, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dogsList[position]

        holder.name.text = "Name: " + currentItem.name
        holder.breed.text = "Breed: " + currentItem.breed
        holder.activity.text = "Activity: " + currentItem.activity
        holder.age.text = "Age: ${currentItem.age} years"
        holder.weight.text = "Weight: ${currentItem.weight} lbs"

        holder.deleteDogButton.setOnClickListener {
            dogsList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dogsList.size)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val age: TextView = itemView.findViewById(R.id.age)
        val weight: TextView = itemView.findViewById(R.id.weight)
        val activity: TextView = itemView.findViewById(R.id.activity)
        val breed: TextView = itemView.findViewById(R.id.breed)
        val deleteDogButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
