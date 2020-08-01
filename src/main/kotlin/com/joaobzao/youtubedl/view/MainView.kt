package com.joaobzao.youtubedl.view

import com.joaobzao.youtubedl.app.Styles
import com.joaobzao.youtubedl.controller.Callback
import com.joaobzao.youtubedl.controller.YoutubeDownloadController
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import tornadofx.*
import java.awt.Desktop
import java.io.File
import java.util.regex.Pattern

class MainView : View("Youtube downloader") {
    private val taskStatus: TaskStatus by inject()
    private val youtubeDownloadController: YoutubeDownloadController by inject()

    private lateinit var tfFN: TextField
    private lateinit var youtubeUrlTextField: TextField

    private val model = object : ViewModel() {
        val youtubeUrl = bind { SimpleStringProperty() }
    }

    override val root = form {

        addClass(Styles.loginScreen)
        fieldset {
            field("Youtube URL:") {
                youtubeUrlTextField = textfield(model.youtubeUrl) {
                    required()
                    promptText = "insert youtube url here..."
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

                enableWhen(youtubeUrlTextField.textProperty().isNotEmpty)
                action {
                    model.commit {
                        youtubeDownloadController.download(model.youtubeUrl.value, taskStatus, tfFN.text, object : Callback {
                            override fun onDownloadFinish(downloadAbsolutePath: String) {
                                Desktop.getDesktop().open(File(downloadAbsolutePath))
                            }
                        })
                    }
                }
            }
        }

        fieldset("Download destination") {
            field("Folder") {
                hbox {
                    tfFN = textfield(System.getProperty("user.home"))
                    button("open") {
                        action {
                            val directory = chooseDirectory("Choose destination", File(System.getProperty("user.home")))
                            directory?.let {
                                tfFN.text = directory.absolutePath
                            }
                        }
                    }
                }
            }
        }
    }
}