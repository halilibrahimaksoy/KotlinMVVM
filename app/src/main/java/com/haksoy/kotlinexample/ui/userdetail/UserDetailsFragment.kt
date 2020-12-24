package com.haksoy.kotlinexample.ui.userdetail

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.haksoy.kotlinexample.R
import com.haksoy.kotlinexample.databinding.FragmentUserDetailsBinding
import kotlinx.android.synthetic.main.user_item.*

class UserDetailsFragment : Fragment() {
    private lateinit var user: User
    lateinit var binding: FragmentUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.get("selectedUser")?.let { user = it as User }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)


        Glide.with(binding.root).load(user.picture.large).transform(CircleCrop())
            .into(binding.userImage)
        binding.tvFullName.text = user.name.first + user.name.last
        binding.tvEmail.text = user.email
        binding.tvMobile.text = user.phone
        binding.tvCell.text = user.cell

        return binding.root
    }

}