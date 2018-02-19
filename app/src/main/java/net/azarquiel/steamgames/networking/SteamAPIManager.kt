package net.azarquiel.steamgames.networking

import android.util.Log
import com.google.gson.Gson
import net.azarquiel.steamgames.adapter.CustomAdapterSteam
import net.azarquiel.steamgames.api.SteamAPI
import net.azarquiel.steamgames.api.SteamStoreAPI
import net.azarquiel.steamgames.model.GameStore
import net.azarquiel.steamgames.model.SteamGameDetailed
import net.azarquiel.steamgames.views.MainActivity
import org.jetbrains.anko.Android
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 09/02/2018.
 */
class SteamAPIManager {

    private val steamApi by lazy {
        SteamAPI.create()
    }

    private val storeApi by lazy {
        SteamStoreAPI.create()
    }

    fun loadGames(){
        steamApi.getPublicApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { games ->
                            MainActivity.GAMES.clear()
                            MainActivity.GAMES.addAll(games.applist.apps.toMutableList())
                            MainActivity.DRIVE_ADAPTER.notifyDataSetChanged()
                            },
                        { error -> Log.e("ERROR", error.message) }
                )
    }

    fun getGame(id: Int){
        storeApi.getPublicGame(id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            body ->
                                var jsonTxt = body.string()
                                jsonTxt = cleanJsonTxt(jsonTxt)
                                var gameStore = Gson().fromJson(jsonTxt,GameStore::class.java)
                                CustomAdapterSteam.STEAM_GAME_DETAILED = gameStore.data
                                MainActivity.STEAM_ADAPTER.addToDrive()
                        },
                        {
                            error ->
                                Log.e("ERROR", error.message)
                        }
                )
    }

    private fun cleanJsonTxt(jsonTxt: String): String {
        val preRegex = jsonTxt.substring(0, jsonTxt.length - 1)
        val regex = "\\{\\\"success\".+".toRegex()
        var cleanJSON: String = ""
        regex.findAll(preRegex, 0).forEach { result ->
            cleanJSON = result.value
        }

        return cleanJSON
    }
}