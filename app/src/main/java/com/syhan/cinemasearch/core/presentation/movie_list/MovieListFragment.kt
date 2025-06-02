package com.syhan.cinemasearch.core.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.data.UiState
import com.syhan.cinemasearch.core.data.observeWithFragmentLifecycle
import com.syhan.cinemasearch.databinding.FragmentMovieListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MovieListFragment"

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { MovieListScreen(viewModel = viewModel) }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observeWithFragmentLifecycle(this) {
            if (it.uiState == UiState.ShowError) {
                showErrorSnackBar(view) {
                    viewModel.setLoadingState()
                    viewModel.loadMovies()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorSnackBar(
        view: View,
        action: () -> Unit,
    ) {
        Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.action_retry) {
                action()
            }
            .setBackgroundTint(resources.getColor(R.color.dark_grey, null))
            .setTextColor(resources.getColor(R.color.white, null))
            .setActionTextColor(resources.getColor(R.color.dark_yellow, null))
            .show()
    }
}