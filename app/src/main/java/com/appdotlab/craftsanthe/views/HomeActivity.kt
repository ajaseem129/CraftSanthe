package com.appdotlab.craftsanthe.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appdotlab.craftsanthe.adapters.FeedAdapter
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.Feed
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    companion object{
        val TAG:String=HomeActivity::class.java.simpleName
    }
    lateinit var rvFeed:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
    }
    fun initViews()
    {
        rvFeed= rv_feed
        rvFeed.layoutManager = LinearLayoutManager(this)
        rvFeed.adapter = FeedAdapter(emptyList<Feed>())
    }
}