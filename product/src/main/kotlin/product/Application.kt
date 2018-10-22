package product

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("product")
                .mainClass(Application.javaClass)
                .start()
    }
}