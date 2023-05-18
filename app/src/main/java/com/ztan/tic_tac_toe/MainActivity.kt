package com.ztan.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ztan.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindButtons()
        Log.d("EA", "Hi")
    }

    private fun bindButtons() = with(binding) {
        buttonSinglePlayer.setOnClickListener {
            val intent = Intent(this@MainActivity, SinglePlayerActivity::class.java)
            startActivity(intent)
        }
        buttonMultiPlayer.setOnClickListener { Toast.makeText(this@MainActivity, "Multi Player", Toast.LENGTH_SHORT).show() }
    }
}