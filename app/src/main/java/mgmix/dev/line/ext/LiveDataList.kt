package mgmix.dev.line.ext

import androidx.lifecycle.MutableLiveData

class LiveDataList<T> : MutableLiveData<MutableList<T>>() {

    init {
        value = arrayListOf()
    }

    fun add(item: T) {
        val items: MutableList<T> = value ?: arrayListOf()
        items.add(item)
        value = items
    }

    fun remove(item: T) {
        val items: MutableList<T> = value ?: arrayListOf()
        items.remove(item)
        value = items
    }

}