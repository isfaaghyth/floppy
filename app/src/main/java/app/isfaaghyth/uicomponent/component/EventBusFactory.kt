package app.isfaaghyth.uicomponent.component

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class EventBusFactory private constructor(
    val owner: LifecycleOwner
) {

    companion object {

        private val buses = mutableMapOf<LifecycleOwner, EventBusFactory>()
        val map = HashMap<Class<*>, BroadcastChannel<*>>()

        /**
         * Return the [EventBusFactory] associated to the [LifecycleOwner]. It there is no bus it will create one.
         * If the [LifecycleOwner] used is a fragment it use [Fragment#getViewLifecycleOwner()]
         */
        @JvmStatic fun get(lifecycleOwner: LifecycleOwner): EventBusFactory {
            return with(lifecycleOwner) {
                var eventBus = buses[lifecycleOwner]
                if (eventBus == null) {
                    eventBus = EventBusFactory(lifecycleOwner)
                    buses[lifecycleOwner] = eventBus
                    lifecycleOwner.lifecycle.addObserver(eventBus.observer)
                }
                eventBus
            }
        }
    }

    internal val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            map.forEach { entry -> entry.value.cancel() }
            buses.remove(owner)
        }
    }

    private fun <T> create(clazz: Class<T>): BroadcastChannel<T> {
        val channel = BroadcastChannel<T>(Channel.BUFFERED)
        map[clazz] = channel
        return channel
    }

    /**
     * emit will create (if needed) or use the existing channel to send events.
     *
     * @param clazz is the Event Class
     * @param event is the instance of the Event to be sent
     */
    suspend fun <T : ComponentEvent> emit(clazz: Class<T>, event: T) {
        val channel = if (map[clazz] != null) map[clazz] as SendChannel<T> else create(clazz)
        channel.send(event)
    }

    /**x
     * getSafeManagedFlow returns a Flow which is
     *  *Safe* against reentrant events as it is serialized and
     *  *Managed* since it disposes itself based on the lifecycle
     *
     *  @param clazz is the class of the event type used by this channel
     */
    fun <T : ComponentEvent> getSafeManagedFlow(clazz: Class<T>): Flow<T> {
        return (if (map[clazz] != null) map[clazz] as BroadcastChannel<T> else create(clazz)).asFlow()
    }

}