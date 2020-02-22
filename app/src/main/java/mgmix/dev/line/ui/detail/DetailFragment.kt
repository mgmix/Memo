package mgmix.dev.line.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentDetailBinding
import mgmix.dev.line.databinding.HeaderDetailBinding
import mgmix.dev.line.repository.data.model.NoteItem
import mgmix.dev.line.ui.OnBackPressedListener
import mgmix.dev.line.ui.attachment.AttachmentFragment
import java.util.*
import javax.inject.Inject

class DetailFragment : DaggerFragment(), OnBackPressedListener {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var attachmentBehavior: BottomSheetBehavior<FrameLayout>

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }


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
            onBackPressed()
            viewModel.saveMode(mode = false)
        }

        // Temp: move Floating or bottom fragment
        attach.setOnClickListener {
                    AlertDialog.Builder(requireContext())
            .setIcon(R.drawable.ic_attach_file_black_24dp)
            .setTitle("이미지 추가")
            .setItems(R.array.attachments
            ) { dialog, which ->
                Log.d(TAG, "dialog : $which")
            }
            .show()
        }
    }

    private fun FragmentDetailBinding.initView(viewModel: DetailViewModel) {
        attachmentBehavior = BottomSheetBehavior.from(container)
        attachmentBehavior.addBottomSheetCallback(bottomSheetCallback)

        val tag = "attachment"
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            childFragmentManager.beginTransaction().add(container.id,
                AttachmentFragment(), tag ).commit()
        }

        arguments?.getLong("keyId")?.let {
            viewModel.saveMode(mode = false)
            viewModel.setKeyId(it)
            viewModel.getNoteDetail()
        } ?: viewModel.saveMode(mode = true)

        viewModel.mode.observe(this@DetailFragment, Observer {
            if (it) binding.onEditMode() else binding.onViewMode()
        })

    }

    private fun FragmentDetailBinding.onViewMode() {
        title.isEnabled = false
        contents.isEnabled = false
        binding.detailHeader.save.visibility = View.GONE
        binding.detailHeader.attach.visibility = View.GONE
        binding.detailHeader.delete.visibility = View.VISIBLE
    }

    private fun FragmentDetailBinding.onEditMode() {
        title.requestFocus()
        title.isEnabled = true
        contents.isEnabled = true
        binding.detailHeader.save.visibility = View.VISIBLE
        binding.detailHeader.attach.visibility = View.VISIBLE
        binding.detailHeader.delete.visibility = View.GONE
    }


    private val bottomSheetCallback
            = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            Log.d(TAG, "bottomSheet.height: ${bottomSheet.height} ")
            Log.d(TAG, "container.height: ${binding.container.height}}")

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {

        }
    }


    override fun onBackPressed() {
        // TODO Add 인지 Update 인지 View 에서는 상관하지 않도록
        val mode = viewModel.mode.value ?: false
        if (mode) {
            viewModel.addNote(noteItem = NoteItem(
                Calendar.getInstance().timeInMillis,
                binding.title.text.toString(),
                binding.contents.text.toString()
            ))
        }
        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val TAG = "DetailFragment"
    }
}