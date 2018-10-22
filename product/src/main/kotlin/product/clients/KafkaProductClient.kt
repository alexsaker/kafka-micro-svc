package product.clients
import io.micronaut.configuration.kafka.annotation.*
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener
import product.models.Product
import javax.inject.Inject

@KafkaClient
interface KafkaProductClient {
    @Topic("my-products")
    fun sendProduct(@KafkaKey brand: String, product: Product)
}