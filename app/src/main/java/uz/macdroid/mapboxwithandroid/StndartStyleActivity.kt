//package uz.macdroid.mapboxwithandroid
//
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.location.Location
//import android.os.Bundle
//import android.telecom.Call
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.google.android.gms.common.api.Response
//import com.google.android.gms.location.LocationServices
//import com.mapbox.geojson.Feature
//import com.mapbox.geojson.LineString
//import com.mapbox.geojson.Point
//import com.mapbox.maps.CameraOptions
//import com.mapbox.maps.MapView
//import com.mapbox.maps.Style
//import com.mapbox.maps.extension.style.layers.addLayer
//import com.mapbox.maps.extension.style.layers.generated.LineLayer
//import com.mapbox.maps.extension.style.layers.generated.SymbolLayer
//import com.mapbox.maps.extension.style.layers.getLayer
//import com.mapbox.maps.extension.style.sources.addSource
//import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource
//import com.mapbox.maps.extension.style.sources.getSourceAs
//import com.mapbox.maps.plugin.gestures.addOnMapClickListener
//import com.mapbox.turf.TurfMeasurement
//import java.lang.ref.WeakReference
//
//
//class StndartStyleActivity : AppCompatActivity() {
//    private lateinit var mapView: MapView
//    private lateinit var locationPermissionHelper: LocationPermissionHelper
//    private val points = listOf(
//        Point.fromLngLat(69.2173, 41.3152),
//        Point.fromLngLat(69.2874, 41.2995),
//        Point.fromLngLat(69.2475, 41.3111)
//    )
//
//    private val icons = listOf(
//        R.drawable.firstmark,
//        R.drawable.secondmark,
//        R.drawable.thirdmark
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_map)
//        mapView = findViewById(R.id.mapView)
//
//        locationPermissionHelper = LocationPermissionHelper(WeakReference(this))
//        locationPermissionHelper.checkPermissions {
//            initializeMap()
//        }
//    }
//
//    private fun initializeMap() {
//        mapView.getMapboxMap().apply {
//            loadStyleUri(Style.MAPBOX_STREETS) { style ->
//                addMarkersToMap(style)
//                getCurrentLocation {
//                    setCamera(
//                        CameraOptions.Builder()
//                            .center(Point.fromLngLat(it.longitude, it.latitude))
//                            .zoom(14.0)
//                            .build()
//                    )
//                }
//            }
//        }
//    }
//
//    private fun addMarkersToMap(style: Style) {
//        points.forEachIndexed { index, point ->
//            val iconDrawable = ContextCompat.getDrawable(this, icons[index])
//            val bitmap = BitmapUtils.getBitmapFromDrawable(iconDrawable)
//            bitmap?.let {
//                style.addImage("marker-$index", it)
//             //   style.addSource(GeoJsonSource("source-$index", Feature.fromGeometry(point)))
//                style.addLayer(
//                    SymbolLayer("layer-$index", "source-$index")
////                        .withProperties(
////                            PropertyFactory.iconImage("marker-$index"),
////                            PropertyFactory.iconSize(1.5f)
////                        )
//                )
//            }
//        }
//        setupMapClickListener()
//    }
//
//    private fun setupMapClickListener() {
//        mapView.getMapboxMap().addOnMapClickListener { point ->
//            points.forEach { destination ->
//                if (TurfMeasurement.distance(point, destination) < 0.05) {
//                    drawRoute(destination)
//                    return@addOnMapClickListener true
//                }
//            }
//            false
//        }
//    }
//
//    private fun drawRoute(destination: Point) {
//        getCurrentLocation { location ->
//            val origin = Point.fromLngLat(location.longitude, location.latitude)
//
//            val client = MapboxDirections.builder()
//                .origin(origin)
//                .destination(destination)
//                .profile(DirectionsCriteria.PROFILE_DRIVING)
//                .accessToken(getString(R.string.mapbox_access_token))
//                .build()
//
//            client.enqueueCall(object : Callback<DirectionsResponse> {
//                override fun onResponse(
//                    call: Call<DirectionsResponse>,
//                    response: Response<DirectionsResponse>
//                ) {
//                    response.body()?.routes()?.firstOrNull()?.let { route ->
//                        addRouteToMap(route)
//                    }
//                }
//
//                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
//                    Toast.makeText(this@StndartStyleActivity, "Route error: ${t.message}", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }
//
//    private fun addRouteToMap(route: DirectionsRoute) {
//        mapView.getMapboxMap().getStyle { style ->
//            val source = style.getSourceAs<GeoJsonSource>("route-source")
//                ?: GeoJsonSource("route-source").also { style.addSource(it) }
//
//            source.setGeoJson(LineString.fromPolyline(route.geometry()!!, 6))
//            if (style.getLayer("route-layer") == null) {
//                style.addLayer(
//                    LineLayer("route-layer", "route-source").withProperties(
//                        PropertyFactory.lineColor(Color.RED),
//                        PropertyFactory.lineWidth(5.0f)
//                    )
//                )
//            }
//        }
//    }
//
//    private fun getCurrentLocation(callback: (Location) -> Unit) {
//        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                if (location != null) {
//                    callback(location)
//                }
//            }
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//        }
//    }
//
//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
//    }
//}
//
//
