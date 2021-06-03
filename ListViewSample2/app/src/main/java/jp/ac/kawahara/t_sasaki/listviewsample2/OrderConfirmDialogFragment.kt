package jp.ac.kawahara.t_sasaki.listviewsample2

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class OrderConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
            builder.setMessage(R.string.dialog_msg)
            builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())
            builder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())
            builder.setNeutralButton(R.string.dialog_btn_nu, DialogButtonClickListener())
            builder.create()
        }//let
        return dialog ?: throw IllegalStateException("アクティビティがnullです")
    }//onCreateDialog

    private inner class DialogButtonClickListener
        : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Toast.makeText(
                        activity, getString(R.string.dialog_ok_toast),
                        Toast.LENGTH_LONG
                    ).show()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Toast.makeText(
                        activity, getString(R.string.dialog_ng_toast),
                        Toast.LENGTH_LONG
                    ).show()
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                    Toast.makeText(
                        activity, getString(R.string.dialog_nu_toast),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }//when
        }//onClick
    }//DialogButtonClickListener
}//OrderConfirmDialogFragment
