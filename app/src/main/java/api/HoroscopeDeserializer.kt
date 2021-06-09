package api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HoroscopeDeserializer: JsonDeserializer<HoroscopeResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): HoroscopeResponse {

        val response = json?.asJsonObject
        val horoscope = response?.get("horoscope")?.asString
        val horoscopeResponse = HoroscopeResponse()
        horoscopeResponse.horoscope = horoscope ?: ""

        return horoscopeResponse
    }
}