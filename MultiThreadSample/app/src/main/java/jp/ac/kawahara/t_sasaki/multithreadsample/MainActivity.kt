package jp.ac.kawahara.t_sasaki.multithreadsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // プロセスもスレッドもOSがコンテキストスイッチングする物であることに変わりはない
    // プロセス間ではメモリ空間やファイルハンドルなどの資源がOSにより分離されている
    // 同一プロセスに含まれるスレッド間ではそれらの資源が分離されておらず相互にアクセス可能
    // アクセス可能とは言い換えればアクセスできてしまうということなので注意深さが必要
    // 例えば複数のスレッドから同時にファイルに出力してしまうとぐちゃぐちゃになる。
    // プログラマに任されている＝プログラマの責任が大きい

    // 一般論としてのスレッドの概念、AndroidにおけるUIスレッドの概念を理解するために
    // 小規模のアプリを作って動作を確かめてみる。

    private fun funA(){
        (1..100).forEach {
            print("A$it ")
        }
    }

    private fun funB(){
        (1..100).forEach {
            print("B$it ")
        }
    }

    public fun onButtonClick(v: View){
        //ボタンを押すとこの関数がUIスレッドで実行される
        thread {
            funA()
        }
        thread {
            funB()
        }
    }
}