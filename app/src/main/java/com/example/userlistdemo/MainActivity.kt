package com.example.userlistdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.userlistdemo.databinding.ActivityMainBinding
import com.example.userlistdemo.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var adapter: UserAdapter

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initViews()
        observeData()
    }

    private fun observeData() {
        viewModel.showErrorToast.observe(this, EventObserver {
            Toast.makeText(this, "Error occur", Toast.LENGTH_SHORT).show()
        })
        viewModel.userList.observe(this, {
            Log.d("FAFA","size ${it.size}")
            adapter.updateItems(it)
        })
    }

    private fun initViews() {
        binding.buttonReload.setOnClickListener {
            viewModel.fetchUsers()
        }
        binding.recyclerView.adapter ?: run {
            binding.recyclerView.adapter = adapter
        }
    }
}