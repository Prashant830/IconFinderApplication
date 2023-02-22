package com.example.iconfind.core.uimvvnlayer.thirdui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.entity.model.FormatXX
import com.example.iconfind.core.networkdata.entity.model.IconX

class CatDetailIconRecycle ( val context: Context, private val mList: List<IconX>) : RecyclerView.Adapter<CatDetailIconRecycle.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_detail_icon_recy_card, parent , false)
        return ViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.iconID.text = mList[position].icon_id.toString()
        var value = mList[position].tags.toString()
        holder.icontags.text = value.substring( 1, value.length - 1 )

        val formatXX = mList[position].raster_sizes[6].formats

        Log.d("jnfj",formatXX[0].preview_url.toString())

        Glide.with(context).load(formatXX[0].preview_url).centerCrop().into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {

        val iconID : TextView = ItemView.findViewById(R.id.icon_Id)
        val icontags : TextView = ItemView.findViewById(R.id.ImageDetail)
        val imageView = ItemView.findViewById<ImageView>(R.id.iconImage)



        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}