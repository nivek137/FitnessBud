package com.example.android.workoutgenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, firstname TEXT, lastname TEXT, age TEXT, weight TEXT, gender TEXT, height TEXT )");    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        //contentValues.put("firstname","hi");
        //contentValues.put("lastname","hi");
        //contentValues.put("age","hi");
        //contentValues.put("weight","hi");
        //contentValues.put("gender","hi");
        //contentValues.put("height","hi");
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public boolean checkUsername(String username){
        String[] columns = { COL_1 };
        SQLiteDatabase MyDB = getReadableDatabase();
        String selection = COL_2 + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = MyDB.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        MyDB.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public boolean changePassword(String password, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,username);
        values.put(COL_3,password);
        db.update(TABLE_NAME,values,"username = ?",new String[] {username});
        return true;
    }

    public boolean addInformation(String username, String firstname, String lastname,String weight,String age, String gender, String height)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("age",age);
        contentValues.put("weight",weight);
        contentValues.put("gender",gender);
        contentValues.put("height",height);
        db.update(TABLE_NAME,contentValues,"username = ?",new String[] {username});
        //long res = db.insert("registeruser",null,contentValues);
        return true;
    }

    public Cursor viewData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor f = db.rawQuery("select * from "+TABLE_NAME,null);//"SELECT " + "*" + " FROM registeruser WHERE username = '" + username+"'",null);
        return f;
    }
}
