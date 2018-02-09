package net.azarquiel.steamgames.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_row.view.*
import net.azarquiel.steamgames.model.GameDrive
import net.azarquiel.steamgames.model.SteamGame
import net.azarquiel.steamgames.model.SteamGames

/**
 * Created by alberto on 09/02/2018.
 */
class CustomAdapterSteam (val context: Context,
                          val layout: Int,
                          val games: List<SteamGame>) : RecyclerView.Adapter<CustomAdapterSteam.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = games[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return games.size
    }


    inner class ViewHolder(viewLayout: View, val context: Context) : RecyclerView.ViewHolder(viewLayout) {
        fun bind(dataItem: SteamGame, position: Int){
            itemView.tvRow.text = dataItem.name
        }
    }
}