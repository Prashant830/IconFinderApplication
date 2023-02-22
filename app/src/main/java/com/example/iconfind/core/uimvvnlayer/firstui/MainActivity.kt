package com.example.iconfind.core.uimvvnlayer.firstui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.Category
import com.example.iconfind.core.uimvvnlayer.secondui.CateDetailActivity
import com.example.iconfind.core.uimvvnlayer.uforthui.SearchActivity
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: mainActivityRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    var newArrayList: ArrayList<Category> = ArrayList<Category>()
    lateinit var recyclerview : RecyclerView
    private lateinit var mainViewModel: MainViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // recyclerView declaration
        recyclerview = findViewById<RecyclerView>(R.id.idCateRV)
        recyclerview.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = myLayoutManager

        progressBar = findViewById(R.id.pro)

        mainViewModel =  ViewModelProvider(this).get(
            MainViewModel::class.java
        )


        CoroutineScope(Dispatchers.Main).launch {
            fectchResponse()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.serch -> {
                intent = Intent(applicationContext,SearchActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private suspend fun fectchResponse() {
        progressBar?.visibility = View.VISIBLE

        var categoriesModel = mainViewModel.fetchApi()

        myAdapter = mainActivityRecycle(categoriesModel!!.categories)
        progressBar?.visibility = View.GONE

        myAdapter.notifyDataSetChanged()
        recyclerview.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : mainActivityRecycle.onItemClickListener {
            override fun onItemClick(position: Int) {
                intent = Intent(applicationContext, CateDetailActivity::class.java)
                intent.putExtra("name", categoriesModel!!.categories[position].identifier.toString())
                startActivity(intent)
            }
        })

          }


    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    override fun onStart() {
        super.onStart()
        if (checkForInternet(this)) {
            Toast.makeText(this, "Internet is Connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Internet is Disconnected so open Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}