package mgmix.dev.line.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeHeader.setHeader()
        binding.initView()
    }

    private fun HeaderHomeBinding.setHeader() {
        addNote.setOnClickListener {
            activity.replace(R.id.container, DetailFragment())?.addToBackStack(null)?.commit()
        }

    }

    private fun FragmentHomeBinding.initView() {
        val homeListAdapter =  HomeListAdapter {
            Log.d(TAG, "KeyId: ${it.keyId} ")
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment::class.java, bundleOf("keyId" to it.keyId))
                .addToBackStack(null)
                .commit()
        }

        with(itemList) {
            adapter = homeListAdapter
        }

        viewModel.noteList.observe(viewLifecycleOwner) {
            homeListAdapter.items = it
            showEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.getNotes()
    }



    companion object {
        private const val TAG = "HomeFragment"
    }


}