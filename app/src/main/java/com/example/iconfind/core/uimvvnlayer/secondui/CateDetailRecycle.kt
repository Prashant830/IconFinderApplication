package com.example.iconfind.core.uimvvnlayer.secondui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iconfind.R
import com.example.iconfind.core.networkdata.entity.model.Category
import com.example.iconfind.core.networkdata.entity.model.Iconset
import com.example.iconfind.core.uimvvnlayer.firstui.mainActivityRecycle

class CateDetailRecycle ( private val mList: List<Iconset>) : RecyclerView.Adapter<CateDetailRecycle.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_detail_recy_card, parent , false)
        return ViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.a_name.text = mList[position].author.name
        holder.a_type.text = mList[position].type
        //holder.premium.text = mList[position].is_premium.toString()
        holder.total.text = mList[position].icons_count.toString()


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {

        val a_name: TextView = ItemView.findViewById(R.id.autherName)
        val a_type: TextView = ItemView.findViewById(R.id.a_type)
        //val premium: TextView = ItemView.findViewById(R.id.primium)
        val total: TextView = ItemView.findViewById(R.id.total)


        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}