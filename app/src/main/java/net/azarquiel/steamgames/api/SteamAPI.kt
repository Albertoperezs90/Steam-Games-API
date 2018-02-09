package net.azarquiel.steamgames.api

import android.util.Log
import net.azarquiel.steamgames.model.SteamGames
import net.azarquiel.steamgames.views.MainActivity
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 05/02/2018.
 */
interface SteamAPI {

    @GET("ISteamApps/GetAppList/v2/")
    fun getPublicApps() : Observable<SteamGames>


    companion object {

        fun create(): SteamAPI {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://api.steampowered.com/")
                    .build()
            return retrofit.create(SteamAPI::class.java)
        }


    }

}