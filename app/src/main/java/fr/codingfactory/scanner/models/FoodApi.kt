package fr.codingfactory.scanner.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime

data class FoodWrapperApi(val product: ProductApi)

data class ProductApi(val product_name: String, val image_url: String)

@RequiresApi(Build.VERSION_CODES.O)
fun mapFoodWrapperApiToFood(wrapperApi: FoodWrapperApi): Food {
    return Food(
        wrapperApi.product.product_name,
        LocalDate.now().toString(),
        LocalDateTime.now().toString(),
        wrapperApi.product.image_url
    )

}