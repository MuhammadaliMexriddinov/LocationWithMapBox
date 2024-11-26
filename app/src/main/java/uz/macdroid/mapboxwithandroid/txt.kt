//package uz.macdroid.mapboxwithandroid
//
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.health.connect.datatypes.ExerciseRoute
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.google.android.gms.location.LocationServices
//import com.mapbox.android.gestures.MoveGestureDetector
//import com.mapbox.geojson.Feature
//import com.mapbox.geojson.LineString
//import com.mapbox.geojson.FeatureCollection
//import com.mapbox.geojson.Point
//import com.mapbox.maps.CameraOptions
//import com.mapbox.maps.ImageHolder
//import com.mapbox.maps.MapView
//import com.mapbox.maps.Style
//import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.interpolate
//import com.mapbox.maps.extension.style.layers.addLayer
//import com.mapbox.maps.extension.style.layers.generated.LineLayer
//import com.mapbox.maps.extension.style.layers.generated.SymbolLayer
//import com.mapbox.maps.extension.style.layers.getLayer
//import com.mapbox.maps.extension.style.sources.addSource
//import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource
//import com.mapbox.maps.extension.style.sources.getSourceAs
//import com.mapbox.maps.plugin.LocationPuck2D
//import com.mapbox.maps.plugin.PuckBearing
//import com.mapbox.maps.plugin.gestures.OnMoveListener
//import com.mapbox.maps.plugin.gestures.addOnMapClickListener
//import com.mapbox.maps.plugin.gestures.gestures
//import com.mapbox.maps.plugin.locationcomponent.LocationComponentPlugin
//import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
//import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
//import com.mapbox.maps.plugin.locationcomponent.location
//import com.mapbox.turf.TurfMeasurement
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import uz.macdroid.mapboxwithandroid.MapScreen.Companion.LOCATION_PERMISSION_REQUEST_CODE
//import java.lang.ref.WeakReference
//
//class StandardStyleActivity : AppCompatActivity() {
//    private lateinit var locationPermissionHelper: LocationPermissionHelper
//
//    private val onIndicatorBearingChangedListener = OnIndicatorBearingChangedListener {
//        mapView.mapboxMap.setCamera(CameraOptions.Builder().bearing(it).build())
//    }
//
//    private val onIndicatorPositionChangedListener = OnIndicatorPositionChangedListener {
//        mapView.mapboxMap.setCamera(CameraOptions.Builder().center(it).build())
//        mapView.gestures.focalPoint = mapView.mapboxMap.pixelForCoordinate(it)
//    }
//
//    private val onMoveListener = object : OnMoveListener {
//        override fun onMoveBegin(detector: MoveGestureDetector) {
//            onCameraTrackingDismissed()
//        }
//
//        override fun onMove(detector: MoveGestureDetector): Boolean {
//            return false
//        }
//
//        override fun onMoveEnd(detector: MoveGestureDetector) {}
//    }
//    private lateinit var mapView: MapView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mapView = MapView(this)
//        setContentView(mapView)
//        locationPermissionHelper = LocationPermissionHelper(WeakReference(this))
//        locationPermissionHelper.checkPermissions {
//            onMapReady()
//        }
//    }
//
//    private fun onMapReady() {
//        mapView.mapboxMap.setCamera(
//            CameraOptions.Builder()
//                .zoom(14.0)
//                .build()
//        )
//        mapView.mapboxMap.loadStyle(
//            Style.STANDARD
//        ) {
//            initLocationComponent()
//            setupGesturesListener()
//        }
//
//    }
//
//    private fun setupGesturesListener() {
//        mapView.gestures.addOnMoveListener(onMoveListener)
//    }
//
//    private fun initLocationComponent() {
//        val locationComponentPlugin = mapView.location
//        locationComponentPlugin.updateSettings {
//            puckBearing = PuckBearing.COURSE
//            puckBearingEnabled = true
//            enabled = true
//            locationPuck = LocationPuck2D(
//                bearingImage = ImageHolder.from(R.drawable.currentlocation),
//                shadowImage = ImageHolder.from(R.drawable.currentlocation),
//                scaleExpression = interpolate {
//                    linear()
//                    zoom()
//                    stop {
//                        literal(0.0)
//                        literal(0.6)
//                    }
//                    stop {
//                        literal(20.0)
//                        literal(1.0)
//                    }
//                }.toJson()
//            )
//        }
//        locationComponentPlugin.addOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
//        locationComponentPlugin.addOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
//    }
//
//    private fun onCameraTrackingDismissed() {
//        Toast.makeText(this, "onCameraTrackingDismissed", Toast.LENGTH_SHORT).show()
//        mapView.location
//            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
//        mapView.location
//            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
//        mapView.gestures.removeOnMoveListener(onMoveListener)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView.location
//            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
//        mapView.location
//            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
//        mapView.gestures.removeOnMoveListener(onMoveListener)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        locationPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//
//}
//
//
