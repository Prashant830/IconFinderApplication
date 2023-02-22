package com.example.iconfind.core.uimvvnlayer.thirdui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.IconDetailModel
import com.example.iconfind.core.uimvvnlayer.secondui.CateDetailRecycle
import com.example.iconfind.core.uimvvnlayer.secondui.CateDetailViewModel
import kotlinx.coroutines.*

class CatDetailIconActivity : AppCompatActivity() {
    lateinit var myAdapter: CatDetailIconRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerview: RecyclerView
    lateinit var str :String
    lateinit var catDetailIcoViewModel: CatDetailIcoViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();

        setContentView(R.layout.activity_cat_detail_icon)

        val intent = intent
        str = intent.getStringExtra("iconset_id").toString()

        // recyclerView declaration
        recyclerview = findViewById<RecyclerView>(R.id.idCateDetailRV)
        recyclerview.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = myLayoutManager

        progressBar = findViewById(R.id.pro2)

        catDetailIcoViewModel = ViewModelProvider(this).get(
            CatDetailIcoViewModel::class.java
        )

        Log.d("hg",str.toString())
        CoroutineScope(Dispatchers.Main).launch {
            fectchResponse()
        }
    }

    private suspend fun fectchResponse() {

        progressBar.visibility = View.VISIBLE
        var iconX = catDetailIcoViewModel.fetchApi(str)

        progressBar.visibility = View.GONE
        myAdapter = CatDetailIconRecycle( this,iconX!!.icons)
        myAdapter.notifyDataSetChanged()
        recyclerview.adapter = myAdapter
        myAdapter.setOnItemClickListener(object : CatDetailIconRecycle.onItemClickListener {
            override fun onItemClick(position: Int) { val intent = Intent(applicationContext, CatDetailIconActivity::class.java)
                Toast.makeText(baseContext, "You Can't have permission for download Images Because it's have premium", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
