package com.haksoy.kotlinmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.haksoy.kotlinmvvm.databinding.ActivityMainBinding
import com.haksoy.kotlinmvvm.ui.userlist.UserListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binging: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        viewModel.messages.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


}
