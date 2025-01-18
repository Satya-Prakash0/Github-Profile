package com.satya.profilesearchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.satya.profilesearchapp.databinding.ItemRepoBinding
import com.satya.profilesearchapp.domain.model.RepoUiModel


class RepoAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<RepoUiModel, RepoAdapter.RepoViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepoViewHolder(
        private val binding: ItemRepoBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoUiModel) {
            binding.apply {
                repoName.text = repo.name
                repoId.text = repo.id.toString()
                repoUrl.text = repo.repoUrl.toString()

                // Set up click listener
                root.setOnClickListener {
                    listener.onItemClick(repo)
                }
            }
        }
    }

    class RepoDiffCallback : DiffUtil.ItemCallback<RepoUiModel>() {
        override fun areItemsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repo: RepoUiModel)
    }

}

