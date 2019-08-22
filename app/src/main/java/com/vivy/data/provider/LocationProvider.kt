package com.vivy.data.provider

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.vivy.BuildConfig
import com.vivy.R
import com.vivy.ui.home.HomeActivity
import com.vivy.ui.search.LatLongListener

class LocationProvider(private val context: HomeActivity) {
    lateinit var latLongListener: LatLongListener
    private val PERMISSIONS_REQUEST_CODE = 99
    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val TAG = "LocationProvider"

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        fusedLocationClient.lastLocation
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful && task.result != null) {
                        if (task.isSuccessful && task.result != null) {
                            latLongListener.onLatLongReceived(task.result!!.latitude, task.result!!.longitude)
                        } else {
                            showSnackbar(R.string.no_location_detected)
                        }
                    }
                }
    }

    private fun showSnackbar(snackStrId: Int, actionStrId: Int = 0, listener: View.OnClickListener? = null) {
        val snackbar = Snackbar.make(context.findViewById(android.R.id.content), context.getString(snackStrId),
                Snackbar.LENGTH_INDEFINITE)
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(context.getString(actionStrId), listener)
        }
        snackbar.show()
    }


    fun checkPermissions() =
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSIONS_REQUEST_CODE)
    }

    fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            showSnackbar(R.string.permission_rationale, android.R.string.ok, View.OnClickListener {
                startLocationPermissionRequest()
            })
        } else {
            startLocationPermissionRequest()
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.")
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()
                else -> {
                    showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                            View.OnClickListener {
                                val intent = Intent().apply {
                                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                    data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                context.startActivity(intent)
                            })
                }
            }
        }
    }

    fun setListener(latLongListenere: LatLongListener) {
        latLongListener = latLongListenere
    }

}
