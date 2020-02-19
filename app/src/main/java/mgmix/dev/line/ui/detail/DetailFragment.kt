package mgmix.dev.line.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import mgmix.dev.line.R
import mgmix.dev.line.databinding.FragmentDetailBinding
import mgmix.dev.line.databinding.HeaderDetailBinding
import mgmix.dev.line.ui.attachment.AttachmentFragment
import mgmix.dev.line.ui.OnBackPressedListener
import mgmix.dev.line.ui.home.HomeViewModelFactory
import javax.inject.Inject

class DetailFragment : DaggerFragment(), OnBackPressedListener {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var attachmentBehavior: BottomSheetBehavior<FrameLayout>

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.detailHeader.setHeader()
        binding.initView()
        binding.onViewMode()
        return binding.root
    }

    private fun HeaderDetailBinding.setHeader() {
        val popupMenu = PopupMenu(requireContext(), overflow).apply {
            menuInflater.inflate(R.menu.detail_menu, this.menu)
        }

        overflow.setOnClickListener {
            popupMenu.show()
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun FragmentDetailBinding.initView() {
        attachmentBehavior = BottomSheetBehavior.from(container)
        attachmentBehavior.addBottomSheetCallback(bottomSheetCallback)

        val tag = "attachment"
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            childFragmentManager.beginTransaction().replace(container.id,
                AttachmentFragment(), tag ).commit()
        }

        viewModel.init()

    }

    private fun FragmentDetailBinding.onViewMode() {
        title.isEnabled = false
        contents.isEnabled = false
    }

    private fun FragmentDetailBinding.onEditMode() {
        title.requestFocus()
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
        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val TAG = "DetailFragment"
    }
}