package com.haksoy.kotlinmvvm.ui.userdetail

import com.haksoy.kotlinmvvm.data.entiries.User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.haksoy.kotlinmvvm.databinding.FragmentUserDetailsBinding

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


        binding.tvFullName.text = "${user.name} ${user.surname}"
        binding.tvEmail.text = user.email
        binding.tvMobile.text = user.number
        binding.tvCompany.text = user.company_name
        binding.tvDepartment.text = user.department

        return binding.root
    }

}