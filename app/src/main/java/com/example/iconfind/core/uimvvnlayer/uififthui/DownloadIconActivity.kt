package com.example.iconfind.core.uimvvnlayer.uififthui

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import java.io.File


class DownloadIconActivity : AppCompatActivity() {

    lateinit var list : List<String>
    lateinit var list2 : List<String>
    lateinit var myAdapter: DownloadIconRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide();
        setContentView(R.layout.activity_download_icon)

        // recyclerView declaration
        recyclerview = findViewById<RecyclerView>(R.id.idIconDownloadRV)
        recyclerview.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = myLayoutManager

        var intent = intent

        list = listOf((intent.getStringExtra("16")).toString(),
            (intent.getStringExtra("20")).toString(),
            (intent.getStringExtra("24")).toString(),
            (intent.getStringExtra("32")).toString(),
            (intent.getStringExtra("48")).toString(),
            (intent.getStringExtra("64")).toString(),
            (intent.getStringExtra("128")).toString(),
            (intent.getStringExtra("256")).toString(),
            (intent.getStringExtra("512")).toString(),
            (intent.getStringExtra("1024")).toString()
        )

        list2 = listOf( "16X16",
            "24X24",
            "32X32",
            "48X48",
            "64X64",
            "128X128",
            "256X256",
            "512X512",
            "1024X1024"
        )

        myAdapter = DownloadIconRecycle(list2)
        myAdapter.notifyDataSetChanged()
        recyclerview.adapter = myAdapter
        myAdapter.setOnItemClickListener(object : DownloadIconRecycle.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(baseContext, list2[position].toString() + " Icon Size start Downloaded ", Toast.LENGTH_SHORT).show()
                 var url = list[position].toString()
                DonloadImage("IconFinder", url)
            }
        })

    }

     fun DonloadImage(fileNmae: String, url: String)
         {
         try {
             var manager:DownloadManager? = null


             manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
             var uri : Uri = Uri.parse(url)

             val request = DownloadManager.Request(uri)

             request.setAllowedOverRoaming(false)
                 .setTitle(fileNmae)
                 .setMimeType ("image/png")
                 .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                 .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator+fileNmae+".png")


             manager!!.enqueue(request)

             Toast.makeText( this,  "Image Download Done", Toast.LENGTH_SHORT).show ()


         }catch (e : Exception){
             Toast.makeText(this, "Image Downloading Fail", Toast.LENGTH_SHORT).show()
         }
     }


}