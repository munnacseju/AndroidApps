package jutecbd.munna.julibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDatabasehelper extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "cart.dp";
    private static final String TABLE_NAME = "cart";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String PRICE = "Price";
    private static final String COUNT = "Count";
    private static final int VERSION_NO = 1;

    public MyDatabasehelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+ID+" VARCHAR(255) PRIMARY KEY, "+ NAME + " VARCHAR(255),"+ COUNT+" VARCHAR(255), " +PRICE+ " VARCHAR(255))");
        }catch (Exception e){
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+ID+" VARCHAR(255) PRIMARY KEY, "+ NAME + " VARCHAR(255),"+ COUNT+" VARCHAR(255), " +PRICE+ " VARCHAR(255))");
    }

    public long insertData(String id, String name, String count, String price){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME,name);
        contentValues.put(COUNT, count);
        contentValues.put(PRICE, price);
        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowid;
    }

    public Cursor showData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

    public void updateData(String id, String name, String count, String price){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME,name);
        contentValues.put(COUNT, count);
        contentValues.put(PRICE, price);
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID+" = ?", new String[]{String.valueOf(id)});
    }

    public void deleteDAta(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID +" =  ?", new String[]{String.valueOf(id)});
    }

}
