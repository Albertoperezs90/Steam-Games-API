package net.azarquiel.steamgames.networking

import android.util.Log
import com.google.gson.Gson
import net.azarquiel.steamgames.api.DriveGamesAPI
import net.azarquiel.steamgames.api.DrivePostAPI
import net.azarquiel.steamgames.model.DriveResponse
import net.azarquiel.steamgames.model.GameDrive
import net.azarquiel.steamgames.model.SteamGameDetailed
import net.azarquiel.steamgames.views.MainActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 19/02/2018.
 */
class DriveAPIManager {

    val driveAPI by lazy {
        DriveGamesAPI.create()
    }

    val drivePostAPI by lazy {
        DrivePostAPI.create()
    }

    fun getGames(){
        driveAPI.getGamesDrive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    body ->
                        var jsonTxt = body.string()
                        jsonTxt = cleanDriveTxt(jsonTxt)
                        val response: DriveResponse = Gson().fromJson(jsonTxt, DriveResponse::class.java)
                        MainActivity.GAMES_DRIVE.clear()
                        response.table.rows.forEach { game ->
                            val gameDrive: GameDrive = GameDrive(game.column[1].value.toInt(),
                                                                 game.column[2].value,
                                                                 game.column[3].value,
                                                                 game.column[4].value,
                                                                 game.column[5].value,
                                                                 game.column[6].value,
                                                                 game.column[7].value)
                            MainActivity.GAMES_DRIVE.add(gameDrive)
                        }
                        MainActivity.DRIVE_ADAPTER.notifyDataSetChanged()
                    },
                        { error ->
                            Log.e("ERROR", error.message)
                        })
    }

    fun uploadGame(steamGame: SteamGameDetailed){
        drivePostAPI.saveGame("\'${steamGame.steam_appid}",
                              steamGame.name,
                              steamGame.detailed_description,
                              steamGame.supported_languages,
                              steamGame.header_image,
                              steamGame.initial.toString(),
                              "h")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    body ->
                        Log.d("RESULT", body)
                }, {
                    error ->
                        Log.e("ERROR", error.message)
                })

    }

    private fun cleanDriveTxt(jsonTxt: String): String {
        val regex= "\\{.+([^);])".toRegex()
        var cleanJSON: String = ""
        regex.findAll(jsonTxt, 0).forEach { result ->
            cleanJSON = result.value
        }

        return cleanJSON
    }
}