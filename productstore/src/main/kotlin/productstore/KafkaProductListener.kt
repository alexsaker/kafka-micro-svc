package productstore
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.reactive.RedisReactiveCommands
import io.micronaut.configuration.kafka.annotation.*
import javax.inject.Inject
import org.slf4j.LoggerFactory

@KafkaListener(offsetReset = OffsetReset.LATEST)
<<<<<<< HEAD
class KafkaProductListener @Inject constructor(
        private val redisConnection: StatefulRedisConnection<String, String>,
){
    private lateinit var redisReactiveCmds: RedisReactiveCommands<String, String>
    private val logger = LoggerFactory.getLogger(KafkaProductListener::class.java)

    init {
        try {
            redisReactiveCmds = redisConnection.reactive()
        } catch (ex: RuntimeException) {
            logger.error(ex.message)
        }
=======
class KafkaProductListener{
>>>>>>> cc5cfd2f985d445fcfc02c9f001f72ea7752dc8b

    }
    @Topic("my-products")
    fun receive(@KafkaKey brand: String, product: Product) {
        println("Got Product - ${product.name} by $brand @ ${product.price} (Stock : ${product.quantity})")
        redisReactiveCmds.set(product.name,product.quantity.toString()).block()
    }
}