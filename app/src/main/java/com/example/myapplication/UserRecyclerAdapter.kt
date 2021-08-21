package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserRecyclerAdapter(private val listUsers: List<Users>) : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_recycler, parent, false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textViewName.text = listUsers[position].name
        holder.textViewEmail.text = listUsers[position].email
        holder.textViewPassword.text = listUsers[position].password
    }

    override fun getItemCount(): Int {
        Log.e("Asmita ==> "," Adapter =  " + listUsers.size)
        return listUsers.size
    }


    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewName: TextView
        var textViewEmail: TextView
        var textViewPassword: TextView

        init {
            textViewName = view.findViewById<View>(R.id.textViewName) as TextView
            textViewEmail = view.findViewById<View>(R.id.textViewEmail) as TextView
            textViewPassword = view.findViewById<View>(R.id.textViewPassword) as TextView
        }
    }


}