package com.joaobzao.youtubedl.view

import com.joaobzao.youtubedl.app.Styles
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}