package com.uni.goodzik.ui.core.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

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

suspend fun downloadFileToDownloads(
    url: String,
    filename: String? = null
): String? = withContext(Dispatchers.IO) {
    runCatching {
        val connection = URL(url).openConnection()
        val outputFilename = filename ?: URLDecoder.decode(
            File(URL(url).path).name.ifEmpty { "downloaded_file" },
            StandardCharsets.UTF_8.name()
        )

        val downloadDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadDir, outputFilename)

        connection.getInputStream().use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        file.absolutePath
    }.getOrNull()
}

fun Context.openDesktopSite(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        addCategory(Intent.CATEGORY_BROWSABLE)
        putExtra("desktop_mode", true)
        putExtra("HeaderName", "User-Agent")
        putExtra("HeaderValue", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    }
    startActivity(intent)
}