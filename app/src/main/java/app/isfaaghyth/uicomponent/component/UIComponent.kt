package app.isfaaghyth.uicomponent.component

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.flow.Flow

interface UIComponent<T> : LifecycleObserver {
    @IdRes fun containerId(): Int
    fun interactionEvents(): Flow<T>
}