package com.example.iconfind.core.uimvvnlayer.firstui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.entity.model.Category

//class mainActivityRecycle{
class mainActivityRecycle( private val mList: List<Category>) : RecyclerView.Adapter<mainActivityRecycle.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

   interface onItemClickListener{
       fun onItemClick(position: Int)
   }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_recy_card_view, parent , false)
        return ViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cateName.text = mList[position].name.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {

        val cateName: TextView = ItemView.findViewById(R.id.cateName)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}