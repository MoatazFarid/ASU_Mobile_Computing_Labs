package com.moatazfarid.lab1_login_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

/**
 * Created by MoatazFarid on 3/3/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    // database version
    private static final int DATABASE_VERSION=1;
    // database name
    private static final String DATABASE_NAME="myDatabase";

    /**
     * Main Constuctor
     * @param context
     */
    public DBHandler(Context context) {
        /**
         * Create a helper object to create, open, and/or manage a database.
         * This method always returns very quickly.  The database is not actually
         * created or opened until one of {@link #getWritableDatabase} or
         * {@link #getReadableDatabase} is called.
         *
         * @param context to use to open or create the database
         * @param name    of the database file, or null for an in-memory database
         * @param factory to use for creating cursor objects, or null for the default
         * @param version number of the database (starting at 1); if the database is older,
         *                {@link #onUpgrade} will be used to upgrade the database; if the database is
         *                newer, {@link #onDowngrade} will be used to downgrade the database
         */
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY,"+
                "name TEXT ,"+
                "email TEXT UNIQUE,"+
                "password TEXT ,"+
                ")";
        db.execSQL(CREATE_USERS_TABLE); // create table
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table users if exist
        db.execSQL("DROP TABLE IF EXISTS users");
        // recreate users table
        onCreate(db);
    }

    /**
     * Called when we want to create a new User in database
     * @param name
     * @param email
     * @param password
     * @return true if created and false if error occured
     *
     */
    public boolean createUser(String name ,String email ,String password){
        // step 1 : get database writable instance
        SQLiteDatabase db = this.getWritableDatabase();

        // step 2 : get the Contentvalues object
        ContentValues values= new ContentValues();

        // step 3 : prepare contentvalues to be inserted
        values.put("name",name);
        values.put("email",email);
        values.put("password",password);

        // step 4 : insert into database order
        try {
            db.insert("users", null, values);
            db.close(); // Closing database connection
        }catch (Exception e){
            System.out.println("Can't create User ERROR:"+e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * get User by name
     * @param name
     * @return
     */
    public User getUserByName (String name){
        Cursor user = null ;

        // get readable instance from database
        SQLiteDatabase db = this.getReadableDatabase();
        // prepare query sentance
        String sql = "select * from users where name ="+name+"";
        try {
            // excecute the query sentance and save to User
            user = db.rawQuery(sql, null);
            db.close(); // Closing database connection

        }catch (Exception e ){
            return null;
        }
        User usr = new User(Integer.parseInt(user.getString(0)),user.getString(1),user.getString(2),user.getString(3));
        return usr;
    }

    /**
     * get User by email
     * @param email
     * @return User object
     */
    public User getUserByEmail (String email){
        Cursor user = null ;

        // get readable instance from database
        SQLiteDatabase db = this.getReadableDatabase();
        // prepare query sentance
        String sql = "select * from users where email ="+email+"";
        try {
            // excecute the query sentance and save to User
            user = db.rawQuery(sql, null);
            db.close(); // Closing database connection

        }catch (Exception e ){
            return null;
        }
        User usr = new User(Integer.parseInt(user.getString(0)),user.getString(1),user.getString(2),user.getString(3));
        return usr;
    }
    public boolean updateUserField(int id , String str){
        // get editable instance of database
        SQLiteDatabase db = this.getWritableDatabase();

        // create content values
        ContentValues values = new ContentValues();

        // write quires
        if (str.equals("name")){
            values.put("name",str);
        }else if (str.equals("email")){
            values.put("email",str);
        }else if (str.equals("password")){
            values.put("password",str);
        }else {
            System.out.println("ERROR: Wrong str was sent");
            return false;
        }
        try {
            // execute Query
            db.update("users",values,"id= ? ",new String []{Integer.toString(id)});
            db.close(); // Closing database connection
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
