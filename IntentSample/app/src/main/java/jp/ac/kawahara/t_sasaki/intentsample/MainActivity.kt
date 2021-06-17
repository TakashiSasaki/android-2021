package jp.ac.kawahara.t_sasaki.intentsample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private inner class CustomViewBinder : SimpleAdapter.ViewBinder {
        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {
            when (view?.id) {
                android.R.id.text1 -> {
                    val tv = view as TextView
                    tv.text = "🍽" + data as String
                }
                android.R.id.text2 -> {
                    val tv = view as TextView
                    tv.text = "😊" + data as String
                }
            }
            return true
        }//setViewValue
    }//CustomViewBinder

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

        val lv = findViewById<ListView>(R.id.lvMenu)
        lv.adapter = adapter
        lv.onItemClickListener = ListItemClickListener()

    }//onCreate

    private inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick
                    (parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position)
                as MutableMap<String,String>
            val name = item["name"]
            val price = item["price"]

            val intent = Intent(this@MainActivity,
                                MenuThanksActivity::class.java)
            intent.putExtra("menuName", name)
            intent.putExtra("menuPrice", price)
            startActivity(intent)
        }//onItemClick
    }//ListItemClickListener



}//MainActivity
