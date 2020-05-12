package app.isfaaghyth.uicomponent.ui

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import app.isfaaghyth.uicomponent.component.EventBusFactory
import app.isfaaghyth.uicomponent.component.UIComponent
import app.isfaaghyth.uicomponent.dispatchers.DispatcherProvider
import app.isfaaghyth.uicomponent.state.ScreenStateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SampleComponent(
    container: ViewGroup,
    private val bus: EventBusFactory,
    coroutineScope: CoroutineScope,
    dispatcher: DispatcherProvider
): UIComponent<SampleInteractionEvent>, CoroutineScope by coroutineScope, SampleUIView.Listener {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val uiView = initView(container)

    private fun initView(container: ViewGroup): SampleUIView {
        return SampleUIView(container, this)
    }

    init {
        launch(dispatcher.immediate()) {
            bus.getSafeManagedFlow(ScreenStateEvent::class.java)
                .collect {
                    when (it) {
                        ScreenStateEvent.Init -> uiView.hide()
                        is ScreenStateEvent.SetButtonTitle -> {
                            setButtonTitle(it.title)
                        }
                    }
                }
        }
    }

    private fun setButtonTitle(title: String) {
        uiView.setButtonTitle(title)
        uiView.show()
    }

    override fun onTestClicked() {
        launch {
            bus.emit(
                SampleInteractionEvent::class.java,
                SampleInteractionEvent.TestClicked
            )
        }
    }

    override fun interactionEvents(): Flow<SampleInteractionEvent> {
        return bus.getSafeManagedFlow(SampleInteractionEvent::class.java)
    }

    override fun getContainerId(): Int {
        return uiView.containerId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        uiView.onDestroy()
    }

}