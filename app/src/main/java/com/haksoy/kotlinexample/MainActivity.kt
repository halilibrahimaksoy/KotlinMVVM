package com.haksoy.kotlinexample

import Name
import User
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haksoy.kotlinexample.databinding.ActivityMainBinding
import com.haksoy.kotlinexample.ui.userlist.UserListAdapter
import com.haksoy.kotlinexample.ui.userlist.UserListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binging: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    }


}
