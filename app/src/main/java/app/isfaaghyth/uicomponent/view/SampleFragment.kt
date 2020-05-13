package app.isfaaghyth.uicomponent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.isfaaghyth.uicomponent.R
import app.isfaaghyth.uicomponent.component.EventBusFactory
import app.isfaaghyth.uicomponent.component.UIComponent
import app.isfaaghyth.uicomponent.dispatchers.AppDispatcherProvider
import app.isfaaghyth.uicomponent.dispatchers.DispatcherProvider
import app.isfaaghyth.uicomponent.state.ScreenStateEvent
import app.isfaaghyth.uicomponent.ui.detail.DetailComponent
import app.isfaaghyth.uicomponent.ui.person.CharListComponent
import app.isfaaghyth.uicomponent.ui.person.CharInteractionEvent
import app.isfaaghyth.uicomponent.view.uimodel.CharDetailUIModel
import app.isfaaghyth.uicomponent.view.uimodel.CharacterUIModel
import app.isfaaghyth.uicomponent.view.viewmodel.SampleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SampleFragment: Fragment(), CoroutineScope {

    private var dispatchers: DispatcherProvider = AppDispatcherProvider()

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + dispatchers.ui()

    private lateinit var personComponent: UIComponent<*>
    private lateinit var personDetailComponent: UIComponent<*>

    private lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
            .of(this)
            .get(SampleViewModel::class.java)
    }

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
        viewModel.onShowPerson()

        observerPersonInfo()
        observerPersonDetailInfo()
    }

    private fun initialState() {
        launch(dispatchers.immediate()) {
            EventBusFactory.get(viewLifecycleOwner).emit(
                ScreenStateEvent::class.java,
                ScreenStateEvent.Init
            )
        }
    }

    private fun initComponents(container: ViewGroup) {
        personComponent = initPersonComponent(container)
        personDetailComponent = initDetailComponent(container)

        initialState()
    }

    private fun observerPersonInfo() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            setPersonInfo(it)
        })
    }

    private fun observerPersonDetailInfo() {
        viewModel.personDetail.observe(viewLifecycleOwner, Observer {
            setCharacterDetail(it)
        })
    }

    private fun setPersonInfo(characters: List<CharacterUIModel>) {
        launch {
            EventBusFactory.get(viewLifecycleOwner)
                .emit(
                    ScreenStateEvent::class.java,
                    ScreenStateEvent.SetPersonInfo(characters)
                )
        }
    }

    private fun setCharacterDetail(detail: CharDetailUIModel) {
        launch {
            EventBusFactory.get(viewLifecycleOwner)
                .emit(
                    ScreenStateEvent::class.java,
                    ScreenStateEvent.SetPersonDetail(detail)
                )
        }
    }

    private fun initPersonComponent(
        container: ViewGroup
    ): UIComponent<CharInteractionEvent> {
        return CharListComponent.init(
            container = container,
            coroutineScope = this,
            dispatcher = dispatchers,
            lifecycleOwner = viewLifecycleOwner,
            onAction = {
                when (it) {
                    is CharInteractionEvent.CharInfoClicked -> {
                        viewModel.onShowPersonDetail(it.character)
                    }
                }
            }
        )
    }

    private fun initDetailComponent(
        container: ViewGroup
    ): UIComponent<Unit> {
        return DetailComponent.init(
            container = container,
            coroutineScope = this,
            dispatcher = dispatchers,
            lifecycleOwner = viewLifecycleOwner
        )
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