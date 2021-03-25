package fr.codingfactory.scanner.requests

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import fr.codingfactory.scanner.data.Item
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class FoodWrapperApi(val product: ProductApi)

data class ProductApi(
    val product_name: String,
    val image_url: String,
    val nutrition_grades: String,
    val ingredients_text_fr: String
    )

@RequiresApi(Build.VERSION_CODES.O)
fun mapFoodWrapperApiToFood(wrapperApi: FoodWrapperApi): Item {
    Log.i("test", "mapFoodWrapperApiToFood:$wrapperApi ")
    return Item(
        0,
            wrapperApi.product.product_name,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
            wrapperApi.product.image_url,
            wrapperApi.product.nutrition_grades,
            wrapperApi.product.ingredients_text_fr
    )

}