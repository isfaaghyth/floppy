package app.isfaaghyth.uicomponent.ui.person

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import app.isfaaghyth.uicomponent.component.EventBusFactory
import app.isfaaghyth.uicomponent.component.UIComponent
import app.isfaaghyth.uicomponent.dispatchers.DispatcherProvider
import app.isfaaghyth.uicomponent.state.ScreenStateEvent
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharListComponent(
    container: ViewGroup,
    private val bus: EventBusFactory,
    coroutineScope: CoroutineScope,
    dispatcher: DispatcherProvider
): UIComponent<CharInteractionEvent>, CoroutineScope by coroutineScope, CharUIView.Listener {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val uiView = initView(container)

    private fun initView(container: ViewGroup): CharUIView {
        return CharUIView(container, this)
    }

    init {
        launch(dispatcher.immediate()) {
            bus.getSafeManagedFlow(ScreenStateEvent::class.java)
                .collect {
                    when (it) {
                        ScreenStateEvent.Init -> uiView.hide()
                        is ScreenStateEvent.SetPersonInfo -> {
                            setPersonInfo(it.characters)
                        }
                    }
                }
        }
    }

    private fun setPersonInfo(characters: List<CharacterUIModel>) {
        uiView.bind(characters)
        uiView.show()
    }

    override fun onPersonInfoClicked(character: CharacterUIModel) {
        launch {
            bus.emit(
                CharInteractionEvent::class.java,
                CharInteractionEvent.CharInfoClicked(character)
            )
        }
    }

    override fun interactionEvents(): Flow<CharInteractionEvent> {
        return bus.getSafeManagedFlow(CharInteractionEvent::class.java)
    }

    override fun containerId(): Int {
        return uiView.containerId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        uiView.onDestroy()
    }

    companion object {
        fun init(
            container: ViewGroup,
            coroutineScope: CoroutineScope,
            lifecycleOwner: LifecycleOwner,
            dispatcher: DispatcherProvider,
            onAction: (event: CharInteractionEvent) -> Unit
        ): UIComponent<CharInteractionEvent> {
            val pinnedComponent = CharListComponent(
                container,
                EventBusFactory.get(lifecycleOwner),
                coroutineScope,
                dispatcher
            ).also(lifecycleOwner.lifecycle::addObserver)

            coroutineScope.launch {
                pinnedComponent.interactionEvents()
                    .collect { onAction(it) }
            }

            return pinnedComponent
        }
    }

}