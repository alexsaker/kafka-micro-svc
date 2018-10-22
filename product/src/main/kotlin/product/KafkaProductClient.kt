package product
import io.micronaut.configuration.kafka.annotation.*
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener

@KafkaClient
interface KafkaProductClient {

    @Topic("my-products")
    fun sendProduct(@KafkaKey brand: String, product: Product)

    @EventListener
    fun sendNotification(event:ApplicationEventPublisher){

    }
}