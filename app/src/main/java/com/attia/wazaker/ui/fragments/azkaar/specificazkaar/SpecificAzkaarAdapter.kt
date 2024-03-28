package com.attia.wazaker.ui.fragments.azkaar.specificazkaar


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.databinding.SpecifcAzkaarViewholderLayoutBinding


class SpecificAzkaarAdapter(
    val listener: (Int) -> Unit
) :
    ListAdapter<SpecficAzkaarType, SpecificAzkaarAdapter.SpecificViewHolder>(SpecificAzkaarDiffer) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificViewHolder {
        return SpecificViewHolder(
            SpecifcAzkaarViewholderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SpecificViewHolder, position: Int) {
        holder.binding.apply {
            tvzekr.text = currentList[position].zekr
            tvProgress.text = currentList[position].count.toString()
            root.setOnClickListener {
                listener(position)
            }
        }
    }

    class SpecificViewHolder(val binding: SpecifcAzkaarViewholderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    private object SpecificAzkaarDiffer : DiffUtil.ItemCallback<SpecficAzkaarType>() {
        override fun areItemsTheSame(
            oldItem: SpecficAzkaarType,
            newItem: SpecficAzkaarType
        ): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: SpecficAzkaarType,
            newItem: SpecficAzkaarType
        ): Boolean {
            return newItem == oldItem
        }
    }
}