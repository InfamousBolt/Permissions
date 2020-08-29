package com.example.permissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRequest.setOnClickListener {
            requestPermissions()
        }

    }
    private fun hasLocationPermission()=
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
    private fun hasInternetPermission()=
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED
    private fun hasBackgroundLocationPermission()=
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)==PackageManager.PERMISSION_GRANTED
    private fun hasWriteStoragePermission()=
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED

    private fun requestPermissions(){
        var permissionsToRequest= mutableListOf<String>();
        if(!hasLocationPermission()){
            permissionsToRequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!hasBackgroundLocationPermission()){
            permissionsToRequest.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if(!hasInternetPermission()){
            permissionsToRequest.add(android.Manifest.permission.INTERNET)
        }
        if(!hasWriteStoragePermission()){
            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(permissionsToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionsToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    Log.d("Request","${permissions[i]} granted" )
                }
                else if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                    Log.d("Request","${permissions[i]} not granted with result ${grantResults[i]}")
                }
            }
        }
    }


}