package com.joaobzao.youtubedl.controller

import com.sapher.youtubedl.YoutubeDL
import com.sapher.youtubedl.YoutubeDLRequest
import tornadofx.*

class YoutubeDownloadController : Controller() {

    fun download(url: String, directory: String = System.getProperty("user.home")) {
        println("🙏")

        val request = YoutubeDLRequest(url, directory)
        request.setOption("ignore-errors")
        request.setOption("format mp4")
        request.setOption("output", "%(id)s.%(ext)s")
        request.setOption("retries", 10)

        val response = YoutubeDL.execute(request) { p0, p1 -> println("progress: $p0 and $p1") }

        println("🌍 ${response.out}")
        println("🌍 ${response.directory}")
    }
}