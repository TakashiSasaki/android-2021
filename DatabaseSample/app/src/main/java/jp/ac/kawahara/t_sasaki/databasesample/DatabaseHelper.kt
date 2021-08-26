package jp.ac.kawahara.t_sasaki.databasesample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.StringBuilder

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "cocktailmemo.db"
        val DATABASE_VERSION = 3
    }//companion object

    // SQLiteのファイルが無い状態で getWritableDatabase もしくは
    // getReadableDatabase が呼び出されたときにはじめて
    // DatabaseHelper#onCreate が呼び出される
    override fun onCreate(db: SQLiteDatabase?) {
        Log.v("DatabaseHelper", "onCreate")
        val sb = StringBuilder()
        sb.append("CREATE TABLE cocktailmemos (")
        sb.append("_id INTEGER PRIMARY KEY,")
        sb.append("name TEXT,")
        sb.append("note TEXT")
        sb.append(");")
        val sql = sb.toString()
        db?.execSQL(sql)
    }//onCreate

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.v("DatabaseHJelper", "onUpgrade")

        //if(oldVersion == 1 && newVersion == 2){
            //ここにversion1 のDBからversion 2のDBへのAlter文を実行するコードを書く。
        //}
        if(oldVersion == 1 && newVersion == 3){
            //ここにversion1 のDBからversion 3のDBへのAlter文を実行するコードを書く。
        }
        if(oldVersion == 2 && newVersion == 3){
            //ここにversion1 のDBからversion 2のDBへのAlter文を実行するコードを書く。
        }
    }//onUpgrade
}//DatabaseHelper
