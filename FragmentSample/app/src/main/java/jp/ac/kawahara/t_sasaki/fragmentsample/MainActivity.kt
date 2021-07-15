package jp.ac.kawahara.t_sasaki.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity", "onCreate before super.onCreate")
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate before after.onCreate")
        setContentView(R.layout.activity_main)
    }
}