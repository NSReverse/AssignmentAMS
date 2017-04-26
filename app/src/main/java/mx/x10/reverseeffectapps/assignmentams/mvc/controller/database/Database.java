package mx.x10.reverseeffectapps.assignmentams.mvc.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.x10.reverseeffectapps.assignmentams.mvc.model.Assignment;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.SchoolClass;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;

/**
 * Class Database -> SQLiteOpenHelper -
 *
 * This class is intended to have basic operations for creating the database. When instantiated
 * as an object, it can provide read and write operations. Access package-private.
 *
 * @author Robert
 * Created 4/20/2017.
 */
class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "amsdatabase.db";

    /**
     * Constructor Database(Context) -
     *
     * This is a basic constructor to create a new Database object, it is package-private and
     * only used by the DatabaseController class.
     *
     * @param context A Context object to pass to the super (SQLiteOpenHelper) constructor.
     */
    Database(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    /**
     * Overridden method onCreate(SQLiteDatabase) -
     *
     * This method creates the tables needed by the app to persist data. It only creates the tables
     * if they do not exist. This method in particular creates 3 tables: school_classes,
     * assignments, and reminders.
     *
     * @param db A SQLiteDatabase object to execute queries on.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String classesQuery = "CREATE TABLE " + SchoolClass.TABLE + "(" +
                SchoolClass.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SchoolClass.KEY_CLASS_NAME + " TEXT, " +
                SchoolClass.KEY_CLASS_ID + " TEXT, " +
                SchoolClass.KEY_CLASS_INSTRUCTOR + " TEXT, " +
                SchoolClass.KEY_CLASS_DATE + " TEXT, " +
                SchoolClass.KEY_HASH_ID + " TEXT)";

        db.execSQL(classesQuery);

        String assignmentsQuery = "CREATE TABLE " + Assignment.TABLE + "(" +
                Assignment.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Assignment.KEY_CLASS_ID + " TEXT, " +
                Assignment.KEY_ASSIGNMENT_NAME + " TEXT, " +
                Assignment.KEY_ASSIGNMENT_DUE_DATE + " TEXT, " +
                Assignment.KEY_HASH_ID + " TEXT)";

        db.execSQL(assignmentsQuery);

        String remindersQuery = "CREATE TABLE " + Reminder.TABLE + "(" +
                Reminder.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Reminder.KEY_ITEM_NAME + " TEXT, " +
                Reminder.KEY_ITEM_DATE + " TEXT, " +
                Reminder.KEY_ITEM_COMMENTS + " TEXT, " +
                Reminder.KEY_HASH_ID + " TEXT)";

        db.execSQL(remindersQuery);
    }

    /**
     * Overridden method onUpgrade(SQLiteDatabase, int, int) -
     *
     * This method is required to be overridden and only provides basic database correction by
     * removing all of the tables and their data and starting with a fresh database. This will be
     * changed later to provide more practical functionality.
     *
     * @param db A SQLiteDatabase object to execute queries on.
     * @param oldVersion An int representing the version of the old database structure.
     * @param newVersion An int representing the version of the new database structure.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SchoolClass.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Assignment.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Reminder.TABLE);
    }
}
