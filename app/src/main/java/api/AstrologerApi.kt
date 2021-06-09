package api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AstrologerApi {
    @GET("horoscope/{sign}/today")
    suspend fun fetchHoroscope(@Path("sign") sign: String): HoroscopeResponse

    companion object{
        private val gson: Gson = GsonBuilder()
            .registerTypeAdapter(HoroscopeResponse::class.java, HoroscopeDeserializer())
            .create()

        fun getApiService() = Retrofit.Builder()
            .baseUrl("http://sandipbgt.com/theastrologer/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AstrologerApi::class.java)
    }
}