package mgmix.dev.line

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mgmix.dev.line.databinding.ActivityMainBinding
import mgmix.dev.line.databinding.HeaderMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainListAdapter by lazy {
        MainListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            set()
            header.set()
        }
    }

    private fun HeaderMainBinding.set() {

    }

    private fun ActivityMainBinding.set() {
        with(itemList) {
            adapter = mainListAdapter
        }
    }





}
