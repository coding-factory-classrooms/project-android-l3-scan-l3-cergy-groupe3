package fr.codingfactory.scanner.requests

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val url = "https://world.openfoodfacts.org/api/"


    private val retrofitClient: Retrofit.Builder by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(url)
            .client(client.build())
            .addConverterFactory(MoshiConverterFactory.create())
    }


    val itemService: ItemService by lazy {
        retrofitClient
            .build()
            .create(ItemService::class.java)
    }

}