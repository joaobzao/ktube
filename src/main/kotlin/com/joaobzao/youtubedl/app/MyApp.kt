package com.joaobzao.youtubedl.app

import com.joaobzao.youtubedl.view.MainView
import com.sapher.youtubedl.DownloadProgressCallback
import com.sapher.youtubedl.YoutubeDL
import com.sapher.youtubedl.YoutubeDLRequest
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {
    // Video url to download
    val videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"

// Destination directory
    val directory = System.getProperty("user.home")


    override fun start(stage: Stage) {
        super.start(stage)
        println("🙏")

        val request = YoutubeDLRequest(videoUrl, directory)
        request.setOption("ignore-errors")
        request.setOption("format mp4")
        request.setOption("output", "%(id)s.%(ext)s")
        request.setOption("retries", 10)

        val response = YoutubeDL.execute(request) { p0, p1 -> println("progress: $p0 and $p1") }

        println("🌍 ${response.out}")
        println("🌍 ${response.directory}")
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}