package com.yotdark.example_listpopupwindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val textMenu: TextView by lazy {
        findViewById(R.id.text_menu)
    }

    private val spinnerMenu: Spinner by lazy {
        findViewById(R.id.spinner_menu)
    }

    private val popupList: ListPopupWindow by lazy {
        ListPopupWindow(this@MainActivity)
    }

    private var isSpinnerFirst = true

    private val infoList = mutableListOf<HashMap<String, Any>>().apply {
        add(hashMapOf("name" to "대한민국", "tag" to 1))
        add(hashMapOf("name" to "미국", "tag" to 1))
        add(hashMapOf("name" to "영국", "tag" to 1))
        add(hashMapOf("name" to "일본", "tag" to 1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinnerMenu()

        textMenu.setOnClickListener {
            initTextMenu()
        }

        textMenuEvent()
    }

    private fun initTextMenu(){
        popupList.apply {
            width = 400
            anchorView = textMenu
            setAdapter(ModuleArrayAdapter(this@MainActivity,R.layout.listpopup_layout,infoList))
            show()
        }
    }

    private fun textMenuEvent(){
        popupList.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this@MainActivity,
                "textView: ${infoList[position]["name"]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



    private fun initSpinnerMenu(){
        spinnerMenu.apply {
            adapter = ModuleArrayAdapter(this@MainActivity, R.layout.listpopup_layout, infoList)
        }

        spinnerMenu.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(!isSpinnerFirst){
                    Toast.makeText(
                        this@MainActivity,
                        "spinnerView: ${infoList[position]["name"]}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                isSpinnerFirst = false
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "spinnerView: 아무것도 선택하지 않았습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}