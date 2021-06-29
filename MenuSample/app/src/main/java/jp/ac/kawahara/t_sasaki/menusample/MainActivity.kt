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
import android.widget.Toast
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
            order(item)
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

    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int
        val intent = Intent(this, MenuThanksActivity::class.java)
        //Java的には MenuThanksActivity.class
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", "${menuPrice}円")
        startActivity(intent)
    }//order

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val menu = _menuList[info.position]

        when(item.itemId){
            R.id.menuListContextDesc->{
                val desc = menu["desc"] as String
                Toast.makeText(this, desc, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menuListContextOrder->{
                order(menu)
                return true
            }
            else ->{
                return super.onContextItemSelected(item)
            }
        }//when
    }//onContextItemSelected

}//MainActivity