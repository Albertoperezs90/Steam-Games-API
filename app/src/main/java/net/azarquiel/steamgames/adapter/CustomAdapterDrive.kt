package net.azarquiel.steamgames.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.azarquiel.steamgames.model.GameDrive

/**
 * Created by alberto on 05/02/2018.
 */
class CustomAdapterDrive(val context: Context,
                         val layout: Int,
                         val games: MutableList<GameDrive>) : RecyclerView.Adapter<CustomAdapterDrive.ViewHolder>(){

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
        return games.count()
    }



    inner class ViewHolder(viewlayout: View, val context : Context) : RecyclerView.ViewHolder(viewlayout){
        fun bind(dataItem: GameDrive, position: Int){

        }
    }
}