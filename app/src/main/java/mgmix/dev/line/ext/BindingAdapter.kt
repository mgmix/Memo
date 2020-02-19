package mgmix.dev.line.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun setImage(view: ImageView, url: String?) {
    // TODO if null delete imageView
    Glide.with(view.context)
        .load(url)
        .into(view)
}