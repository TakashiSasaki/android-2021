package jp.ac.kawahara.t_sasaki.asyncsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEBUG_TAG = "AsyncSample"
        private const val WEATHERINFO_URL =
            "https://api.openweathermap.org/data/2.5/weather?lang=ja"
        private const val APP_ID = "45e1a251abcf21306d5e1497fd4c3457"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _list = createList()

        val lv = findViewById<ListView>(R.id.lvCityList)
        lv.adapter = SimpleAdapter(
            this, _list,
            android.R.layout.simple_list_item_1,
            arrayOf("name"),
            intArrayOf(android.R.id.text1)
        )

        lv.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, l: Long ->
                val item = _list.get(position)
                val q = item.get("q")
                q?.let {
                    Log.v(DEBUG_TAG, "it = $it")
                    val urlFull = "$WEATHERINFO_URL&q=$q&appid=$APP_ID"
                    receiveWeatherInfo(urlFull)
                }//let q
            }//onItemClickListener
    }//onCreate

    private fun receiveWeatherInfo(urlFull: String) {
        Log.v(DEBUG_TAG, "urlFull = $urlFull")
    }//receiveWeatherInfo

    var _list: MutableList<MutableMap<String, String>> = mutableListOf()

    private fun createList(): MutableList<MutableMap<String, String>> {
        var list = mutableListOf<MutableMap<String, String>>()
        list.add(mutableMapOf("name" to "大阪", "q" to "Osaka"))
        list.add(mutableMapOf("name" to "神戸", "q" to "Kobe"))
        list.add(mutableMapOf("name" to "松山", "q" to "Matsuyama"))
        return list
    }//createList

    private inner class
    WeatherInfoBackgroundReceiver(url : String) : Runnable{
        private val _url = url

        override fun run() {
            // url をもとにOpenWeatherMapからJSONをGETする
            var result = ""
            val con = URL(_url).openConnection() as? HttpURLConnection
            con?.let{
                try{
                    it.connectTimeout = 1000
                    it.readTimeout = 1000
                    it.requestMethod = "GET"
                    it.connect()
                    result = is2String(it.inputStream)
                    it.inputStream.close()
                } catch(ex :SocketTimeoutException){
                    Log.w(DEBUG_TAG, "通信タイムアウト", ex)
                }//try
            }//let

            Handler(Looper.getMainLooper())
                .post(WeatherInfoPostExecutor())
        }
    }

    private fun is2String(bs: InputStream): String {
        val sb = StringBuilder()

        //InputStreamReaderはChar（UTF-16）の列として読みだすためのもの
        //sr.read(cbuf : CharArray!)
        val sr = InputStreamReader(bs, "UTF-8")
        //BufferdReaderは行単位で読みだすためのもの
        val br = BufferedReader(sr)
        var line = br.readLine()
        while(line != null){
            sb.append(line)
            line = br.readLine()
        }//while
        return sb.toString()
    }//is2String

    private inner class
    WeatherInfoPostExecutor(): Runnable{
        override fun run(){
            // JSONをもとにUIを更新する
        }
    }



}//MainActivity