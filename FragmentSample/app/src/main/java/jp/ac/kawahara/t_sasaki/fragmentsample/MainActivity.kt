package jp.ac.kawahara.t_sasaki.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Log.v("hoge", "fuga") // verbose
        Log.i("MainActivity", "onCreate before super.onCreate") // informational
        //Log.d("hoge", "fuga") // debug
        //Log.w("hoge", "fuga") // warning
        //Log.e("hoge", "fuga") // error
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate after super.onCreate") // informational
        setContentView(R.layout.activity_main)
    }

}