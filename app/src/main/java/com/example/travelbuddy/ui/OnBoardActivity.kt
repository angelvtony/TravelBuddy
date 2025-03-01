package com.example.travelbuddy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.travelbuddy.R
import com.example.travelbuddy.databinding.ActivityOnBoardBinding

class OnBoardActivity : AppCompatActivity() {
    lateinit var adapter: FragmentAdapter
    private lateinit var binding: ActivityOnBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(Fragment1())
        adapter.addFragment(Fragment2())
        adapter.addFragment(Fragment3())

        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.viewPager2.adapter = adapter
    }
}