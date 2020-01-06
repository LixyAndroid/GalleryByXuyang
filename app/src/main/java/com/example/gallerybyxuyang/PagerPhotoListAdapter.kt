package com.example.gallerybyxuyang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pager_photo_view.view.*

/**
 * @author Li Xuyang
 * @date 2020-01-06 21:23
 */
class PagerPhotoListAdapter: ListAdapter<PhotoItem, PagerPhotoViewHolder>(DiffCallback) {

    object DiffCallback: DiffUtil.ItemCallback<PhotoItem>(){
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_view,parent, false).apply {
            return PagerPhotoViewHolder(this)
        }
    }


    //加载图片
    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position).fullUrl)
            .placeholder(R.drawable.photo_placeholder)
            .into(holder.itemView.pagerPhoto)
    }

}


class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)