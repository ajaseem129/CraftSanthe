package com.appdotlab.craftsanthe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.Feed

class FeedAdapter(
    private val feeds: List<Feed>?
): RecyclerView.Adapter<FeedHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        val layoutInflater=  LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_feed,parent,false)
        return FeedHolder(view)
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        feeds?.get(position)?.let {
            holder.onBind(it)
        }
    }
    override fun getItemCount(): Int = feeds?.size ?: 0
}

class FeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(feed: Feed){

    }

}
