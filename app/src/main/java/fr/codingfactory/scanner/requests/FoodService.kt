package fr.codingfactory.scanner.requests

import fr.codingfactory.scanner.models.Food
import retrofit2.http.GET
import retrofit2.Call

interface FoodService {
    @GET()
    fun foodInformation(): Call<Food>
}


