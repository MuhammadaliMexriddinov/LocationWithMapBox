package uz.macdroid.mapboxwithandroid

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.Manifest
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.annotation.AnnotationPlugin
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import uz.macdroid.mapboxwithandroid.databinding.ScreenMapBinding

class MapScreen : Fragment(R.layout.screen_map) {


    private lateinit var pointAnnotationManager: PointAnnotationManager
    private  val binding by viewBinding(ScreenMapBinding::bind)
    private lateinit var mapboxMap: MapboxMap



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapboxMap = binding.mapView.getMapboxMap()

        setupMap()





    }

    private fun setupMap() {
        mapboxMap.loadStyleUri(Style.MAPBOX_STREETS) {

            getCurrentLocation { location ->
                mapboxMap.setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(location.longitude, location.latitude))
                        .zoom(12.0)
                        .build()
                )


            }
        }
    }


    private fun getCurrentLocation(callback: (Location) -> Unit) {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    callback(location)
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 2004
    }

}