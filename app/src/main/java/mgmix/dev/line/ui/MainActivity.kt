package mgmix.dev.line.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        binding.apply {
            replace(container.id, HomeFragment())
        }
    }

}
