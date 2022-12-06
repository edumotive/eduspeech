package com.djinc.eduspeech.screens.ar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.djinc.eduspeech.R
import io.github.sceneview.utils.setFullScreen

class ARActivity : AppCompatActivity(R.layout.activity_ar) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = intent.extras

        setFullScreen(
            fullScreen = true,
            hideSystemBars = true,
            fitsSystemWindows = false,
            rootView = findViewById(R.id.rootView)
        )

        if (params != null) {
            val arguments = Bundle()
            arguments.putString("type", params.getString("type"))
            arguments.putString("id", params.getString("id"))
            supportFragmentManager.commit {
                add(R.id.containerFragment, ARFragment::class.java, arguments)
            }
        }
    }
}
