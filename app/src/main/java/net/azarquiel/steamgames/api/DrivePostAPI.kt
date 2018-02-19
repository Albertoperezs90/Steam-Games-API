package net.azarquiel.steamgames.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by alberto on 05/02/2018.
 */
interface DrivePostAPI {

    @FormUrlEncoded
    @POST("formResponse")
    fun saveGame(
            @Field("entry.1356769397") steam_appid: String,
            @Field("entry.179955454") name: String,
            @Field("entry.1874838974") description: String,
            @Field("entry.1504091838") languages: String,
            @Field("entry.2096260004") image: String,
            @Field("entry.1902952412") price: String,
            @Field("entry.1813775853") movie: String
    ): Observable<String>

    companion object {
        fun create(): DrivePostAPI {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://docs.google.com/forms/d/e/1FAIpQLSct0kfQVVZz_STd42gaxaXPJHtmKTb5jzS9rEb8c5GlK3XQ5w/")
                    .build()

            return retrofit.create(DrivePostAPI::class.java)
        }
    }
}