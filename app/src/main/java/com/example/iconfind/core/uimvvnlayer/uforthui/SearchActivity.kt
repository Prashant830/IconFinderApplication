package com.example.iconfind.core.uimvvnlayer.uforthui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.iconfind.R
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.Icon
import com.example.iconfind.core.uimvvnlayer.thirdui.CatDetailIcoViewModel
import com.example.iconfind.core.uimvvnlayer.thirdui.CatDetailIconActivity
import com.example.iconfind.core.uimvvnlayer.uififthui.DownloadIconActivity
import kotlinx.coroutines.*

class SearchActivity : AppCompatActivity() {

    lateinit var myAdapter: SerachRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerview: RecyclerView
    lateinit var searchView : SearchView
    lateinit var src : String
    lateinit var searchViewModel: SearchViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide();

        setContentView(R.layout.activity_search)

        // recyclerView declaration
        recyclerview = findViewById<RecyclerView>(R.id.idSerchRV)
        recyclerview.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = myLayoutManager

        progressBar = findViewById(R.id.pro3)
         searchView = findViewById<SearchView>(R.id.SearchView)

        searchViewModel = ViewModelProvider(this).get(
            SearchViewModel::class.java
        )


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                src = query.toString()

                CoroutineScope(Dispatchers.Main).launch {
                     fectchResponse(src)
                }
                    return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                src = newText.toString()
                CoroutineScope(Dispatchers.Main).launch {
                    fectchResponse(src)
                }
                return false
            }

        })

    }

    private suspend fun fectchResponse(src: String) {


        val map = mapOf("query" to src, "count" to 10)

        progressBar.visibility = View.VISIBLE
        val icon = searchViewModel.fetchApi(map as Map<String, String>)!!.icons

        progressBar.visibility = View.GONE

        myAdapter = SerachRecycle( this,icon)
        myAdapter.notifyDataSetChanged()
        recyclerview.adapter = myAdapter
        myAdapter.setOnItemClickListener(object : SerachRecycle.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, CatDetailIconActivity::class.java)
                val v = icon[position].is_premium.toString()
                if (v == "true") {
                    Toast.makeText(
                        baseContext,
                        "You Can't have permission for download Images Because it's have premium",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "You have a permission for download Images Because it's have download option",
                        Toast.LENGTH_SHORT
                    ).show()

                    var intent = Intent(applicationContext,DownloadIconActivity::class.java)

                    var formats = icon[position].raster_sizes[0].formats
                    var format1 = icon[position].raster_sizes[1].formats
                    var format2 = icon[position].raster_sizes[2].formats
                    var format3 = icon[position].raster_sizes[3].formats
                    var format4 = icon[position].raster_sizes[4].formats
                    var format5 = icon[position].raster_sizes[5].formats
                    var format6 = icon[position].raster_sizes[6].formats
                    var format7 = icon[position].raster_sizes[7].formats
                    var format8 = icon[position].raster_sizes[8].formats
                    var format9 = icon[position].raster_sizes[9].formats

                    Log.d("jnjl",formats[0].download_url.toString())
                    Log.d("jnjl",format1[0].download_url.toString())
                    Log.d("jnjl",format2[0].download_url.toString())
                    Log.d("jnjl",format3[0].download_url.toString())
                    Log.d("jnjl",format4[0].download_url.toString())
                    Log.d("jnjl",format5[0].download_url.toString())
                    Log.d("jnjl",format6[0].download_url.toString())
                    Log.d("jnjl",format7[0].download_url.toString())
                    Log.d("jnjl",format8[0].download_url.toString())
                    Log.d("jnjl",format9[0].download_url.toString())



                    intent.putExtra("16",formats[0].download_url.toString())
                    intent.putExtra("20",format1[0].download_url.toString())
                    intent.putExtra("24",format2[0].download_url.toString())
                    intent.putExtra("32",format3[0].download_url.toString())
                    intent.putExtra("48",format4[0].download_url.toString())
                    intent.putExtra("64",format5[0].download_url.toString())
                    intent.putExtra("128",format6[0].download_url.toString())
                    intent.putExtra("256",format7[0].download_url.toString())
                    intent.putExtra("512",format8[0].download_url.toString())
                    intent.putExtra("1024",format9[0].download_url.toString())

                    startActivity(intent)

                }

            }
          })
        }
    }
