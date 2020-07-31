package com.joaobzao.youtubedl.view

import com.joaobzao.youtubedl.app.Styles
import com.joaobzao.youtubedl.controller.YoutubeDownloadController
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainView : View("Youtube downloader") {
    private val youtubeDownloadController: YoutubeDownloadController by inject()

    private val model = object : ViewModel() {
        val youtubeUrl = bind { SimpleStringProperty() }
    }

    override val root = form {
        label(title) {
            addClass(Styles.heading)
        }
        addClass(Styles.loginScreen)
        fieldset {
            field("Youtube URL") {
                textfield(model.youtubeUrl) {
                    required()
                    whenDocked { requestFocus() }
                }
            }
        }

        button("Download") {
            isDefaultButton = true

            action {
                model.commit {
                    youtubeDownloadController.download(model.youtubeUrl.value)
                }
            }
        }
    }
}