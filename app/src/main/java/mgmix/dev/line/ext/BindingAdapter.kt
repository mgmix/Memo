package mgmix.dev.line.ext

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, url: String?) {
    // TODO if null delete imageView
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("app:setVisible")
fun setVisible(view: View, visible: Boolean) {
    Log.e("Binding", "visible: $visible ")
    view.visibility = if (visible) View.VISIBLE else View.GONE
}