package jp.ac.kawahara.t_sasaki.asyncsample

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEBUG_TAG = "AsyncSample"
        private const val WEATHERINFO_URL =
            "https://api.openweathermap.org/data/2.5/weather?lang=ja"
        private const val APP_ID = "45e1a251abcf21306d5e1497fd4c3457"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _list = createList()

        val lv = findViewById<ListView>(R.id.lvCityList)
        lv.adapter = SimpleAdapter(this, _list,
            android.R.layout.simple_list_item_1,
            arrayOf("name"),
            intArrayOf(android.R.id.text1))
        
    }//onCreate

    var _list: MutableList<MutableMap<String, String>> = mutableListOf()

    private fun createList(): MutableList<MutableMap<String, String>>{
        var list = mutableListOf<MutableMap<String, String>>()
        list.add(mutableMapOf("name" to "大阪", "q" to "Osaka"))
        list.add(mutableMapOf("name" to "神戸", "q" to "Kobe"))
        list.add(mutableMapOf("name" to "松山", "q" to "Matsuyama"))
        return list
    }
}