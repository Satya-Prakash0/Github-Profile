package com.satya.profilesearchapp.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.satya.profilesearchapp.R
import com.satya.profilesearchapp.databinding.FragmentMainBinding
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.presentation.adapter.RepoAdapter
import com.satya.profilesearchapp.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
    Fragment(R.layout.fragment_main),
    RepoAdapter.OnItemClickListener {

    lateinit var binding: FragmentMainBinding
    private val repoAdapter = RepoAdapter(this)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeUiState()
        setupSearch()
        viewModel.loadRepositories()
    }

    /**
     * Sets up the search functionality.
     */
    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search when the user submits the query
                query?.let {
                    viewModel.searchRepository(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModel.searchRepository(newText)
                } else {
                    viewModel.loadRepositories()
                }
                return true
            }
        })
    }

    /**
     * Configures the RecyclerView with adapter and layout manager.
     */
    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    /**
     * Observes the UI state from the ViewModel to handle loading, success, and error states.
     */
    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    showLoading(true)
                }
                is UiState.Success -> {
                    showLoading(false)
                    updateRecyclerView(uiState.data)
                }
                is UiState.Error -> {
                    showLoading(false)
                    showError(uiState.message)
                }
            }
        }
    }

    /**
     * Toggles the loading indicator visibility.
     */
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        if (isLoading) {
            binding.recyclerView.isVisible = false
        } else {
            binding.recyclerView.isVisible = true
        }
    }

    /**
     * Updates the RecyclerView with a list of repositories or shows an empty state.
     */
    private fun updateRecyclerView(repositories: List<RepoUiModel>) {
        if (repositories.isEmpty()) {
            binding.emptyStateText.isVisible = true
            binding.recyclerView.isVisible = false
        } else {
            binding.emptyStateText.isVisible = false
            repoAdapter.submitList(repositories)
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Handles item click events and navigates to the WebViewFragment.
     */
    override fun onItemClick(repo: RepoUiModel) {
        val action = MainFragmentDirections.actionMainFragmentToWebViewFragment(repo.repoUrl)
        findNavController().navigate(action)
    }
}