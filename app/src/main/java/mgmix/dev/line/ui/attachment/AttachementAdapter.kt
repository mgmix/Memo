package mgmix.dev.line.ui.attachment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mgmix.dev.line.R
import mgmix.dev.line.databinding.ItemAttachmentsBinding

class AttachementAdapter
    : RecyclerView.Adapter<AttachementAdapter.DetailAttachViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAttachViewHolder
    = DetailAttachViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_attachments,
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DetailAttachViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class DetailAttachViewHolder(private val binding: ItemAttachmentsBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

}