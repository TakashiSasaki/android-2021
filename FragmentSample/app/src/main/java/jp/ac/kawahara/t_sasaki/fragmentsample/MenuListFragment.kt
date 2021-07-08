package jp.ac.kawahara.t_sasaki.fragmentsample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var menuList = mutableListOf<MutableMap<String, String>>()
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        menuList.add(mutableMapOf("name" to "からあげ定食", "price" to "800円"))
        menuList.add(mutableMapOf("name" to "ハンバーグ定食", "price" to "850円"))
    }//onCreate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
        //val menuList = mutableListOf<MutableMap<String, String>>()

        lvMenu.adapter = SimpleAdapter(
            activity, menuList,
            android.R.layout.simple_list_item_2,
            arrayOf("name", "price"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        lvMenu.onItemClickListener = ListItemClickListener()
        return view
    }//onCreateView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val frame = activity?.findViewById<FrameLayout>(R.id.menuThanksFrame)
        if(frame == null){
            _isLayoutXLarge = false
        }
    }//onActivityCreated

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private inner class ListItemClickListener :
        AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position)
                    as MutableMap<String, String>
            val name = item["name"]
            val price = item["price"]
            val b = Bundle()
            b.putString("menuName", name)
            b.putString("menuPrice", price)

            if(_isLayoutXLarge) {
                val f = MenuThanksFragment()
                f.arguments = b
                val t = fragmentManager?.beginTransaction()
                t?.replace(R.id.menuThanksFrame, f)
                t?.commit()
            } else {
                val intent = Intent(activity, MenuThanksActivity::class.java)
                //intent.putExtra("menuName", name)
                //intent.putExtra("menuPrice", price)
                intent.putExtras(b)
                activity?.startActivity(intent)
            }
        }//onItemClick
    }//OnItemClickListener
}
