# π[Android] ListPopupWindow demo

## βοΈ Study

π Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## π€¨ Why

- `spinner` κ° μλ `View`λ₯Ό λλ μλ `popupList` κ΅¬ν

## π Try 

- `ListPopupWindow ` λ₯Ό μ¬μ©ν΄ `popupList` κ΅¬ν
- `spinner` μ `ListPopupWindow` μ μ°¨μ΄μ  

## βοΈ AdapterLayout μ€μ  νκΈ°

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
        android:text="λ΄μ©"
        android:textSize="18sp"
        android:padding="10dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```



## βοΈ AdapterClass κ΅¬μ±

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

## βοΈ MainActivity κ΅¬μ±νκΈ°

### βοΈ ListPopupListλ‘ νμ λ¦¬μ€νΈ μ€μ  ν΄λ³΄κΈ°

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

### βοΈ Spinner μ¬μ©νμ¬ λ¦¬μ€νΈ μ€μ  ν΄λ³΄κΈ°

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