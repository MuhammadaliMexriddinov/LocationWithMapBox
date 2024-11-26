package uz.macdroid.mapboxwithandroid

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import java.lang.ref.WeakReference

class LocationPermissionHelper(private val activityRef: WeakReference<Activity>) {

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 1234
    }

    fun checkPermissions(callback: () -> Unit) {
        val activity = activityRef.get() ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION_PERMISSION
            )
        } else {
            callback()
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            val activity = activityRef.get() ?: return
            (activity as? PermissionsCallback)?.onPermissionGranted()
        }
    }




    interface PermissionsCallback {
        fun onPermissionGranted()
    }
}
