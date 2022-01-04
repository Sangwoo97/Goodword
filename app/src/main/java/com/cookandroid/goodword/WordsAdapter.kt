package com.cookandroid.goodword

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class WordsAdapter(var context: Context?, var arrayList: ArrayList<WordsItem>) : BaseAdapter(){

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view : View = View.inflate(context , R.layout.cardview_item_grid, null)
        var icons:ImageView = view.findViewById(R.id.icons)
        var names:TextView = view.findViewById(R.id.card_text)

        var listItem:WordsItem = arrayList.get(p0)
        Glide.with(view)
            .load(listItem.icons)
            .into(icons)
        //icons.setImageResource(listItem.icons!!)
        names.text = listItem.name

        return view
    }
}