package jp.ac.kawahara.t_sasaki.listviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.lvMenu)
            .onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener :
            AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>, p1: View, p2: Int, p3: Long) {

            //AdapterViewにある位置のアイテムの値を問い合わせる
            //val item = p0.getItemAtPosition(p2) as String

            // p1はTextViewなのでそこから定食名を取り出すこともできる
            val item = (p1 as TextView).text //toString()しなくてもいいみたい

            val show = "あなたが選んだ定食は " + item
            Toast.makeText(applicationContext ,show, Toast.LENGTH_LONG).show()
            // 教科書ではアクティビティそのもの this@MainActivity を第一引数に渡していた
            // Java なら MainActivity.this と書いていたところ
        }
    }



}