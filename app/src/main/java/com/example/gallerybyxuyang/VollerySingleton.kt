package com.example.gallerybyxuyang

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * @author Li Xuyang
 * @date 2020-01-02 15:32
 */

class VolleySingleton private constructor(context: Context){
    companion object {
        private var INSTANCE : VolleySingleton?=null
        fun getInstance(context: Context) = INSTANCE?: synchronized(this) {
                VolleySingleton(context).also { INSTANCE = it }
            }

    }

    //全局唯一
    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}