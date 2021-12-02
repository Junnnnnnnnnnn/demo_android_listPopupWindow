# 🏃[Android] ListPopupWindow demo

## ✏️ Study

🌍 Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## 🤨 Why

- `spinner` 가 아닌 `View`를 눌렀을때 `popupList` 구현

## 🙋 Try 

- `ListPopupWindow ` 를 사용해 `popupList` 구현
- `spinner` 와 `ListPopupWindow` 의 차이점 

## ✏️ AdapterLayout 설정 하기

> listpopup_layout.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <TextView
        android:id="@+id/adapter_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:text="내용"
        android:textSize="18sp"
        android:padding="10dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```



## ✏️ AdapterClass 구성

> ModuleArrayAdapter.kt

```kotlin
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
```

## ✏️ MainActivity 구성하기

### ✏️ ListPopupList로 팝업 리스트 설정 해보기

> MainActivity.kt

```kotlin
private fun initTextMenu(){
  popupList.apply {
    width = 400
    anchorView = textMenu
    setAdapter(ModuleArrayAdapter(this@MainActivity,R.layout.listpopup_layout,infoList))
    show()
  }
}
```

### ✏️ Spinner 사용하여 리스트 설정 해보기

```kotlin
    private fun textMenuEvent(){
        popupList.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this@MainActivity,
                "textView: ${infoList[position]["name"]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
```