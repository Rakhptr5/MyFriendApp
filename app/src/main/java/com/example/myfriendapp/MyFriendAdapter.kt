package com.example.myfriendapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
class MyFriendAdapter(private val context: Context, private val myFriendList: ArrayList<MyFriend>) :
    RecyclerView.Adapter<MyFriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_friends_item, parent, false))


    override fun getItemCount(): Int {
        return myFriendList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(myFriendList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var txtFriendName: TextView = itemView.findViewById(R.id.txtFriendName)
        private var txtFriendEmail: TextView = itemView.findViewById(R.id.txtFriendEmail)
        private var txtFriendAddress: TextView = itemView.findViewById(R.id.txtFriendAddress)
        private var txtFriendPhone: TextView = itemView.findViewById(R.id.txtFriendTelp)

        fun bindItem(item: MyFriend) {
            txtFriendName.text = item.nama
            txtFriendEmail.text = item.email
            txtFriendAddress.text = item.alamat
            txtFriendPhone.text = item.telp
        }
    }
}