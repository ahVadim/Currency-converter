package com.example.currencyconverter.network


//class CustomJsonDeserializer : JsonDeserializer<ConvertResponse> {
//
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): ConvertResponse {
//        val coef = json?.asJsonObject?.entrySet()?.firstOrNull()?.value?.asDouble ?: -1.0
//        return ConvertResponse(coef)
//    }
//}