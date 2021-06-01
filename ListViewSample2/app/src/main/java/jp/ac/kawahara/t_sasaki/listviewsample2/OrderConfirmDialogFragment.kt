package jp.ac.kawahara.t_sasaki.listviewsample2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class OrderConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
            builder.setMessage(R.string.dialog_msg)
            builder.setPositiveButton(R.string.dialog_btn_ok, null)
            builder.setNegativeButton(R.string.dialog_btn_ng, null)
            builder.setNeutralButton(R.string.dialog_btn_nu, null)
            builder.create()
        }
        return dialog ?: throw IllegalStateException("アクティビティがnullです")
    }
}