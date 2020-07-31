package com.joaobzao.youtubedl.controller

import com.sapher.youtubedl.YoutubeDL
import com.sapher.youtubedl.YoutubeDLRequest
import tornadofx.*

class YoutubeDownloadController : Controller() {

    fun download(url: String, taskStatus: TaskStatus, directory: String = System.getProperty("user.home")) {
        println("🙏")

        val request = YoutubeDLRequest(url, directory)
        request.setOption("ignore-errors")
        request.setOption("format mp4")
        request.setOption("output", "%(id)s.%(ext)s")
        request.setOption("retries", 10)

        runAsync(taskStatus) {
            val response = YoutubeDL.execute(request) { p0, p1 ->
                val message = if (p1 != 0L) "Downloading... ETA - $p1 s" else "Download complete!"
                updateMessage(message)
                updateProgress(p0.toDouble(), 100.0)
                println("progress: $p0 and $p1")
            }
            println("🌍 ${response.out}")
            println("🌍 ${response.directory}")
        }



    }
}