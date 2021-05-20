package jp.ac.kawahara.t_sasaki.hellosample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.btClick)
//            .setOnClickListener { view: View ->
//                val input = findViewById<EditText>(R.id.etName)
//                val output = findViewById<TextView>(R.id.tvOutput)
//                val inputStr = input.getText().toString()
//                output.text = inputStr + "さん、こんにちは？"
//            }
//
//        findViewById<Button>(R.id.btClear)
//            .setOnClickListener {
//                val input = findViewById<EditText>(R.id.etName)
//                input.setText("")
//                val output = findViewById<TextView>(R.id.tvOutput)
//                output.setText("")
//            }

        var listener = HelloListener()

        findViewById<Button>(R.id.btClick)
            .setOnClickListener(listener)
        findViewById<Button>(R.id.btClear)
            .setOnClickListener(listener)

    }// onClick

    private inner class HelloListener : View.OnClickListener{
        override fun onClick(view: View){
            var input  = findViewById<EditText>(R.id.etName)
            var output = findViewById<TextView>(R.id.tvOutput)

            when(view.id){

                R.id.btClick -> {
                    output.text = input.text.toString() + "さん、こんにちは"
                }//R.id.btClick

                R.id.btClear -> {
                    output.text = ""
                    input.setText("")
                }//R.id.btClear

            }//when

        }//fun onClick

    }//class HelloLister

}// MainActivity
