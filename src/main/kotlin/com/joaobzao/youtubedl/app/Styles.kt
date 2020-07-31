package com.joaobzao.youtubedl.app

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val loginScreen by cssclass()
        val progressBar by cssclass()
    }

    init {
        label and heading {
            padding = box(20.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }

        loginScreen {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
            minWidth = 800.px
        }

        progressBar {
            //padding = box(15.px)
            minWidth = 500.px
        }
    }
}