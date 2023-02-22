package com.example.iconfind.core.uimvvnlayer.secondui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.Iconset
import com.example.iconfind.core.uimvvnlayer.firstui.MainViewModel
import com.example.iconfind.core.uimvvnlayer.thirdui.CatDetailIconActivity
import kotlinx.coroutines.*


class CateDetailActivity : AppCompatActivity() {
    lateinit var myAdapter: CateDetailRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerview: RecyclerView
    lateinit var str :String
    private lateinit var cateDetailViewModel: CateDetailViewModel
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();

        setContentView(R.layout.activity_cate_detail)

        // recyclerView declaration
        recyclerview = findViewById<RecyclerView>(R.id.idCateDetailRV)
        recyclerview.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = myLayoutManager


        progressBar = findViewById(R.id.pro1)

        val intent = intent
         str = intent.getStringExtra("name").toString()

        Log.d("hg",str.toString())


        cateDetailViewModel =  ViewModelProvider(this).get(
            CateDetailViewModel::class.java
        )

        CoroutineScope(Dispatchers.Main).launch {
            fectchResponse()
        }



    }

    private suspend fun fectchResponse() {

        progressBar.visibility = View.VISIBLE
        var cateDetailModel = cateDetailViewModel.fetchApi(str)

        Log.d("val", cateDetailViewModel.fetchApi(str)!!.iconsets.toString())

        progressBar.visibility = View.GONE
        myAdapter = CateDetailRecycle( cateDetailModel!!.iconsets)
        myAdapter.notifyDataSetChanged()
        recyclerview.adapter = myAdapter
        myAdapter.setOnItemClickListener(object : CateDetailRecycle.onItemClickListener {
            override fun onItemClick(position: Int) { val intent = Intent(applicationContext, CatDetailIconActivity::class.java)
              intent.putExtra("iconset_id", cateDetailModel!!.iconsets[position].iconset_id.toString())
            startActivity(intent)
            }
          })

    }


}