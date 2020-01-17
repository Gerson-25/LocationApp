package com.example.locationapp

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import com.example.locationapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val handler = Handler()
    lateinit var location1: FusedLocationProviderClient

    lateinit var database: FirebaseDatabase
    lateinit var databaseRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biding: ActivityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        biding.button.setOnClickListener{
            getLocation()
        }


        /*var database = FirebaseDatabase.getInstance()
        var databaseRef = database.reference*/
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this,
                "tenemos accesso... obteniendo la ubicacion...",
                Toast.LENGTH_SHORT
            ).show()


        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
        location1 = LocationServices.getFusedLocationProviderClient(this)

        location1.lastLocation.addOnSuccessListener {
                handler.postDelayed(
                    {runOnUiThread{
                        var latitude = it.latitude
                        var longitude = it.longitude

                        Toast.makeText(
                            this,
                            "latitud = ${latitude}, longitud = ${longitude}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    },5000)

                    database = FirebaseDatabase.getInstance()
                    databaseRef = database.getReference("location")
                }
        }
    }
