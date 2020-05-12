package app.isfaaghyth.uicomponent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.EventBusFactory
import app.isfaaghyth.uicomponent.component.UIComponent
import app.isfaaghyth.uicomponent.dispatchers.AppDispatcherProvider
import app.isfaaghyth.uicomponent.dispatchers.DispatcherProvider
import app.isfaaghyth.uicomponent.state.ScreenStateEvent
import app.isfaaghyth.uicomponent.ui.SampleComponent
import app.isfaaghyth.uicomponent.ui.SampleInteractionEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SampleFragment: Fragment(), CoroutineScope {

    private lateinit var sampleComponent: UIComponent<*>
    private var dispatchers: DispatcherProvider = AppDispatcherProvider()

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + dispatchers.ui()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        initComponents(view.findViewById(R.id.containerSample) as ViewGroup)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setButtonTitle("ISFA GANTENG")
    }

    private fun initComponents(container: ViewGroup) {
        sampleComponent = initSampleComponent(container)
        sendInitState()
    }

    private fun setButtonTitle(title: String) {
        launch {
            EventBusFactory.get(viewLifecycleOwner)
                .emit(
                    ScreenStateEvent::class.java,
                    ScreenStateEvent.SetButtonTitle(title)
                )
        }
    }

    private fun initSampleComponent(
        container: ViewGroup
    ): UIComponent<SampleInteractionEvent> {
        val pinnedComponent = SampleComponent(
            container,
            EventBusFactory.get(viewLifecycleOwner),
            this,
            dispatchers)
            .also(viewLifecycleOwner.lifecycle::addObserver)

        launch {
            pinnedComponent.interactionEvents()
                .collect {
                    when (it) {
                        is SampleInteractionEvent.TestClicked -> {
                            setButtonTitle("CIEE, BERUBAH!")
                        }
                    }
                }
        }
        return pinnedComponent
    }

    private fun sendInitState() {
        launch(dispatchers.immediate()) {
            EventBusFactory.get(viewLifecycleOwner).emit(
                ScreenStateEvent::class.java,
                ScreenStateEvent.Init
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

    companion object {
        fun init(): SampleFragment {
            return SampleFragment()
        }
    }

}