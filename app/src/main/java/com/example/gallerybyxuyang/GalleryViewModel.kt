package com.example.gallerybyxuyang

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

/**
 * @author Li Xuyang
 * @date 2020-01-02 15:29
 */
class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive : LiveData<List<PhotoItem>>
        get() = _photoListLive

    fun fetchData() {
        val stringRequest = StringRequest(
            //类型
            Request.Method.GET,
            //url
            getUrl(),
            //成功回调，监听器
            Response.Listener {
                //解析gson
                _photoListLive.value = Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },

            //失败回调，监听器
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )


        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)

    }

    private fun getUrl():String {
        return "https://pixabay.com/api/?key=14793670-ea5a62d0bbaf752a8218590e6&q=${keyWords.random()}&per_page=100"
    }

    private val keyWords = arrayOf("apple","MacBook Pro","cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal")
}