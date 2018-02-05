package net.azarquiel.steamgames.api

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by alberto on 05/02/2018.
 */
interface DriveGamesAPI {
    @GET("1xZ7O7xMmWMqKHleXgS2Ji-WMHzk3-yDCFBIVLYXjS0c/gviz/tq")
    fun getGamesDrive() : Observable<ResponseBody>

    companion object {
        fun create(): DriveGamesAPI{
            val retrofit = Retrofit.Builder()
                    //No añadimos converter debido a que necesitamos limpiar el JSON primero
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://docs.google.com/spreadsheets/d/")
                    .build()
            return retrofit.create(DriveGamesAPI::class.java)
        }
    }
}