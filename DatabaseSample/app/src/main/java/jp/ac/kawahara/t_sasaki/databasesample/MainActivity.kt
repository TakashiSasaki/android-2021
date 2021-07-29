package jp.ac.kawahara.t_sasaki.databasesample

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var _cocktailId = -1
    var _cocktailName = ""
    var _helper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("MainActivity", "onCreate")
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.lvCocktail).onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                _cocktailId = position
                _cocktailName = parent.getItemAtPosition(position) as String
                findViewById<TextView>(R.id.tvCocktailName)
                    .text = _cocktailName
                findViewById<Button>(R.id.btnSave)
                    .isEnabled = true

                //選択したカクテルのメモを呼び出す
                val db = _helper.writableDatabase
                val sql = "SELECT * FROM cocktailmemos WHERE _id = ${_cocktailId}"
                val cursor = db.rawQuery(sql, null)
                var note = ""
                while(cursor.moveToNext()){
                    val idxNote = cursor.getColumnIndex("note")
                    note = cursor.getString(idxNote)
                }//while
                findViewById<EditText>(R.id.etNote).setText(note)
            }//AdapterView.OnItemClickListener

    }//onCreate

    fun onSaveButtonClick(v: View) {
        val note = findViewById<EditText>(R.id.etNote).text.toString()
        val db = _helper.writableDatabase

        //既存のメモがあるかもしれないので削除
        val deleteStatement = db.compileStatement("DELETE FROM cocktailmemos WHERE _id = ?")
        deleteStatement.bindLong(1, _cocktailId.toLong())
        deleteStatement.executeUpdateDelete()

        //新規のメモを追加
        val insertStatement = db.compileStatement(
            "INSERT INTO cocktailmemos (_id, name, note) VALUES (?, ?, ?)")
        insertStatement.bindLong(1, _cocktailId.toLong())
        insertStatement.bindString(2, _cocktailName)
        insertStatement.bindString(3, note)
        insertStatement.executeInsert()

        findViewById<EditText>(R.id.etNote).setText("")
        findViewById<TextView>(R.id.tvCocktailName)
            .setText(R.string.tv_name)
        findViewById<Button>(R.id.btnSave).isEnabled = false
    }//onSaveButtonClick

    override fun onDestroy(){
        Log.v("MainActivity", "onDestroy, before _helper.close()")
        _helper.close()
        Log.v("MainActivity", "onDestroy, after _helper.close()")
        super.onDestroy()
    }

}//MainActivity