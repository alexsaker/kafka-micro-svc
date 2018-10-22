package product.models
import com.fasterxml.jackson.annotation.JsonProperty


data class Product(
        @JsonProperty("name")val name:String,
        @JsonProperty("brand")val brand:String,
        @JsonProperty("price")val price:Float,
        @JsonProperty("quantity")val quantity:Int = 0
)