package jp.ac.kawahara.t_sasaki.asynccoroutinesample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

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

    var _list: MutableList<MutableMap<String, String>> = mutableListOf()

    private fun createList(): MutableList<MutableMap<String, String>> {
        val list = mutableListOf<MutableMap<String, String>>()
        list.add(mutableMapOf("name" to "??????", "q" to "Osaka"))
        list.add(mutableMapOf("name" to "??????", "q" to "Kobe"))
        list.add(mutableMapOf("name" to "??????", "q" to "Matsuyama"))
        return list
    }//createList

    private fun is2String(bs: InputStream): String {
        val sb = StringBuilder()

        //InputStreamReader???Char???UTF-16?????????????????????????????????????????????
        //sr.read(cbuf : CharArray!)
        val sr = InputStreamReader(bs, "UTF-8")
        //BufferedReader??????????????????????????????????????????
        val br = BufferedReader(sr)
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }//while
        return sb.toString()
    }//is2String

    @UiThread
    private fun receiveWeatherInfo(urlFull: String) {
        lifecycleScope.launch {
            val result = weatherInfoBackgroundRunner(urlFull)
            weatherInfoPostRunner(result)
        }//launch
    }//receiveWeatherInfo

    @WorkerThread
    private suspend fun weatherInfoBackgroundRunner(url: String): String =
        withContext(Dispatchers.IO) {
            // url ????????????OpenWeatherMap??????JSON???GET??????
            var result = ""
            val con = URL(url).openConnection() as? HttpURLConnection
            con?.let {
                try {
                    it.connectTimeout = 1000
                    it.readTimeout = 1000
                    it.requestMethod = "GET"
                    it.connect()
                    Log.v(DEBUG_TAG, "responseCode = ${it.responseCode}")
                    result = is2String(it.inputStream)
                    //result???JSON?????????????????????????????????????????????
                    it.inputStream.close()
                } catch (ex: SocketTimeoutException) {
                    Log.w(DEBUG_TAG, "????????????????????????", ex)
                }//try
            }//let
            result
        }//weatherInfoBackgroundRunner

    @UiThread
    private fun weatherInfoPostRunner(result: String) {
        // JSON?????????????????????????????????????????????UI???????????????
        // result ??? JSON?????????????????????
        val root = JSONObject(result)
        val weather = root
            .getJSONArray("weather")
            .getJSONObject(0)
            .getString("description")
        val longitude = root.getJSONObject("coord").getString("lon")
        val latitude = root.getJSONObject("coord").getString("lat")
        val cityName = root.getString("name")

        val telop = "${cityName}?????????"
        val desc = "?????????${weather}?????????\n?????????${latitude}???????????????${longitude}????????????"

        findViewById<TextView>(R.id.tvWeatherTelop).text = telop
        findViewById<TextView>(R.id.tvWeatherDesc).text = desc
    }
}