package mgmix.dev.line.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import mgmix.dev.line.R
import mgmix.dev.line.databinding.ActivityMainBinding
import mgmix.dev.line.ext.replace
import mgmix.dev.line.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        requestPhotoPermission()
        binding.apply {
            replace(container.id, HomeFragment())
        }
    }

    private fun requestPhotoPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_CAMERA)
        }  else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_WRITE_EXT_STORAGE)
        }
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        val list = supportFragmentManager.fragments
        for(i in list) {
            if (i is OnBackPressedListener) {
                (i as OnBackPressedListener).onBackPressed()
            }
        }
    }

    companion object {
        const val PERMISSION_CAMERA = 100
        const val PERMISSION_WRITE_EXT_STORAGE = 101
    }

}
