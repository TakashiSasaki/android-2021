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
        Log.i("MainActivity", savedInstanceState?.getString("myname", "unknown").toString())
        Log.i("MainActivity", "onCreate after super.onCreate") // informational
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.i("MainActivity", "onStart before super.onStart") // informational
        super.onStart()
        Log.i("MainActivity", "onStart after super.onStart") // informational
    }

    override fun onResume() {
        Log.i("MainActivity", "onResume before super.onResume") // informational
        super.onResume()
        Log.i("MainActivity", "onResume after super.onResume") // informational
    }

    override fun onRestart() {
        Log.i("MainActivity", "onRestart before super.onRestart") // informational
        super.onRestart()
        Log.i("MainActivity", "onRestart after super.onRestart") // informational
    }

    override fun onPause() {
        Log.i("MainActivity", "onPause before super.onPause") // informational
        super.onPause()
        Log.i("MainActivity", "onPause after super.onPause") // informational
    }

    override fun onStop() {
        Log.i("MainActivity", "onStop before super.onStop") // informational
        super.onStop()
        Log.i("MainActivity", "onStop after super.onStop") // informational
    }

    override fun onDestroy() {
        Log.i("MainActivity", "onDestroy before super.onDestroy") // informational
        super.onDestroy()
        Log.i("MainActivity", "onDestroy after super.onDestroy") // informational
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("MainActivity", "onSaveInstanceState before super.onSaveInstanceState") // informational
        outState.putString("myname", "takashi")
        super.onSaveInstanceState(outState)
        Log.i("MainActivity", "onSaveInstanceState after super.onSaveInstanceState") // informational
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i("MainActivity", "onRestoreInstanceState before super.onRestoreInstanceState") // informational
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity", "onRestoreInstanceState before super.onRestoreInstanceState") // informational
    }
}