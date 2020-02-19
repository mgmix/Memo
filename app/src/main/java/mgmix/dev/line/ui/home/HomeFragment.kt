package mgmix.dev.line.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

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
        val homeListAdapter =  HomeListAdapter {
            Log.d(TAG, "it: ${it.id} ::  ${it.title} ")
        }
        with(itemList) {
            adapter = homeListAdapter
        }

        viewModel.noteList.observe(viewLifecycleOwner) {
            homeListAdapter.items = it
        }

        viewModel.getNotes()
    }



    companion object {
        private const val TAG = "HomeFragment"
    }


}