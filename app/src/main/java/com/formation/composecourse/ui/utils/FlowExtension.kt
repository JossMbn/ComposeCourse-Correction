package com.formation.composecourse.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Use for collecting Flow just ones.
 **/
@Composable
fun <T> Flow<T>.CollectAsEvents(
    block: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(this, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@CollectAsEvents.collect(block)
        }
    }
}
