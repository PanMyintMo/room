package com.pan.room_database.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pan.room_database.R
import com.pan.room_database.databinding.CustomRowBinding
import com.pan.room_database.fragment.ListFragmentDirections
import com.pan.room_database.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CustomRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.txtNo.text = currentItem.id.toString()
        holder.binding.txtFirstName.text = currentItem.firstName
        holder.binding.txtLastName.text = currentItem.lastName
        holder.binding.txtAge.text = currentItem.age.toString()

        holder.binding.customRow.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}