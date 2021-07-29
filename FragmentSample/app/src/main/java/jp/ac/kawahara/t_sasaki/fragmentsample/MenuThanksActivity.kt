package jp.ac.kawahara.t_sasaki.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MenuThanksActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)
    }

    override fun onStart() {
        Log.i("MenuThanksActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("MenuThanksActivity", "onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.i("MenuThanksActivity", "onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.i("MenuThanksActivity", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("MenuThanksActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("MenuThanksActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("MenuThanksActivity", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i("MenuThanksActivity", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

}