package mgmix.dev.line.ext

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import mgmix.dev.line.R

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, path: String?) {
    if (path != null) {
        Glide.with(view.context)
            .load(path)
            .error(R.drawable.ic_error_black_24dp)
            .into(view)
    }
}

@BindingAdapter("app:setVisible")
fun setVisible(view: View, visible: Boolean) {
    Log.e("Binding", "visible: $visible ")
    view.visibility = if (visible) View.VISIBLE else View.GONE
}