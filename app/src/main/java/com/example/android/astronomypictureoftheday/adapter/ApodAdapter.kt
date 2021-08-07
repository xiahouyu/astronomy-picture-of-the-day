package com.example.android.astronomypictureoftheday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.astronomypictureoftheday.databinding.ListImageBinding
import com.example.android.astronomypictureoftheday.model.ApodImage

class ApodAdapter(val onClickListener: OnClickListener) :
    ListAdapter<ApodImage, ApodAdapter.ApodViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder(ListImageBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val apodImage = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(apodImage)
        }
        holder.bind(apodImage)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ApodImage>() {
        override fun areItemsTheSame(oldItem: ApodImage, newItem: ApodImage): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ApodImage, newItem: ApodImage): Boolean {
            return oldItem.date == newItem.date
        }
    }

    class ApodViewHolder(private var binding: ListImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apodImage: ApodImage) {
            binding.apodImage = apodImage
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (apodImage: ApodImage) -> Unit) {
        fun onClick(apodImage: ApodImage) = clickListener(apodImage)
    }
}