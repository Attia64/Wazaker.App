package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.database.Azkaar
import com.attia.wazaker.databinding.MyAzkaarAdapterLayoutBinding


class MyAzkaarAdapter(
    val listener: (String) -> Unit
) : ListAdapter<Azkaar, MyAzkaarAdapter.AzkaarViewHolder>(AzkarDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkaarViewHolder {
        return AzkaarViewHolder(
            MyAzkaarAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class AzkaarViewHolder(val binding: MyAzkaarAdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: AzkaarViewHolder, position: Int) {
        holder.binding.apply {
            zekr = currentList[position]
            root.setOnClickListener {
                listener(currentList[position].zekr)
            }
        }
    }


    private object AzkarDiffer : DiffUtil.ItemCallback<Azkaar>() {
        override fun areItemsTheSame(oldItem: Azkaar, newItem: Azkaar): Boolean {
            return newItem.zekrId == oldItem.zekrId
        }

        override fun areContentsTheSame(oldItem: Azkaar, newItem: Azkaar): Boolean {
            return newItem == oldItem
        }
    }
}