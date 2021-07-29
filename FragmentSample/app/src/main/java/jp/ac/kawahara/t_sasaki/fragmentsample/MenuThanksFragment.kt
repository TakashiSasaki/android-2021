package jp.ac.kawahara.t_sasaki.fragmentsample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuThanksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuThanksFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MenuThanksFragment", "onCreate")
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("MenuThanksFragment", "onCreateView")
        val frame = activity?.findViewById<FrameLayout>(R.id.menuThanksFrame)
        _isLayoutXLarge = if(frame == null) false else true

        val extras = if(_isLayoutXLarge) arguments else activity?.intent?.extras

        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")

        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)
        view.findViewById<TextView>(R.id.tvMenuName).text = menuName
        view.findViewById<TextView>(R.id.tvMenuPrice).text = menuPrice

        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener {
            if (_isLayoutXLarge) {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.remove(this)
                transaction.commit()
            } else {
                activity?.finish()
            }
        }
        return view
    }//onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("MenuThanksFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuThanksFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuThanksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        Log.i("MenuThanksFragment", "onAttach")
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("MenuThanksFragment", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("MenuThanksFragment", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("MenuThanksFragment", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("MenuThanksFragment", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("MenuThanksFragment", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("MenuThanksFragment", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("MenuThanksFragment", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("MenuThanksFragment", "onDetach")
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("MenuThanksFragment", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.i("MenuThanksFragment", "onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }
}