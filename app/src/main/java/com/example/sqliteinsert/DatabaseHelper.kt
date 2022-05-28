package com.example.sqliteinsert

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR
import android.provider.BaseColumns
import android.provider.SyncStateContract.Helpers.insert
import com.example.sqliteinsert.DatabaseContainer.PersonTable.Companion.HEIGHT_COLUMN
import com.example.sqliteinsert.DatabaseContainer.PersonTable.Companion.LASTNAME_COLUMN
import com.example.sqliteinsert.DatabaseContainer.PersonTable.Companion.NAME_COLUMN
import com.example.sqliteinsert.DatabaseContainer.PersonTable.Companion.TABLE_NAME
import com.example.sqliteinsert.DatabaseContainer.PersonTable.Companion.WEIGHT_COLUMN

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
//    constructor(context: MainActivity) : this()

    override fun onCreate(db: SQLiteDatabase?) {
        val personTable ="CREATE TABLE" + TABLE_NAME + "("+
                BaseColumns._ID+ "INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COLUMN + "TEXT" + LASTNAME_COLUMN + "TEXT" +
                HEIGHT_COLUMN + "TEXT" +
                WEIGHT_COLUMN + "TEXT" + ")"
        db!!.execSQL(personTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun insertData(strName: String,strLastName: String,strHeight: String,strWeight: String): Boolean
    {
        val db:SQLiteDatabase = this.writableDatabase
        val contextValues = ContentValues()
        contextValues.put(NAME_COLUMN,strName)
        contextValues.put(LASTNAME_COLUMN,strLastName)
        contextValues.put(HEIGHT_COLUMN,strHeight)
        contextValues.put(WEIGHT_COLUMN,strWeight)
        val insert_data = db.insert(TABLE_NAME,null,contextValues)
        db.close()

        return !insert_data.equals(-1)
    }

    companion object{
        private const val DATABASE_NAME = "person.db"
        private const val DATABASE_VERSION = 1
    }
}
