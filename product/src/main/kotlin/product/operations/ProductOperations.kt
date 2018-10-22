package product.operations
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import io.reactivex.Single
import product.models.Product
import javax.validation.constraints.NotNull

@Validated
interface ProductOperations {
    @Get("/")
    fun getAllProducts(limit:Int?):Single<MutableList<Product>>

    @Post("/products")
    fun save(@NotNull @Body product: Product): Single<Product>
}