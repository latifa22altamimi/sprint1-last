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
    public static final String itemCOL5="status";
    public static final String itemCOL6="user";


    public static final String TABLENAME2 ="Rental";
    public static final String RCOL1 ="ID";
    public static final String RCOL2 ="UserID";
    public static final String RCOL3 ="ItemID";
    public static final String RCOL4 ="Status";
    public static final String RCOL5 ="Name";
    public static final String RCOL6 ="City";
    public static final String RCOL7 ="District";
    public static final String RCOL8 ="PhoneNumber";
    public static final String RCOL9 ="totalPrice";






    public DBHelper( Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME,null, 1);
    }

    public DBHelper(Context context) {
        super(context,DBNAME,null,1);
    }




    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT,"+COL3+" Text,"+COL4+" TEXT)");
        sqLiteDatabase.execSQL("create Table "+TABLENAME1+"(" +COl_ID+ " INTEGER primary key AUTOINCREMENT, "+ itemCOL1+" TEXT,"+itemCOL2+" TEXT,"+
                itemCOL3+" REAL,"+itemCOL4+ " blob, "+itemCOL5+" TEXT, "+ itemCOL6 +" TEXT, "+" FOREIGN KEY ("+itemCOL6 +") REFERENCES "+ TABLENAME +"("+COL1+")"+")"
        );

       sqLiteDatabase.execSQL("create Table "+TABLENAME2+ "("+RCOL1 +" INTEGER primary key AUTOINCREMENT, "+RCOL2 +" TEXT ,"+RCOL3+ " INTEGER , "+
RCOL4 +" TEXT, "+ RCOL5 +" TEXT, "+RCOL6+" TEXT, "+RCOL7 +" TEXT, "+RCOL8+" TEXT, "+RCOL9 +"  REAL, "+ " FOREIGN KEY ("+RCOL2 +") REFERENCES "+TABLENAME+" ("+COL1+") ,"+
                       " FOREIGN KEY ("+RCOL3 +") REFERENCES " +TABLENAME1+   " ("+COl_ID+") "+")"
      );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME1);
        sqLiteDatabase.execSQL("drop Table if exists " +TABLENAME2);
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


    public boolean addItem(Item item) {

        SQLiteDatabase db = this.getWritableDatabase();
String sql= "SELECT * FROM "+TABLENAME1 +" WHERE "+itemCOL1 +" = "+"'"+item.getName()+"'"+" AND "+itemCOL2 +" = " +"'"+item.getDescription()+"'";
Cursor cursor=db.rawQuery(sql,null);
if(cursor.getCount()<=0) {

    ContentValues values = new ContentValues();
    values.put(itemCOL1, item.getName());
    values.put(itemCOL2, item.getDescription());
    values.put(itemCOL3, item.getCost());
    values.put(itemCOL4, item.getImage());
    values.put(itemCOL5,item.getStatus());
    values.put(itemCOL6,item.getUser());
    db.insert(TABLENAME1, null, values);
    cursor.close();
    return true;
}
        cursor.close();
return false;
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
                @SuppressLint("Range") String status =cursor.getString(cursor.getColumnIndex(itemCOL5));
                @SuppressLint("Range") String user =cursor.getString(cursor.getColumnIndex(itemCOL6));
                Item item= new Item(name, description, cost,id, image,status,user);

                items.add(item);

            } while (cursor.moveToNext());

        }

        return items;
    }

    public ArrayList<Item> getUserItems(String user){
        ArrayList<Item> items = new ArrayList<>();
        String select_query = "select * from " + TABLENAME1 +" WHERE "+itemCOL6 + " ='"+user+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if (cursor.moveToFirst()) {

            do {

                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COl_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(itemCOL1));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(itemCOL2));


                @SuppressLint("Range") double cost = cursor.getDouble(cursor.getColumnIndex(itemCOL3));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(itemCOL4));
                @SuppressLint("Range") String status =cursor.getString(cursor.getColumnIndex(itemCOL5));
                @SuppressLint("Range") String username =cursor.getString(cursor.getColumnIndex(itemCOL6));
                Item item= new Item(name, description, cost,id, image,status,username);

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
            @SuppressLint("Range") String status =cursor.getString(cursor.getColumnIndex(itemCOL5));
            @SuppressLint("Range") String user =cursor.getString(cursor.getColumnIndex(itemCOL6));


            item = new Item(name,description , cost, id_item,image,status,user);

        }
        return item;
    }

    public void addRental(Rental rental) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RCOL2, rental.getUser());
        values.put(RCOL3, rental.getItemID());
        values.put(RCOL4, rental.getStatus());
        values.put(RCOL5, rental.getName());
        values.put(RCOL6, rental.getCity());
        values.put(RCOL7, rental.getDistrict());
        values.put(RCOL8, rental.getPhoneNumber());
        values.put(RCOL9, rental.getTotalPrice());

        db.insert(TABLENAME2, null, values);

    }



}
