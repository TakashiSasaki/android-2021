package jp.ac.kawahara.t_sasaki.intentsample

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private inner class CustomViewBinder : SimpleAdapter.ViewBinder{
        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        //val menuList2 = mutableListOf<MutableMap<String, String>>()

        menuList.add(mutableMapOf("name" to "からあげ定食", "price" to "800円"))
        menuList.add(mutableMapOf("name" to "ハンバーグ定食", "price" to "850円"))


        val adapter = SimpleAdapter(
            this, menuList,
            android.R.layout.simple_list_item_2,
            arrayOf("name", "price"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        adapter.viewBinder = CustomViewBinder()

        findViewById<ListView>(R.id.lvMenu).adapter = adapter
    }//onCreate
}//MainActivity
