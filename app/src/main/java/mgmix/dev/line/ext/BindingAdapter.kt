package mgmix.dev.line.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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
