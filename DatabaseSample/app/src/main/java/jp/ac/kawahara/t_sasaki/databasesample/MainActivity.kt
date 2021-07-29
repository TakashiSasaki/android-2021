package jp.ac.kawahara.t_sasaki.databasesample

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var _cocktailId = -1
    var _cocktailName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.lvCocktail).onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                _cocktailId = position
                _cocktailName = parent.getItemAtPosition(position) as String
                findViewById<TextView>(R.id.tvCocktailName)
                    .text = _cocktailName
                findViewById<Button>(R.id.btnSave)
                    .isEnabled = true
            }


        fun onSaveButtonClick(v: View) {
            findViewById<EditText>(R.id.etNote).setText("")
            findViewById<TextView>(R.id.tvCocktailName)
                .setText(R.string.tv_name)
            findViewById<Button>(R.id.btnSave).isEnabled = false
        }//onSaveButtonClick
    }//onCreate


}//MainActivity