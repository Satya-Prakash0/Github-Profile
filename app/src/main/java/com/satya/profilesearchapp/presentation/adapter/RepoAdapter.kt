package com.satya.profilesearchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.satya.profilesearchapp.databinding.ItemRepoBinding
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.R

/**
 * Adapter for displaying a list of repositories (RepoUiModel) in a RecyclerView.
 */
class RepoAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<RepoUiModel, RepoAdapter.RepoViewHolder>(RepoDiffCallback()) {

    // Creates and returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding, listener)
    }

    // Binds data to the ViewHolder
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * ViewHolder for individual repository items.
     */
    class RepoViewHolder(
        private val binding: ItemRepoBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        // Binds RepoUiModel data to the views
        fun bind(repo: RepoUiModel) {
            binding.apply {
                repoName.text = repo.name
                repoId.text = repo.id.toString()
//                viewRepository.text = repo.repoUrl.toString()

                Glide.with(root.context)
                    .load(repo.profileUrl)
                    .centerCrop()
                    .placeholder(R.drawable.baseline_person_24)
                    .into(repoIcon)

                root.setOnClickListener { listener.onItemClick(repo) }
            }
        }
    }

    /**
     * DiffUtil callback for comparing RepoUiModel items.
     */
    class RepoDiffCallback : DiffUtil.ItemCallback<RepoUiModel>() {
        override fun areItemsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel) =
            oldItem == newItem
    }

    /**
     * Listener for handling item clicks.
     */
    interface OnItemClickListener {
        fun onItemClick(repo: RepoUiModel)
    }
}
