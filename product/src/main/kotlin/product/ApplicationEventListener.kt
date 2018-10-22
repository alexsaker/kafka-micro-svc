package product


import io.micronaut.context.annotation.Context
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import javax.inject.Singleton

@Singleton
@Context
class  MyApplicationEventListener:ApplicationEventListener<ServiceStartedEvent> {
    override fun onApplicationEvent(event: ServiceStartedEvent?) {
        event.let { println("#######${it.toString()} STARTED") }
    }


}
