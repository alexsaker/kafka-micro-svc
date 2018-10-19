package productstore

import io.micronaut.configuration.kafka.annotation.*

@KafkaListener(offsetReset = OffsetReset.LATEST)
class KafkaProductListener{

    @Topic("my-products")
    fun receive(@KafkaKey brand: String, product: Product) {
        println("Got Product - ${product.name} by $brand @ ${product.price} (Stock : ${product.quantity})")
    }
}