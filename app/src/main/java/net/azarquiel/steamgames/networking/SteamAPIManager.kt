package net.azarquiel.steamgames.networking

import android.util.Log
import net.azarquiel.steamgames.api.SteamAPI
import net.azarquiel.steamgames.views.MainActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 09/02/2018.
 */
class SteamAPIManager {

    private val steamApi by lazy {
        SteamAPI.create()
    }

    fun loadGames(){
        steamApi.getPublicApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { games ->
                            MainActivity.GAMES.clear()
                            MainActivity.GAMES.addAll(games.applist.apps.toMutableList())
                            MainActivity.ADAPTER.notifyDataSetChanged() },
                        { error -> Log.e("ERROR", error.message) }
                )
    }
}