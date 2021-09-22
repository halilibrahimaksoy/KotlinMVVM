package com.haksoy.kotlinmvvm.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.databinding.UserItemBinding

class UserListAdapter(private val listener: UserItemListener) :
    RecyclerView.Adapter<UserViewHolder>() {

    interface UserItemListener {
        fun onClickedUser(user: User)
        fun onLongClickedUser(user: User)
    }

    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserItemBinding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(items[position])
}

class UserViewHolder(
    private val itemBinding: UserItemBinding,
    private val listener: UserListAdapter.UserItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener, View.OnLongClickListener {

    private lateinit var user: User

    init {
        itemBinding.root.setOnClickListener(this)
        itemBinding.root.setOnLongClickListener(this)
    }

    fun bind(item: User) {
        this.user = item
        itemBinding.name.text = "${item.name} ${item.surname}"
        itemBinding.speciesAndStatus.text = item.email
    }

    override fun onClick(v: View?) {
        listener.onClickedUser(user)
    }

    override fun onLongClick(v: View?): Boolean {
        listener.onLongClickedUser(user)
        return true
    }
}