package net.azarquiel.steamgames.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.SearchView
import com.arlib.floatingsearchview.FloatingSearchView
import net.azarquiel.steamgames.R
import net.azarquiel.steamgames.api.SteamAPI
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.steamgames.adapter.CustomAdapterSteam
import net.azarquiel.steamgames.api.DriveGamesAPI
import net.azarquiel.steamgames.api.DrivePostAPI
import net.azarquiel.steamgames.api.SteamStoreAPI
import net.azarquiel.steamgames.model.SteamGame
import net.azarquiel.steamgames.networking.SteamAPIManager


class MainActivity : AppCompatActivity(), FloatingSearchView.OnQueryChangeListener {

    private lateinit var searchView: SearchView
    private lateinit var filterList : MutableList<SteamGame>

    companion object {
        lateinit var GAMES: MutableList<SteamGame>
        lateinit var ADAPTER: CustomAdapterSteam
    }

    //Singletons
    private val steamApi by lazy {
        SteamAPIManager()
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
        progressBar.visibility = View.VISIBLE
        GAMES = mutableListOf()
        rvGames.layoutManager = LinearLayoutManager(this)
        ADAPTER = CustomAdapterSteam(this, R.layout.recycler_view_row, GAMES)
        rvGames.adapter = ADAPTER

        steamApi.loadGames()

        progressBar.visibility = View.INVISIBLE
    }


    override fun onSearchTextChanged(oldQuery: String, newQuery: String) {

        search.swapSuggestions(mutableListOf())
    }

}
