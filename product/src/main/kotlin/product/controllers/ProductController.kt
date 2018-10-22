package product.controllers

import com.fasterxml.jackson.core.JsonParseException
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.Success
import com.sun.org.apache.xml.internal.security.Init
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import javax.inject.Inject
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateos.JsonError
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable
import product.clients.KafkaProductClient
import product.models.Product
import product.operations.ProductOperations
import java.lang.RuntimeException


@Controller("/products")
class ProductController(
        @Inject open val kafkaProductClient: KafkaProductClient,
        @Inject open val mongoClient:MongoClient
) : ProductOperations {

    @Get("/")
    override fun getAllProducts(limit:Int? ): Single<MutableList<Product>>{
        val myLimit =  limit ?: 15
        return Flowable.fromPublisher(
                mongoClient
                        .getDatabase("test")
                        .getCollection("products")
                        .find().limit(myLimit))
                .map { Product(
                        it.get("name","N.A"),
                        it.get("brand","N.A"),
                        it.get("price",0.0f),
                        it.get("quantity",0))
                }
                .toList()
    }



    @Post("/")
    override fun save(product: Product): Single<Product>{
        kafkaProductClient.sendProduct(brand = product.brand,product=product )
        return Single.fromPublisher(
                mongoClient
                        .getDatabase("test")
                        .getCollection("products", Product::class.java)
                        .insertOne(product)).map { _: Success -> product }
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