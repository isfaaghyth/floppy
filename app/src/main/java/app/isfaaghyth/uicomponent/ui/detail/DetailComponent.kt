package app.isfaaghyth.uicomponent.ui.detail

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LifecycleOwner
import app.isfaaghyth.uicomponent.component.EventBusFactory
import app.isfaaghyth.uicomponent.component.UIComponent
import app.isfaaghyth.uicomponent.dispatchers.DispatcherProvider
import app.isfaaghyth.uicomponent.state.ScreenStateEvent
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class DetailComponent(
    container: ViewGroup,
    private val bus: EventBusFactory,
    coroutineScope: CoroutineScope,
    dispatcher: DispatcherProvider
): UIComponent<Unit>, CoroutineScope by coroutineScope {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val uiView = initView(container)

    private fun initView(container: ViewGroup): DetailUIView {
        return DetailUIView(container)
    }

    init {
        launch(dispatcher.immediate()) {
            bus.getSafeManagedFlow(ScreenStateEvent::class.java)
                .collect {
                    when (it) {
                        ScreenStateEvent.Init -> uiView.hide()
                        is ScreenStateEvent.SetPersonDetail -> {
                            setCharacterDetail(it.charDetail)
                        }
                    }
                }
        }
    }

    private fun setCharacterDetail(detail: CharDetailUIModel) {
        uiView.bind(detail)
        uiView.showWithHeight()
    }

    override fun containerId(): Int {
        return uiView.containerId
    }

    override fun interactionEvents(): Flow<Unit> {
        return emptyFlow()
    }

    companion object {
        fun init(
            container: ViewGroup,
            coroutineScope: CoroutineScope,
            lifecycleOwner: LifecycleOwner,
            dispatcher: DispatcherProvider
        ): UIComponent<Unit> {
            val detailComponent = DetailComponent(
                container,
                EventBusFactory.get(lifecycleOwner),
                coroutineScope,
                dispatcher
            ).also(lifecycleOwner.lifecycle::addObserver)

            coroutineScope.launch {
                detailComponent.interactionEvents()
            }

            return pinnedComponent
        }
    }

}
