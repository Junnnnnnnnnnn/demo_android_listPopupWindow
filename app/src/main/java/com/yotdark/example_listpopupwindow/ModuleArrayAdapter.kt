package com.yotdark.example_listpopupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class ModuleArrayAdapter(
    context: Context,
    @LayoutRes private val res: Int,
    private val infoList: MutableList<HashMap<String, Any>>
): ArrayAdapter<HashMap<String, Any>>(context, res, infoList) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(
            res,
            parent,
            false
        )
        view.findViewById<TextView>(R.id.adapter_text).text = infoList[position]["name"].toString()

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(
            res,
            parent,
            false
        )

        view.findViewById<TextView>(R.id.adapter_text).text = infoList[position]["name"].toString()

        return view
    }
}