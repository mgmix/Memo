package mgmix.dev.line.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.replace(
    @IdRes containerViewId: Int, fragment: Fragment,
    tag: String? = null
) {
    supportFragmentManager.beginTransaction().replace(
        containerViewId, fragment, tag
    ).commit()
}

fun FragmentActivity?.replace(
    @IdRes containerViewId: Int, fragment: Fragment,
    tag: String? = null
): FragmentTransaction? {
    return this?.supportFragmentManager?.beginTransaction()?.replace(
        containerViewId, fragment, tag
    )
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

