package com.syhan.cinemasearch.core.data.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <reified T> Flow<T>.observeWithFragmentLifecycle(
    fragment: Fragment,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
): Job {
    return fragment.viewLifecycleOwner.lifecycleScope.launch {
        flowWithLifecycle(
            lifecycle = fragment.viewLifecycleOwner.lifecycle,
            minActiveState = minActiveState
        ).collect {
            action(it)
        }
    }
}