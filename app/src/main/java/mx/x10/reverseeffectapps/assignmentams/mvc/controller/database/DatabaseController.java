package mx.x10.reverseeffectapps.assignmentams.mvc.controller.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.x10.reverseeffectapps.assignmentams.mvc.controller.MainActivityController;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Assignment;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.SchoolClass;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;

/**
 * Class DatabaseController -
 *
 * This class is used to provide CRUD operations against the database.
 *
 * @author Robert
 * Created 4/21/2017.
 */
public class DatabaseController {
    private Database database = null;
    private static final String LOG_TAG = "DatabaseController";

    /**
     * Constructor DatabaseController(Context) -
     *
     * This is a basic constructor for creating a new DatabaseController object to perform
     * operations on the internal database.
     *
     * @param context A Context object to pass to the Database object.
     */
    public DatabaseController(Context context) {
        database = new Database(context);
    }

    //region -- Insert Data (Create) --

    /**
     * Method insertClassRow(SchoolClass) -
     *
     * This method inserts a row to the "school_classes" table.
     *
     * @param schoolClass A SchoolClass object representing a class for the student to enter
     *                    into the database.
     */
    public void insertClassRow(SchoolClass schoolClass) {
        ContentValues values = new ContentValues();
        values.put(SchoolClass.KEY_CLASS_ID, schoolClass.getClassIdentifier());
        values.put(SchoolClass.KEY_CLASS_NAME, schoolClass.getClassName());
        values.put(SchoolClass.KEY_CLASS_INSTRUCTOR, schoolClass.getClassTeacherName());
        values.put(SchoolClass.KEY_CLASS_DATE, schoolClass.getClassDates());
        values.put(SchoolClass.KEY_HASH_ID, schoolClass.getUUID());

        insertRow(SchoolClass.TABLE, values);
    }

    /**
     * Method insertAssignmentRow(Assignment) -
     *
     * This method inserts a row to the "assignments" table.
     *
     * @param currentAssignment An Assignment object representing an assignment for the student
     *                          to enter into the database.
     */
    public void insertAssignmentRow(Assignment currentAssignment) {
        ContentValues values = new ContentValues();
        values.put(Assignment.KEY_CLASS_ID, currentAssignment.getAssignmentClassIdentifier());
        values.put(Assignment.KEY_ASSIGNMENT_NAME, currentAssignment.getAssignmentName());
        values.put(Assignment.KEY_ASSIGNMENT_DUE_DATE, currentAssignment.getAssignmentDueDate());
        values.put(Assignment.KEY_HASH_ID, currentAssignment.getUUID());

        insertRow(Assignment.TABLE, values);
    }

    /**
     * Method insertReminderRow(Reminder) -
     *
     * This method inserts a row to the "reminders" table.
     *
     * @param currentItem A Reminder object representing a To Do list item for the student to
     *                    enter into the database.
     */
    public void insertReminderRow(Reminder currentItem) {
        ContentValues values = new ContentValues();
        values.put(Reminder.KEY_ITEM_NAME, currentItem.getReminderName());
        values.put(Reminder.KEY_ITEM_DATE, currentItem.getReminderDate());
        values.put(Reminder.KEY_HASH_ID, currentItem.getUUID());

        insertRow(Reminder.TABLE, values);
    }

    /**
     * Method insertRow(String, ContentValues) -
     *
     * Common helper method to enter data into the database. Used by other "insertRow"-type
     * methods.
     *
     * @param tableName A String representing the name of the table.
     * @param values A ContentValues mapping representing the KV pairs of the
     *               row going into the database.
     */
    private void insertRow(String tableName, ContentValues values) {
        try {
            SQLiteDatabase db = database.getWritableDatabase();

            db.insert(tableName, null, values);

            if (db.isOpen()) {
                db.close();
            }
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to save data: " + ex.getMessage());
        }
    }
    //endregion

    //region -- Retrieve Data (Read) --

    /**
     * Method getAllClasses() -
     *
     * This method gets all of the rows in the "school_classes" table in the database and returns
     * them as a List containing SchoolClass objects.
     *
     * @return A List containing all of the SchoolClass objects from the database.
     */
    public List<SchoolClass> getAllClasses() {
        List<SchoolClass> classes = new ArrayList<>();

        try {
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + SchoolClass.TABLE;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    SchoolClass currentClass = new SchoolClass();
                    currentClass.setClassIdentifier(cursor.getString(cursor.getColumnIndex(SchoolClass.KEY_CLASS_ID)));
                    currentClass.setClassName(cursor.getString(cursor.getColumnIndex(SchoolClass.KEY_CLASS_NAME)));
                    currentClass.setClassTeacherName(cursor.getString(cursor.getColumnIndex(SchoolClass.KEY_CLASS_INSTRUCTOR)));
                    currentClass.setClassDates(cursor.getString(cursor.getColumnIndex(SchoolClass.KEY_CLASS_DATE)));
                    currentClass.setUUID(cursor.getString(cursor.getColumnIndex(SchoolClass.KEY_HASH_ID)));

                    classes.add(currentClass);
                }
                while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to retrieve data: " + ex.getMessage());
            return null;
        }

        return classes;
    }

    /**
     * Method getAllAssignments() -
     *
     * This method gets all of the rows in the "assignments" table in the database and returns
     * them as a List containing Assignment objects.
     *
     * @return A List containing all of the Assignment objects from the database.
     */
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();

        try {
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + Assignment.TABLE;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Assignment currentAssignment = new Assignment();
                    currentAssignment.setAssignmentClassIdentifier(cursor.getString(cursor.getColumnIndex(Assignment.KEY_CLASS_ID)));
                    currentAssignment.setAssignmentName(cursor.getString(cursor.getColumnIndex(Assignment.KEY_ASSIGNMENT_NAME)));
                    currentAssignment.setAssignmentDueDate(cursor.getString(cursor.getColumnIndex(Assignment.KEY_ASSIGNMENT_DUE_DATE)));
                    currentAssignment.setUUID(cursor.getString(cursor.getColumnIndex(Assignment.KEY_HASH_ID)));

                    assignments.add(currentAssignment);
                }
                while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to retrieve data: " + ex.getMessage());
            return new ArrayList<>();
        }

        return assignments;
    }

    /**
     * Method getAllReminders() -
     *
     * This method gets all of the rows in the "reminders" table in the database and returns
     * them as a List containing Reminder objects.
     *
     * @return A List containing all of the Reminder objects from the database.
     */
    public List<Reminder> getAllReminders() {
        List<Reminder> reminders = new ArrayList<>();

        try {
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + Reminder.TABLE;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Reminder currentReminder = new Reminder();
                    currentReminder.setReminderName(cursor.getString(cursor.getColumnIndex(Reminder.KEY_ITEM_NAME)));
                    currentReminder.setReminderDate(cursor.getString(cursor.getColumnIndex(Reminder.KEY_ITEM_DATE)));
                    currentReminder.setReminderComments(cursor.getString(cursor.getColumnIndex(Reminder.KEY_ITEM_COMMENTS)));
                    currentReminder.setUUID(cursor.getString(cursor.getColumnIndex(Reminder.KEY_HASH_ID)));

                    reminders.add(currentReminder);
                }
                while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to retrieve data: " + ex.getMessage());
            return new ArrayList<>();
        }

        return reminders;
    }

    /**
     * Method getClassByUUID(String) -
     *
     * This method gets a row as a SchoolClass object identified by its assigned UUID.
     *
     * @param UUID A unique identifier representing the class to be retrieved.
     * @return A SchoolClass object representing the class identified by the UUID.
     */
    public SchoolClass getClassByUUID(String UUID) {
        List<SchoolClass> classes = getAllClasses();

        for (SchoolClass currentClass : classes) {
            if (currentClass.getUUID().equals(UUID)) {
                return currentClass;
            }
        }

        return null;
    }

    /**
     * Method getAssignmentByUUID(String) -
     *
     * This method gets a row as a Assignment object identified by its assigned UUID.
     *
     * @param UUID A unique identifier representing the assignment to be retrieved.
     * @return An Assignment object representing the assignment identified by the UUID.
     */
    public Assignment getAssignmentByUUID(String UUID) {
        List<Assignment> assignments = getAllAssignments();

        for (Assignment currentAssignment : assignments) {
            if (currentAssignment.getUUID().equals(UUID)) {
                return currentAssignment;
            }
        }

        return null;
    }

    /**
     * Method getReminderByUUID(String) -
     *
     * This method gets a row as a Reminder object identified by its assigned UUID.
     *
     * @param UUID A unique identifier representing the assignment to be retrieved.
     * @return A Reminder object representing the To Do list item identified by the UUID.
     */
    public Reminder getReminderByUUID(String UUID) {
        List<Reminder> reminders = getAllReminders();

        for (Reminder currentItem : reminders) {
            if (currentItem.getUUID().equals(UUID)) {
                return currentItem;
            }
        }

        return null;
    }
    //endregion

    //region -- Update Data (Update) --

    /**
     * Method updateClassRow(SchoolClass) -
     *
     * This method updates a row by using data from a SchoolClass object.
     *
     * @param schoolClass A SchoolClass object representing a row in the database to update.
     */
    public void updateClassRow(SchoolClass schoolClass) {
        ContentValues values = new ContentValues();
        values.put(SchoolClass.KEY_CLASS_ID, schoolClass.getClassIdentifier());
        values.put(SchoolClass.KEY_CLASS_NAME, schoolClass.getClassName());
        values.put(SchoolClass.KEY_CLASS_INSTRUCTOR, schoolClass.getClassTeacherName());
        values.put(SchoolClass.KEY_CLASS_DATE, schoolClass.getClassDates());
        values.put(SchoolClass.KEY_HASH_ID, schoolClass.getUUID());

        updateRow(SchoolClass.TABLE, values);
    }

    /**
     * Method updateAssignmentRow(Assignment) -
     *
     * This method updates a row by using data from an Assignment object.
     *
     * @param currentAssignment An Assignment object representing a row in the database to update.
     */
    public void updateAssignmentRow(Assignment currentAssignment) {
        ContentValues values = new ContentValues();
        values.put(Assignment.KEY_CLASS_ID, currentAssignment.getAssignmentClassIdentifier());
        values.put(Assignment.KEY_ASSIGNMENT_NAME, currentAssignment.getAssignmentName());
        values.put(Assignment.KEY_ASSIGNMENT_DUE_DATE, currentAssignment.getAssignmentDueDate());
        values.put(Assignment.KEY_HASH_ID, currentAssignment.getUUID());

        updateRow(Assignment.TABLE, values);
    }

    /**
     * Method updateReminderRow(Reminder) -
     *
     * This method updates a row by using data from a Reminder object.
     *
     * @param currentReminder A Reminder object representing a row in the database to update.
     */
    public void updateReminderRow(Reminder currentReminder) {
        ContentValues values = new ContentValues();
        values.put(Reminder.KEY_ITEM_NAME, currentReminder.getReminderName());
        values.put(Reminder.KEY_ITEM_DATE, currentReminder.getReminderDate());
        values.put(Reminder.KEY_HASH_ID, currentReminder.getUUID());

        updateRow(Reminder.TABLE, values);
    }

    /**
     * Method updateRow(String, ContentValues) -
     *
     * Common helper method used by other "update row"-type methods to update a row in the
     * database.
     *
     * @param tableName The name of the table where the row is located.
     * @param values A ContentValues mapping representing the KV pairs of the
     *               row going into the database.
     */
    private void updateRow(String tableName, ContentValues values) {
        try {
            SQLiteDatabase db = database.getWritableDatabase();

            String key = SchoolClass.KEY_HASH_ID; // These are the universal.

            db.update(tableName, values, key + " =? ", new String[]{ String.valueOf(values.get(key)) });

            if (db.isOpen()) {
                db.close();
            }
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to save data: " + ex.getMessage());
        }
    }
    //endregion

    //region -- Delete Data (Delete) --

    /**
     * Method deleteClassRow(SchoolClass) -
     *
     * This method deletes a row in the "school_classes" table.
     *
     * @param schoolClass A SchoolClass object whose data to use when deleting a row in the
     *                    database.
     */
    public void deleteClassRow(SchoolClass schoolClass) {
        ContentValues values = new ContentValues();
        values.put(SchoolClass.KEY_CLASS_ID, schoolClass.getClassIdentifier());
        values.put(SchoolClass.KEY_CLASS_NAME, schoolClass.getClassName());
        values.put(SchoolClass.KEY_CLASS_INSTRUCTOR, schoolClass.getClassTeacherName());
        values.put(SchoolClass.KEY_CLASS_DATE, schoolClass.getClassDates());
        values.put(SchoolClass.KEY_HASH_ID, schoolClass.getUUID());

        deleteRow(SchoolClass.TABLE, values);
    }

    /**
     * Method deleteAssignmentRow(Assignment) -
     *
     * This method deletes a row in the "assignments" table.
     *
     * @param currentAssignment An Assignment object whose data to use when deleting a row in the
     *                          database.
     */
    public void deleteAssignmentRow(Assignment currentAssignment) {
        ContentValues values = new ContentValues();
        values.put(Assignment.KEY_CLASS_ID, currentAssignment.getAssignmentClassIdentifier());
        values.put(Assignment.KEY_ASSIGNMENT_NAME, currentAssignment.getAssignmentName());
        values.put(Assignment.KEY_ASSIGNMENT_DUE_DATE, currentAssignment.getAssignmentDueDate());
        values.put(Assignment.KEY_HASH_ID, currentAssignment.getUUID());

        deleteRow(Assignment.TABLE, values);
    }

    /**
     * Method deleteReminderRow(Reminder) -
     *
     * This method deletes a row in the "reminders" table.
     *
     * @param currentReminder A Reminder object whose data to use when deleting a row in the
     *                        database.
     */
    public void deleteReminderRow(Reminder currentReminder) {
        ContentValues values = new ContentValues();
        values.put(Reminder.KEY_ITEM_NAME, currentReminder.getReminderName());
        values.put(Reminder.KEY_ITEM_DATE, currentReminder.getReminderDate());
        values.put(Reminder.KEY_HASH_ID, currentReminder.getUUID());

        deleteRow(Reminder.TABLE, values);
    }

    /**
     * Method deleteRow(String, ContentValues) -
     *
     * This is the common helper method used by other "delete row"-type methods to delete a row
     * from the database.
     *
     * @param tableName A String representing the table where the row is located.
     * @param values A ContentValues mapping representing the KV pairs of the
     *               row going into the database.
     */
    private void deleteRow(String tableName, ContentValues values) {
        try {
            SQLiteDatabase db = database.getWritableDatabase();
            String key = SchoolClass.KEY_HASH_ID; // Universally available

            db.delete(tableName, key + " =? ", new String[]{ String.valueOf(values.get(key)) });

            if (db.isOpen()) {
                db.close();
            }
        }
        catch (Exception ex) {
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Unable to delete item: " + ex.getMessage());
        }
    }
    //endregion
}
