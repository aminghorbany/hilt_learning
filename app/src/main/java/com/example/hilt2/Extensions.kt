package com.example.hilt2

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showShortToast(txt : String){
    Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(txt : String){
    Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
}

fun Context.showWidget(view : View){
    view.visibility = View.VISIBLE
}

fun Context.hideWidget(view : View){
    view.visibility = View.INVISIBLE
}

fun Context.goneWidget(view : View){
    view.visibility = View.GONE
}