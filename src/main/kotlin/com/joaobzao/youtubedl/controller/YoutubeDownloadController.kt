package com.joaobzao.youtubedl.controller

import com.sapher.youtubedl.DownloadProgressCallback
import com.sapher.youtubedl.YoutubeDL
import com.sapher.youtubedl.YoutubeDLRequest
import com.sapher.youtubedl.YoutubeDLResponse
import tornadofx.*

class YoutubeDownloadController : Controller() {

    lateinit var response: YoutubeDLResponse

    fun download(url: String, taskStatus: TaskStatus, directory: String, callback: Callback) {
        println("🙏")

        val request = YoutubeDLRequest(url, directory)
        request.setOption("ignore-errors")
        request.setOption("format mp4")
        request.setOption("output", "%(id)s.%(ext)s")
        request.setOption("retries", 10)

        runAsync(taskStatus) {
            response = YoutubeDL.execute(request) { p0, p1 ->
                val message = if (p1 != 0L) "Downloading... ETA - $p1 s" else "Download complete!"
                updateMessage(message)
                updateProgress(p0.toDouble(), 100.0)
                println("progress: $p0 and $p1")
            }
            println("🌍 ${response.out}")
            println("🌍 ${response.directory}")
        } ui {
            val downloadAbsolutePath =
                    response.directory + "/" + response.out.split(" ")[1].substringBeforeLast(":") + ".mp4"
            println(downloadAbsolutePath)
            callback.onDownloadFinish(downloadAbsolutePath)
        }
    }
}

interface Callback {
    fun onDownloadFinish(downloadAbsolutePath: String)
}