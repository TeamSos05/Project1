package team.tiktok.tiktokapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import team.tiktok.tiktokapp.R
import team.tiktok.tiktokapp.databinding.ActivityMainBinding
import team.tiktok.tiktokapp.databinding.FragmentSplashScreenBinding

class SplashActivity : AppCompatActivity() {
        lateinit var binding  : FragmentSplashScreenBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = FragmentSplashScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var handler = Handler(Looper.myLooper()!!)
            handler.postDelayed(Runnable {
               val intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
            },3000)

        }

    }