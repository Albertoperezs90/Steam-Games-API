package net.azarquiel.steamgames.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.arlib.floatingsearchview.FloatingSearchView
import net.azarquiel.steamgames.R
import net.azarquiel.steamgames.api.SteamAPI
import net.azarquiel.steamgames.model.SteamGames
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.steamgames.api.DriveGamesAPI
import net.azarquiel.steamgames.api.DrivePostAPI
import net.azarquiel.steamgames.api.SteamStoreAPI


class MainActivity : AppCompatActivity(), FloatingSearchView.OnQueryChangeListener {


    private lateinit var searchView: SearchView
    private lateinit var games: SteamGames

    //Singletons
    private val steamApi by lazy {
        SteamAPI.create()
    }

    private val steamStoreApi by lazy {
        SteamStoreAPI.create()
    }

    private val driveGamesApi by lazy {
        DriveGamesAPI.create()
    }

    private val drivePostApi by lazy {
        DrivePostAPI.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search.setOnQueryChangeListener(this)
    }

    override fun onSearchTextChanged(oldQuery: String, newQuery: String) {
        //TODO get suggestion based on newQuery
        search.swapSuggestions(mutableListOf())
    }

}
