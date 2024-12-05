package com.uni.goodzik.ui.core.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns

fun Uri.fileName(context: Context): String? {
    return if (scheme == "content") {
        context.contentResolver.query(this, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst()) cursor.getString(nameIndex) else null
        }
    } else {
        path?.substringAfterLast('/')
    }
}

fun Context.openLink(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}