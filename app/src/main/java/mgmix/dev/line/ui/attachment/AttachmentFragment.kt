package mgmix.dev.line.ui.attachment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mgmix.dev.line.databinding.FragmentAttachmentBinding


class AttachmentFragment : Fragment() {

    private lateinit var binding: FragmentAttachmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttachmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun FragmentAttachmentBinding.set() {

    }

}