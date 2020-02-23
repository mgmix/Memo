package mgmix.dev.line.ui.attachment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentAttachmentBinding
import mgmix.dev.line.ui.detail.ShareViewModel


class AttachmentFragment : DaggerFragment() {

    private lateinit var binding: FragmentAttachmentBinding

    private val mAdapter by lazy {
        AttachmentAdapter {
            it.id?.let { attachId ->
                viewModel.removeAttachment(attachId)
            }
            viewModel.attachments.remove(it)
        }
    }

    private val viewModel: ShareViewModel by viewModels({ requireParentFragment() })

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
            count.text = getString(R.string.attach_count, it.count())
            if (it.isEmpty()) showEmpty.visibility = View.VISIBLE else showEmpty.visibility = View.GONE
        })

        viewModel.mode.observe(viewLifecycleOwner, Observer {
            mAdapter.mode = it
        })

    }
}