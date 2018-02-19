package net.azarquiel.steamgames.views

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.SearchView
import com.arlib.floatingsearchview.FloatingSearchView
import net.azarquiel.steamgames.R
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.steamgames.adapter.CustomAdapterDrive
import net.azarquiel.steamgames.adapter.CustomAdapterSteam
import net.azarquiel.steamgames.api.DriveGamesAPI
import net.azarquiel.steamgames.api.DrivePostAPI
import net.azarquiel.steamgames.api.SteamStoreAPI
import net.azarquiel.steamgames.model.GameDrive
import net.azarquiel.steamgames.model.SteamGame
import net.azarquiel.steamgames.networking.DriveAPIManager
import net.azarquiel.steamgames.networking.SteamAPIManager


class MainActivity : AppCompatActivity(), FloatingSearchView.OnQueryChangeListener, FloatingSearchView.OnMenuItemClickListener {

    private lateinit var searchView: SearchView
    private var driveRV: Boolean = true

    companion object {
        lateinit var GAMES: MutableList<SteamGame>
        lateinit var GAMES_DRIVE: MutableList<GameDrive>
        lateinit var DRIVE_ADAPTER: CustomAdapterDrive
        lateinit var STEAM_ADAPTER: CustomAdapterSteam
    }

    //Singletons
    val steamApi by lazy {
        SteamAPIManager()
    }

    val driveApi by lazy {
        DriveAPIManager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search.setOnQueryChangeListener(this)

        search.setOnMenuItemClickListener(this)

        loadData()

    }

    override fun onActionMenuItemSelected(item: MenuItem) {
        //TODO refresh icon on change
        if (driveRV){
            driveApi.getGames()
            item.icon = ContextCompat.getDrawable(this, R.drawable.steam)
            rvGames.adapter = STEAM_ADAPTER
            driveRV = false
        }else {
            item.icon = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on)
            rvGames.adapter = DRIVE_ADAPTER
            driveRV = true
        }
    }

    override fun onSearchTextChanged(oldQuery: String, newQuery: String) {
        if (newQuery.trim().length > 0){
            layoutRV.visibility = View.INVISIBLE
            layoutRV.isEnabled = false
        }else {
            layoutRV.visibility = View.VISIBLE
            layoutRV.isEnabled = true
        }

        GAMES = GAMES.filter { it.name.startsWith(newQuery) }.toMutableList()


        search.swapSuggestions(GAMES)
    }

    fun loadData(){
        progressBar.visibility = View.VISIBLE
        GAMES = mutableListOf()
        GAMES_DRIVE = mutableListOf()
        rvGames.layoutManager = LinearLayoutManager(this)
        DRIVE_ADAPTER = CustomAdapterDrive(this,R.layout.row_drive, GAMES_DRIVE)
        STEAM_ADAPTER = CustomAdapterSteam(this, R.layout.row_steam, GAMES)
        rvGames.adapter = DRIVE_ADAPTER

        driveApi.getGames()
        steamApi.loadGames()

        rvGames.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            Log.d("Rv terminado","Terminado")
            progressBar.visibility = View.INVISIBLE
        })
    }

}
