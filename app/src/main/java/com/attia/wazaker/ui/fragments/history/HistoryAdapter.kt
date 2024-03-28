package com.attia.wazaker.ui.fragments.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.database.AzkaarHistory
import com.attia.wazaker.databinding.HistoryViewholderLayoutBinding

class HistoryAdapter :
    ListAdapter<AzkaarHistory, HistoryAdapter.HistoryViewHolder>(HistoryDiffer) {

    class HistoryViewHolder(val binding: HistoryViewholderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            HistoryViewholderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.apply {
            history = currentList[position]
        }

    }

    private object HistoryDiffer : DiffUtil.ItemCallback<AzkaarHistory>() {
        override fun areItemsTheSame(oldItem: AzkaarHistory, newItem: AzkaarHistory): Boolean {
            return newItem.zekrId == oldItem.zekrId
        }

        override fun areContentsTheSame(oldItem: AzkaarHistory, newItem: AzkaarHistory): Boolean {
            return newItem == oldItem
        }
    }
}