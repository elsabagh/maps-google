package com.example.utils

import android.content.Context
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.createBitmap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
/**
 * Converts a vector drawable resource to a BitmapDescriptor for use in Google Maps.
 *
 * @param context The context to access resources.
 * @param resId The resource ID of the vector drawable.
 * @return A BitmapDescriptor created from the vector drawable.
 * @throws IllegalArgumentException if the resource is not found or cannot be converted.
 */
fun vectorToBitmap(context: Context, @DrawableRes resId: Int): BitmapDescriptor {
    val vectorDrawable = AppCompatResources.getDrawable(context, resId)
        ?: throw IllegalArgumentException("Resource not found: $resId")

    val bitmap = createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
    vectorDrawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}