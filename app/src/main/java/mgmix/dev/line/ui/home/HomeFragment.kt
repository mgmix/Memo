package mgmix.dev.line.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.DaggerFragment
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentHomeBinding
import mgmix.dev.line.databinding.HeaderHomeBinding
import mgmix.dev.line.ext.replace
import mgmix.dev.line.ui.detail.DetailFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var homeListAdapter: HomeListAdapter
//    private val homeListAdapter by lazy {
//        HomeListAdapter()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeHeader.setHeader()
        binding.setView()
        return binding.root
    }

    private fun HeaderHomeBinding.setHeader() {
        addNote.setOnClickListener {
            activity.replace(R.id.container, DetailFragment())?.addToBackStack(null)?.commit()
        }
    }

    private fun FragmentHomeBinding.setView() {
        viewModel = ViewModelProviders.of(
            this@HomeFragment, viewModelFactory
        )[HomeViewModel::class.java]


        with(itemList) {
            adapter = homeListAdapter
        }

        homeListAdapter.items = viewModel.getNotes
    }



    companion object {
        private const val TAG = "HomeFragment"
    }


}