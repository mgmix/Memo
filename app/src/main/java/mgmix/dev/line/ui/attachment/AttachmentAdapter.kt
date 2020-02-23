package mgmix.dev.line.ui.attachment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mgmix.dev.line.R
import mgmix.dev.line.databinding.ItemAttachmentsBinding
import mgmix.dev.line.repository.data.model.AttachmentItem

class AttachmentAdapter(
    private val itemClickListener: (AttachmentItem) -> Unit
)
    : RecyclerView.Adapter<AttachmentAdapter.DetailAttachViewHolder>() {

    var items: List<AttachmentItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var mode: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAttachViewHolder
    = DetailAttachViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_attachments,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DetailAttachViewHolder, position: Int) {
        holder.bind(items[position])
        if (mode) holder.delete.visibility = View.VISIBLE else holder.delete.visibility = View.GONE
        holder.delete.setOnClickListener {
            itemClickListener(items[position])
        }
    }


    class DetailAttachViewHolder(private val binding: ItemAttachmentsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: AttachmentItem) {
            binding.item = items
        }

        val delete: ImageButton = binding.delete
    }

    companion object {
        const val TAG = "AttachmentAdapter"
    }

}