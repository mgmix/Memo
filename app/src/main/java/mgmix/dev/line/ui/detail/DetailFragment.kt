package mgmix.dev.line.ui.detail

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentDetailBinding
import mgmix.dev.line.databinding.HeaderDetailBinding
import mgmix.dev.line.ext.toast
import mgmix.dev.line.ui.OnBackPressedListener
import mgmix.dev.line.ui.attachment.AttachmentFragment
import java.util.*
import javax.inject.Inject

class DetailFragment : DaggerFragment(),ViewModelStoreOwner, OnBackPressedListener {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var attachmentBehavior: BottomSheetBehavior<FrameLayout>

    @Inject
    lateinit var viewModelFactory: SharedViewModelFactory

    private val viewModel: ShareViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailHeader.setHeader()
        binding.initView(viewModel)
    }

    private fun HeaderDetailBinding.setHeader() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        edit.setOnClickListener {
            viewModel.saveMode(mode = true)
        }

        save.setOnClickListener {
            viewModel.addNote()
            viewModel.saveMode(mode = false)
        }

        delete.setOnClickListener {
            viewModel.deleteNote()
            viewModel.saveMode(false)
            requireContext().toast("삭제 되었습니다.")
            onBackPressed()
        }

        // Temp: move Floating or bottom fragment
        attach.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.ic_attach_file_black_24dp)
                .setTitle("이미지 추가")
                .setItems(
                    R.array.attachments
                ) { dialog, which ->
                    when (which) {
                        0 -> {
                            Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                                resolveActivity(requireContext().packageManager)?.let {
                                    startActivityForResult(this, REQUEST_CAMERA)
                                }
                            }
                        }
                        1 -> {
                            Intent(Intent.ACTION_PICK).apply {
                                type = MediaStore.Images.Media.CONTENT_TYPE
                                startActivityForResult(this, REQUEST_ALBUM)
                            }
                        }
                        2 -> {
                            // TextBox Popup Url Link
                        }
                    }
                }
                .show()
        }
    }

    private fun FragmentDetailBinding.initView(viewModel: ShareViewModel) {
        attachmentBehavior = BottomSheetBehavior.from(container)
        attachmentBehavior.addBottomSheetCallback(bottomSheetCallback)

        val tag = "attachment"
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            childFragmentManager.beginTransaction().add(
                container.id,
                AttachmentFragment(), tag
            ).commit()
        }

        arguments?.getLong("keyId")?.let {
            viewModel.saveMode(mode = false)
            viewModel.setKeyId(it)
            viewModel.getNoteDetail()
        } ?: run {
            viewModel.saveMode(mode = true)
            viewModel.setKeyId(Calendar.getInstance().timeInMillis)
        }

        viewModel.mode.observe(this@DetailFragment, Observer {
            if (it) binding.onEditMode() else binding.onViewMode()
        })

    }

    private fun FragmentDetailBinding.onViewMode() {
        title.isEnabled = false
        contents.isEnabled = false
        binding.detailHeader.save.visibility = View.GONE
        binding.detailHeader.attach.visibility = View.GONE
        binding.detailHeader.edit.visibility = View.VISIBLE
        binding.detailHeader.delete.visibility = View.VISIBLE
    }

    private fun FragmentDetailBinding.onEditMode() {
        title.requestFocus()
        title.isEnabled = true
        contents.isEnabled = true
        binding.detailHeader.save.visibility = View.VISIBLE
        binding.detailHeader.attach.visibility = View.VISIBLE
        binding.detailHeader.edit.visibility = View.GONE
        binding.detailHeader.delete.visibility = View.GONE
    }


    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {

            REQUEST_CAMERA -> {
                Log.e(TAG, "camera data: ${data?.data}")
            }

            REQUEST_ALBUM -> {
                Log.e(TAG, "album data: ${data?.data}")
                viewModel.addAttachment(data?.data.toString(), "test")

            }

        }
    }

    override fun onBackPressed() {
        if (viewModel.mode.value == true) viewModel.addNote()
        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val TAG = "DetailFragment"
        private const val REQUEST_CAMERA = 1000
        private const val REQUEST_ALBUM =  1001
    }
}