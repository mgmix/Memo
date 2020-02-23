package mgmix.dev.line.ui.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import mgmix.dev.line.BuildConfig
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentDetailBinding
import mgmix.dev.line.databinding.HeaderDetailBinding
import mgmix.dev.line.ext.toast
import mgmix.dev.line.ui.OnBackPressedListener
import mgmix.dev.line.ui.attachment.AttachmentFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailFragment : DaggerFragment(), ViewModelStoreOwner, OnBackPressedListener {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var attachmentBehavior: BottomSheetBehavior<FrameLayout>

    @Inject
    lateinit var viewModelFactory: SharedViewModelFactory

    private val viewModel: ShareViewModel by viewModels { viewModelFactory }
    private var currentPhotoPath: String = ""

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

        attach.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.ic_attach_file_black_24dp)
                .setTitle("이미지 추가")
                .setItems(
                    R.array.attachments
                ) { _, which ->
                    when (which) {
                        0 -> requestCamera()
                        1 -> requestAlbumPick()
                        2 -> requestUrlImage()
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
        when (requestCode) {
            REQUEST_CAMERA -> {
                if( resultCode == Activity.RESULT_OK) {
                    viewModel.addAttachment(currentPhotoPath, "")
                }
            }
            REQUEST_ALBUM -> {
                if (resultCode == Activity.RESULT_OK){
                    viewModel.addAttachment(data?.data.toString(), "")
                }
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.mode.value == true) viewModel.addNote()
        parentFragmentManager.popBackStack()
    }

    private fun requestCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(requireContext().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (e: IOException) {
                    null
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    photoFile?.also {
                        val photoUri: Uri = FileProvider.getUriForFile(
                            requireContext(),
                            "${BuildConfig.APPLICATION_ID}.fileProvider",
                            it
                        )
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(intent, REQUEST_CAMERA)
                    }
                } else {
                    val photoUri = Uri.fromFile(photoFile)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    private fun requestAlbumPick(){
        Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(this, REQUEST_ALBUM)
        }
    }

    private fun requestUrlImage(){
        val editText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("URL 주소 입력")
            .setView(editText)
            .setPositiveButton(
                "확인"
            ) { _, _ ->
                val url = editText.text.toString()
                if (URLUtil.isValidUrl(url)) {
                    viewModel.addAttachment(url, "")
                } else {
                    requireContext().toast("유효하지 않은 URL 입니다.")
                }
            }
            .setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(Date())
        val storageDir = File("${requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        if (!storageDir.exists()) storageDir.mkdir()
        return File.createTempFile(
            "LINE_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }


    companion object {
        private const val TAG = "DetailFragment"
        private const val REQUEST_CAMERA = 1000
        private const val REQUEST_ALBUM = 1001
    }
}