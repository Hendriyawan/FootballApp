package com.hdev.kt.dicoding.footballapp.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.model.Events
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.text.SimpleDateFormat
import java.util.*


/**
 * set image badge
 * @param url path of image
 */
@Deprecated("BitmapDrawable")
fun ImageView.setBadge(url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.bg_banner).into(object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val layerDrawable = ContextCompat.getDrawable(context, R.drawable.bg_banner) as LayerDrawable
            val bitmapDrawable = BitmapDrawable(bitmap)
            val changed = layerDrawable.setDrawableByLayerId(R.id.layer_image_banner, bitmapDrawable)
            if (changed) setImageDrawable(layerDrawable)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    })
}

/**
 * Load image from url
 * @param url url image source
 */
fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.ic_trophy_white_24dp)
        .error(R.drawable.ic_trophy_white_24dp)
        .into(this)
}


//Covert String to Array
fun String.arrayValue(): Array<String> = substring(0, length - 1).split(";").toTypedArray()

//get formation home string if default value is null
fun Events.getHomeFormation(): String {
    return if (homeDefense!!.isEmpty() && homeMidfield!!.isEmpty() && homeForward!!.isEmpty()) {
        "null - null - null"
    } else {
        val defense = homeDefense!!.arrayValue().size
        val midfield = homeMidfield!!.arrayValue().size
        val forward = homeForward!!.arrayValue().size
        "$defense-$midfield-$forward"
    }
}

//get formation away string if default value is null
fun Events.getAwayFormation(): String {
    return if (awayDefense!!.isEmpty() && awayMidfield!!.isEmpty() && awayForward!!.isEmpty()) {
        "null - null - null"
    } else {
        val defense = awayDefense!!.arrayValue().size
        val midfield = awayMidfield!!.arrayValue().size
        val forward = awayForward!!.arrayValue().size
        "$defense-$midfield-$forward"
    }
}

// set LayoutManager RecyclerView
fun RecyclerView.setLayoutManager() {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

//format date
fun dateFormat(strDate: String, strTime: String?, mode: String): String {
    val srcFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val nullFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val destFormat = SimpleDateFormat(mode, Locale("in", "ID"))
    srcFormat.timeZone = TimeZone.getTimeZone("GMT")
    destFormat.timeZone = TimeZone.getTimeZone("GMT+07:00")

    return if (strTime == null) {
        val date = nullFormat.parse(strDate)
        srcFormat.format(date)

    } else {
        val dateTime = "$strDate $strTime"
        destFormat.format(srcFormat.parse(dateTime))
    }
}

fun View.show(show: Boolean) {
    if (show) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}