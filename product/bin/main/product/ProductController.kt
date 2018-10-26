package product

import com.fasterxml.jackson.core.JsonParseException
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import javax.inject.Inject
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateos.JsonError
import io.micronaut.http.HttpResponse.status
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateos.Link


@Controller("/products")
open class ProductController(
        @Inject open val kafkaProductClient:KafkaProductClient,
        @Inject open val eventPublisher:ApplicationEventPublisher
) : ProductOperations{
    @Post("/")
    override fun save(product:Product): Single<Product>{
        kafkaProductClient.sendProduct(brand = product.brand,product=product )
        eventPublisher.publishEvent("I just send to kafka a product ${product.name}")
        return Single.just(product)
    }

    @Error
    open fun jsonError(request: HttpRequest<Any?>, jsonParseException: JsonParseException): HttpResponse<JsonError> {
      //  val error = JsonError("Invalid JSON: " + jsonParseException.message)
       //         .link(Link.SELF, Link.of(request.uri))
        return HttpResponse.badRequest<JsonError>(
                JsonError("Fix your JSON bro!!")
        ).status(HttpStatus.BAD_REQUEST)
        // return status(HttpStatus.BAD_REQUEST,"Fix your json bro!!!")
    }
}