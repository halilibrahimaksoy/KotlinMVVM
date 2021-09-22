package com.haksoy.kotlinmvvm.ui.userlist

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haksoy.kotlinmvvm.R
import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.databinding.FragmentUserListBinding


class UserListFragment : Fragment(), UserListAdapter.UserItemListener {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var adapter: UserListAdapter

    private val viewModel: UserListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserListBinding.inflate(inflater, container, false)
        Log.d("UserListFragment", "onCreateView")
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("UserListFragment", "onViewCreated")

        setupRecyclerView()
        observe()

    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter(this)
        binding.usrsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.usrsRv.adapter = adapter

        binding.usrsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMore()
                }
            }
        })

    }

    private fun observe() {
        viewModel.mUsers.observe(viewLifecycleOwner, Observer<List<User>> {
            adapter.setItems(ArrayList(it))
        })


    }

    override fun onClickedUser(user: User) {
        findNavController().navigate(
            R.id.action_userListFragment_to_userDetailsFragment, bundleOf("selectedUser" to user)
        )
    }

    override fun onLongClickedUser(user: User) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Are want to sure to delete this contact").setPositiveButton("Delete",
            DialogInterface.OnClickListener { dialog, which ->
                viewModel.deleteUser(user.id)
            }).setCancelable(true)
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("UserListFragment", "onResume")
        viewModel.loadUsers(count = 10)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.menu.clear()
        viewModel.filterNameForCLF.postValue("")
        binding.toolbar.inflateMenu(R.menu.main_menu)
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterNameForCLF.postValue(newText)
                return true
            }
        })

        val newItem = binding.toolbar.menu.findItem(R.id.action_new)
        newItem.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
            viewModel.addNewUser()
            true
        })
    }
}