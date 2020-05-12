package app.isfaaghyth.uicomponent.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun immediate(): CoroutineDispatcher
}