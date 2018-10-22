package productstore

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("productstore")
                .mainClass(Application.javaClass)
                .start()
    }
}