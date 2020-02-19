package mgmix.dev.line.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mgmix.dev.line.R
import mgmix.dev.line.repository.data.model.NoteItem
import mgmix.dev.line.databinding.ItemHomeListBinding

class HomeListAdapter(
    private val itemClickListener: (NoteItem) -> Unit
)
    : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    var items: List<NoteItem>  = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_list,
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                 itemClickListener(items[adapterPosition])
            }
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class HomeListViewHolder(private val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteItem) {
            binding.item = item
        }

    }
}