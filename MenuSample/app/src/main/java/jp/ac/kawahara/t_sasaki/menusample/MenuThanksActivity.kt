package jp.ac.kawahara.t_sasaki.menusample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        val name = intent?.getStringExtra("menuName")
        val price = intent?.getIntExtra("menuPrice", 0)

        findViewById<TextView>(R.id.tvMenuName).text = name
        findViewById<TextView>(R.id.tvMenuPrice).text = "" + price

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }//onCreate

    fun onBackButtonClick(view: View){
        finish()
    }//onBackButtonClick

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }//if
    }//onOptionsItemSelected
}//MenuThanksActivity
