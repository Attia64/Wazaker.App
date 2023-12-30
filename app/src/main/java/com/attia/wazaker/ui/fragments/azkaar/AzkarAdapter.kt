package com.attia.wazaker.ui.fragments.azkaar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.databinding.AzkaarAdapterLayoutBinding


class AzkaarAdapter: ListAdapter<String, AzkaarAdapter.AzkaarViewHolder>(AzkarDiffer) {

    class AzkaarViewHolder(val binding: AzkaarAdapterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkaarViewHolder {
        return AzkaarViewHolder(
            AzkaarAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AzkaarViewHolder, position: Int) {
        holder.binding.apply {
            tvAzkaar.text = currentList[position]
        }
    }
}

object AzkarDiffer : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem === oldItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem == oldItem
    }
}