package com.example.sbtechincaltest.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sbtechincaltest.databinding.ListItemPhotoBinding

class PhotosAdapter :
    androidx.recyclerview.widget.ListAdapter<PhotoListItem, PhotosAdapter.ViewHolder>(
        TestResultsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoListItem) {
            binding.photo = photo
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPhotoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    class TestResultsDiffCallback : DiffUtil.ItemCallback<PhotoListItem>() {
        override fun areItemsTheSame(oldItem: PhotoListItem, newItem: PhotoListItem): Boolean {
            return oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: PhotoListItem, newItem: PhotoListItem): Boolean {
            return oldItem == newItem
        }
    }
}
