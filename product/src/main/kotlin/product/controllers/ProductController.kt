package product.controllers

import com.fasterxml.jackson.core.JsonParseException
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import javax.inject.Inject
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateos.JsonError
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Error
import product.clients.KafkaProductClient
import product.models.Product
import product.operations.ProductOperations
import java.lang.RuntimeException
import javax.ws.rs.BadRequestException


@Controller("/products")
open class ProductController(
        @Inject open val kafkaProductClient: KafkaProductClient
) : ProductOperations {
    @Post("/")
    override fun save(product: Product): Single<Product>{
        kafkaProductClient.sendProduct(brand = product.brand,product=product )
        return Single.just(product)
    }

    @Error
    open fun jsonError(request: HttpRequest<Any?>, jsonParseException: JsonParseException): HttpResponse<JsonError> {
        return HttpResponse.badRequest<JsonError>(
                JsonError("Fix your JSON bro!!")
        ).status(HttpStatus.BAD_REQUEST)
    }

    @Error
    open fun validationError(request: HttpRequest<Any?>, runtimeException: RuntimeException): HttpResponse<JsonError> {
        return HttpResponse.badRequest<JsonError>(
                JsonError("Fix your entered data bro!!: ${runtimeException.message}")
        ).status(HttpStatus.BAD_REQUEST)
    }
}