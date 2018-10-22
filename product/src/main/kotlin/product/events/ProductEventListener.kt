package product.events
import io.micronaut.context.annotation.Context
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.event.annotation.EventListener
import javax.inject.Singleton

@Singleton
@Context
class ProductEventListener{
    @EventListener
    fun onStartup( event: ServiceStartedEvent):Unit {
        println("#########PRODUCT EVENT STARTED ${event}")
    }
}