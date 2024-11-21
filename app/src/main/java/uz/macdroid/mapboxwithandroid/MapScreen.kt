package uz.macdroid.mapboxwithandroid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.ar.core.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import uz.macdroid.mapboxwithandroid.databinding.ScreenMapBinding

class MapScreen : Fragment(R.layout.screen_map) {


    private  val binding by viewBinding(ScreenMapBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)

        }
    }
}