package mgmix.dev.line.ui.attachment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.android.support.DaggerFragment
import mgmix.dev.line.databinding.FragmentAttachmentBinding
import mgmix.dev.line.ui.detail.ShareViewModel
import mgmix.dev.line.ui.detail.SharedViewModelFactory
import javax.inject.Inject


class AttachmentFragment : DaggerFragment() {

    private lateinit var binding: FragmentAttachmentBinding

    private val mAdapter by lazy {
        AttachmentAdapter()
    }

    private val viewModel: ShareViewModel by viewModels ({requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttachmentBinding.inflate(inflater, container, false)
        binding.initView(viewModel)
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun FragmentAttachmentBinding.initView(viewModel: ShareViewModel) {
        with(bottomItemList) {
            adapter = mAdapter
        }

        viewModel.attachments.observe(viewLifecycleOwner, Observer {
            mAdapter.items = it
        })

    }

}