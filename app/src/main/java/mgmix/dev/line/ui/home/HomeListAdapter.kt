package mgmix.dev.line.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import mgmix.dev.line.R
import mgmix.dev.line.repository.data.model.HomeItem
import mgmix.dev.line.databinding.ItemHomeListBinding

class HomeListAdapter
    : RecyclerView.Adapter<HomeListAdapter.MainListViewHolder>() {

    var items: List<HomeItem>  = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class MainListViewHolder(private val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeItem) {
            binding.item = item
        }

    }
}