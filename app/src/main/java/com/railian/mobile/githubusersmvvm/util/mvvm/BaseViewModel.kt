package com.railian.mobile.githubusersmvvm.util.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    // Create ViewModel scope
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun <T : Any> launchCoroutine(
        backgroundTask: suspend () -> T,
        uiTask: (T) -> Unit
    ) {
        uiScope.launch {
            val result = withContext(Dispatchers.IO) { backgroundTask() }
            uiTask(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()   //  Cancel all ViewModel jobs
    }
}