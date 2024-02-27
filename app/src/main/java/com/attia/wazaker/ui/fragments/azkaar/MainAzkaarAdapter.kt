package com.attia.wazaker.ui.fragments.azkaar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.databinding.MainAzkaarViewholerBinding
import com.attia.wazaker.ui.fragments.azkaar.myazkaar.MainAzkaarType

class MainAzkaarAdapter(
    private var cardList: ArrayList<MainAzkaarType>
) :
    ListAdapter<MainAzkaarType, MainAzkaarAdapter.MainAzkaarViewHolder>(MainAzkaarDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAzkaarViewHolder {
        return MainAzkaarViewHolder(
            MainAzkaarViewholerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAzkaarViewHolder, position: Int) {
        holder.binding.apply {
            imMyAzkaar.setImageResource(cardList[position].img)
            tvTitle.text = cardList[position].title
        }
    }

    class MainAzkaarViewHolder(val binding: MainAzkaarViewholerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class MainAzkaarDiffer : DiffUtil.ItemCallback<MainAzkaarType>() {
        override fun areItemsTheSame(oldItem: MainAzkaarType, newItem: MainAzkaarType): Boolean {
            return newItem.img == oldItem.img
        }

        override fun areContentsTheSame(oldItem: MainAzkaarType, newItem: MainAzkaarType): Boolean {
            return newItem.img == oldItem.img
        }
    }

}