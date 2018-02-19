package net.azarquiel.steamgames.model

import android.inputmethodservice.Keyboard
import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by alberto on 05/02/2018.
 */


//Steam classes
data class SteamGames(val applist: Apps)
data class Apps(val apps: List<SteamGame>)
data class SteamGame(val appid: Int, val name: String) : SearchSuggestion {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBody(): String {
        return name
    }

    companion object CREATOR : Parcelable.Creator<SteamGame> {
        override fun createFromParcel(parcel: Parcel): SteamGame {
            return SteamGame(parcel)
        }

        override fun newArray(size: Int): Array<SteamGame?> {
            return arrayOfNulls(size)
        }
    }
}

//Clases de la Store de Steam
data class GameStore (val data: SteamGameDetailed)
data class SteamGameDetailed (val name: String,
                              val steam_appid: Int,
                              val detailed_description: String,
                              val supported_languages: String,
                              val header_image: String,
                              val initial: Int = 0
                              )


//Drive classes
data class DriveResponse (var table: Table)
data class Table (var rows: List<Rows>)
data class Rows (@SerializedName("c") var column: List<Column>)
data class Column (@SerializedName("v") var value: String, @SerializedName("f") var format: String)

//Class para volcar datos
data class GameDrive(val steam_appid: Int,
                     val name: String,
                     val description: String,
                     val languages: String,
                     val image: String,
                     val price: String,
                     val movie: String): SearchSuggestion, Serializable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBody(): String {
        return name
    }

    companion object CREATOR : Parcelable.Creator<GameDrive> {
        override fun createFromParcel(parcel: Parcel): GameDrive {
            return GameDrive(parcel)
        }

        override fun newArray(size: Int): Array<GameDrive?> {
            return arrayOfNulls(size)
        }
    }
}

