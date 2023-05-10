package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Login.db";
    public static final String TABLENAME = "users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COL3 = "email";
    public static final String COL4 = "phone";


    public static final String TABLENAME1 ="items";
    public static final String COl_ID ="id";
    public static final String itemCOL1 ="name";
    public static final String itemCOL2 ="description";
    public static final String itemCOL3 ="cost";
    public static final String itemCOL4="photo";
    //public static final String itemCOL5="username";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME,null, 1);
    }

    public DBHelper(Context context) {
        super(context,DBNAME,null,1);
    }




    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT,"+COL3+" Text,"+COL4+" TEXT)");
        sqLiteDatabase.execSQL("create Table "+TABLENAME1+"(" +COl_ID+ " INTEGER primary key AUTOINCREMENT, "+ itemCOL1+" TEXT,"+itemCOL2+" TEXT,"+
                itemCOL3+" REAL,"+itemCOL4+" blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME1);
    }




    public Boolean insertData(String username, String password,String email, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3, email);
        contentValues.put(COL4, phone);

        long result = MyDB.insert(TABLENAME, null, contentValues);
        if(result==-1) return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ? and " + COL2 + " = ?", new String[] {username,password}  );
        if(cursor.getCount()>0) return true;
        return false;
    }
    public Boolean checkEmail( String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL3 + " = ?" , new String[] {email});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public Boolean checkPhone( String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL4 + " = ?" , new String[] {password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    //end of latifa and shahad

    //start of raya and ibtihal


    public void addItem(Item item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(itemCOL1, item.getName());
        values.put(itemCOL2, item.getDescription());
        values.put(itemCOL3, item.getCost());
        values.put(itemCOL4,item.getImage());
        db.insert(TABLENAME1, null, values);

    }
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        String select_query = "select * from " + TABLENAME1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COl_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(itemCOL1));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(itemCOL2));


                @SuppressLint("Range") double cost = cursor.getDouble(cursor.getColumnIndex(itemCOL3));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(itemCOL4));

                Item item= new Item(name, description, cost,id, image);

                items.add(item);

            } while (cursor.moveToNext());

        }

        return items;
    }

    public boolean deleteItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString= "Delete From " + TABLENAME1 + " WHERE " + COl_ID + " = " + item.getId() ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{
            // nothing happens nothing is added.
            return false;
        }
        //close
    }


    public Item getItemById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from " + TABLENAME1 + "where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Item item= null;

        if (cursor.moveToFirst()) {

            @SuppressLint("Range") int id_item = cursor.getInt(cursor.getColumnIndex(COl_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(itemCOL1));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(itemCOL2));
            @SuppressLint("Range") double cost = cursor.getDouble(cursor.getColumnIndex(itemCOL3));
            @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(itemCOL4));


            item = new Item(name,description , cost, id_item,image);

        }
        return item;
    }


}
