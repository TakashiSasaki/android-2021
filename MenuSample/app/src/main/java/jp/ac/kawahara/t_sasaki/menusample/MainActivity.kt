package jp.ac.kawahara.t_sasaki.menusample

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var _menuList = mutableListOf<MutableMap<String, Any>>()
    private val _from = arrayOf("name", "price")
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(
            this, _menuList, R.layout.row,
            _from, _to
        )
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
        registerForContextMenu(lvMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }//onCreateOptionsMenu

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        menuList.add(
            mutableMapOf(
                "name" to "からあげ定食",
                "desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。",
                "price" to 800
            )
        )
        menuList.add(
            mutableMapOf(
                "name" to "ハンバーグ定食",
                "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。",
                "price" to 850
            )
        )
        return menuList
    }//createTeishokuList

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        menuList.add(
            mutableMapOf(
                "name" to "ビーフカレー",
                "desc" to "特選スパイスを効かせた国産ビーフ100%のカレーです。",
                "price" to 520
            )
        )
        menuList.add(
            mutableMapOf(
                "name" to "ポークカレー",
                "desc" to "特選スパイスを効かせた国産ポーク100%のカレーです。",
                "price" to 420
            )
        )
        return menuList
    }//createCurryList

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position)
                    as MutableMap<String, Any>
            val name = item["name"] as String //String
            val price = item["price"] as Int //int
            val desc = item["desc"] as String //String

            val intent = Intent(
                this@MainActivity,
                MenuThanksActivity::class.java
            )
            intent.putExtra("menuName", name)
            intent.putExtra("menuDesc", desc)
            intent.putExtra("menuPrice", price)
            startActivity(intent)
        }//onItemClick
    }//ListItemClickListener

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true

        when (item.itemId) {
            R.id.menuListOptionTeishoku -> {
                _menuList = createTeishokuList()
            }
            R.id.menuListOptionCurry -> {
                _menuList = createCurryList()
            }
            else -> {
                returnVal = super.onOptionsItemSelected(item)
            }
        }//when

        val lv = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this, _menuList, R.layout.row, _from, _to)
        lv.adapter = adapter

        return returnVal
    }//onOptionsItemSelected

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu.setHeaderTitle(R.string.menu_list_context_header)
    }//onCreateContextMenu

}//MainActivity