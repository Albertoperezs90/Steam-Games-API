package net.azarquiel.steamgames.api

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by alberto on 05/02/2018.
 */
interface SteamStoreAPI {

    @GET("appdetails/")
    fun getPublicGame(@Query("appids") appid: String) : Observable<ResponseBody>

    companion object {
        fun create(): SteamStoreAPI {
            val retrofit = Retrofit.Builder()
                    //No añadimos converter ya que este JSON hay que limpiarlo primero
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://store.steampowered.com/api/")
                    .build()
            return retrofit.create(SteamStoreAPI::class.java)
        }
    }
}