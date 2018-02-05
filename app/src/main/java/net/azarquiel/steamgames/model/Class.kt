package net.azarquiel.steamgames.model

/**
 * Created by alberto on 05/02/2018.
 */


//Steam classes
data class SteamGames(val applist: Apps)
data class Apps(val apps: List<SteamGame>)
data class SteamGame(val appid: Int, val name: String)

//Drive classes
data class GameDrive(val id: Int=0)