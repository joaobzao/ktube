package com.joaobzao.youtubedl.view

import com.joaobzao.youtubedl.app.Styles
import com.joaobzao.youtubedl.controller.YoutubeDownloadController
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import java.awt.Label

class MainView : View("Youtube downloader") {
    private val taskStatus: TaskStatus by inject()
    private val youtubeDownloadController: YoutubeDownloadController by inject()

    private val model = object : ViewModel() {
        val youtubeUrl = bind { SimpleStringProperty() }
    }

    override val root = form {
        hbox(alignment = Pos.CENTER) {
            label(title) {
                addClass(Styles.heading)
                whenDocked { requestFocus() }
            }
        }
        addClass(Styles.loginScreen)
        fieldset {
            field("Youtube URL:") {
                textfield(model.youtubeUrl) {
                    required()
                    promptText = "insert youtube url here..."
                    //whenDocked { requestFocus() }
                }
            }
        }

        vbox(4.0) {
            addClass(Styles.progressBar)
            progressbar(taskStatus.progress) {
                visibleWhen { taskStatus.running }
            }
            label(taskStatus.message)
        }

        hbox(alignment = Pos.BASELINE_RIGHT) {
            button("Download") {
                isDefaultButton = true

                action {
                    model.commit {
                        youtubeDownloadController.download(model.youtubeUrl.value, taskStatus)
                    }
                }
            }
        }
    }
}