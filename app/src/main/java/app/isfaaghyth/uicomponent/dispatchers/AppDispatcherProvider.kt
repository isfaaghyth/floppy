package app.isfaaghyth.uicomponent.dispatchers

import kotlinx.coroutines.Dispatchers

class AppDispatcherProvider: DispatcherProvider {
    override fun ui() = Dispatchers.Main
    override fun io() = Dispatchers.IO
    override fun immediate() = Dispatchers.Unconfined
}