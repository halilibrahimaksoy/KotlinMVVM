package com.haksoy.kotlinmvvm.ui.userdetail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.haksoy.kotlinmvvm.R
import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.databinding.FragmentUserDetailsBinding
import com.haksoy.kotlinmvvm.ui.userlist.UserListViewModel

class UserDetailsFragment : Fragment() {

    private val viewModel: UserListViewModel by activityViewModels()
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


        binding.tvName.setText(user.name)
        binding.tvSurname.setText(user.surname)
        binding.tvEmail.setText(user.email)
        binding.tvMobile.setText(user.number)
        binding.tvCompany.setText(user.company_name)
        binding.tvDepartment.setText(user.department)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.menu.clear()
        binding.toolbar.inflateMenu(R.menu.user_detail_menu)
        val newItem = binding.toolbar.menu.findItem(R.id.action_save)
        newItem.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
            updateUser()
            viewModel.editUser(user)
            true
        })
    }

    private fun updateUser() {
        user.name = binding.tvName.text.toString()
        user.surname = binding.tvSurname.text.toString()
        user.company_name = binding.tvCompany.text.toString()
        user.department = binding.tvDepartment.text.toString()
        user.email = binding.tvEmail.text.toString()
        user.number = binding.tvMobile.text.toString()
    }
}