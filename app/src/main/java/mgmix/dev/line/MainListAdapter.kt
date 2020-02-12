package mgmix.dev.line

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mgmix.dev.line.databinding.ItemMainListBinding

class MainListAdapter
    : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    var items: List<String>  = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainListViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_main_list, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class MainListViewHolder(private val binding: ItemMainListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            //
        }

    }
}