package product
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import io.reactivex.Single
import javax.validation.constraints.NotNull

@Validated
interface ProductOperations {
    @Post("/products")
    fun save(@NotNull @Body product:Product): Single<Product>
}