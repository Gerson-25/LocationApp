package com.example.locationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.locationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biding: ActivityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)

        biding.button.setOnClickListener{
            Toast.makeText(this, "obteniendo la ubicacion...", Toast.LENGTH_SHORT).show()
        }

    }


}
