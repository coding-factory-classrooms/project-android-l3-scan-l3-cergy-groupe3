package fr.codingfactory.scanner.requests

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ItemService {

    @GET("v0/product/{barcode}.json")
    fun itemInformation(@Path("barcode") barcode: String): Call<ItemWrapperApi>

}


