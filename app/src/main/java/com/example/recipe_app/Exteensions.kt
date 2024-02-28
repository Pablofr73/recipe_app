package com.example.recipe_app

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Drawable.setTintForMode(context: Context) {
    val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    val colorRes = if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
        R.color.white
    } else {
        R.color.black
    }
    DrawableCompat.setTint(this, ContextCompat.getColor(context, colorRes))
}
