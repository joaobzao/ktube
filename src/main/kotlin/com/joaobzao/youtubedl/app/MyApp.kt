package com.joaobzao.youtubedl.app

import com.joaobzao.youtubedl.view.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class)

fun main(args: Array<String>) {
    launch<MyApp>(args)
}