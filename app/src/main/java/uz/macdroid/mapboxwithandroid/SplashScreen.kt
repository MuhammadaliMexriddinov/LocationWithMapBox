package uz.macdroid.mapboxwithandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.macdroid.mapboxwithandroid.databinding.ScreenMapBinding
import uz.macdroid.mapboxwithandroid.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {

    private  val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            lifecycleScope.launch {
                delay(2000)
                //findNavController().navigate(R.id.mapScreen)
                val intent = Intent(requireContext(), StandardStyleActivity::class.java)
                startActivity(intent)
            }
        }

    }
}