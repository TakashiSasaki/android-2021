package jp.ac.kawahara.t_sasaki.fragmentsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var menuList = mutableListOf<MutableMap<String, String>>()
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MenuListFragment", "onCreate")
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
        Log.i("MenuListFragment", "onCreateView")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("MenuListFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val frame = activity?.findViewById<FrameLayout>(R.id.menuThanksFrame)
        if(frame == null){
            _isLayoutXLarge = false
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuListFragment.
         */
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
            val bundle = Bundle()
            bundle.putString("menuName", name)
            bundle.putString("menuPrice", price)

            if(_isLayoutXLarge) {
                val fragment = MenuThanksFragment()
                fragment.arguments = bundle
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.menuThanksFrame, fragment)
                transaction.commit()
            } else {
                val intent = Intent(activity, MenuThanksActivity::class.java)
                //intent.putExtra("menuName", name)
                //intent.putExtra("menuPrice", price)
                intent.putExtras(bundle)
                activity?.startActivity(intent)
            }
        }//onItemClick
    }//OnItemClickListener

    override fun onAttach(context: Context) {
        Log.i("MenuListFragment", "onAttach")
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("MenuListFragment", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("MenuListFragment", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("MenuListFragment", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("MenuListFragment", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("MenuListFragment", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("MenuListFragment", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("MenuListFragment", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("MenuListFragment", "onDetach")
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("MenuListFragment", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.i("MenuListFragment", "onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }
}
