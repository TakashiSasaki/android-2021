package jp.ac.kawahara.t_sasaki.menusample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        val name = intent?.getStringExtra("menuName")
        val price = intent?.getStringExtra("menuPrice")

        findViewById<TextView>(R.id.tvMenuName).text = name
        findViewById<TextView>(R.id.tvMenuPrice).text = price
    }//onCreate

    fun onBackButtonClick(view: View){
        finish()
    }//onBackButtonClick

}