package mx.x10.reverseeffectapps.assignmentams.mvc.model;

import java.util.UUID;

/**
 * Class Reminder -
 *
 * This class models the data to be used in both UI and database backend. It also provides
 * convenient getter and setter methods.
 *
 * @author Robert
 * Created 4/21/2017.
 */
public class Reminder {
    public static final String TABLE = "reminders";
    public static final String KEY_ID = "id";
    public static final String KEY_ITEM_NAME = "name";
    public static final String KEY_ITEM_DATE = "date";
    public static final String KEY_ITEM_COMMENTS = "comments";
    public static final String KEY_HASH_ID = "uuid";

    private String reminderName;
    private String reminderDate;
    private String reminderComments;
    private String uuid;

    /**
     * Default Constructor Reminder() -
     *
     * This constructor initializes a new Reminder object with default values.
     */
    public Reminder() {
        reminderName = "";
        reminderDate = "";
        reminderComments = "";
        uuid = "";
    }

    /**
     * Constructor Reminder(String, String, String) -
     *
     * This constructor initializes a new Reminder object with custom values.
     *
     * @param reminderName A String representing the To Do name.
     * @param reminderDate A String representing the To Do date.
     * @param reminderComments A String representing extra comments for the To Do.
     */
    public Reminder(String reminderName, String reminderDate, String reminderComments) {
        this.reminderName = reminderName;
        this.reminderDate = reminderDate;
        this.reminderComments = reminderComments;
        uuid = String.valueOf(UUID.randomUUID());
    }

    /**
     * Constructor Reminder(String, String, String, String) -
     *
     * This constructor initializes a new Reminder object with custom values.
     *
     * @param reminderName A String representing the To Do name.
     * @param reminderDate A String representing the To DO date.
     * @param reminderComments A String representing extra comments for the To Do.
     * @param uuid A String representing the unique identifier to identify a Reminder from the
     *             database.
     */
    public Reminder(String reminderName, String reminderDate, String reminderComments, String uuid) {
        this.reminderName = reminderName;
        this.reminderDate = reminderDate;
        this.reminderComments = reminderComments;
        this.uuid = uuid;
    }

    //region -- Setters --

    /**
     * Method setReminderName(String) -
     *
     * This method sets the name for a To Do item.
     *
     * @param reminderName A String representing the name of a To Do item.
     */
    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    /**
     * Method setReminderDate(String) -
     *
     * This method sets the date for a To Do item.
     *
     * @param reminderDate A String representing the date of a To Do item.
     */
    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    /**
     * Method setReminderComments(String) -
     *
     * This method sets the comments for a To Do item.
     *
     * @param reminderComments A String representing the comments for a To Do item.
     */
    public void setReminderComments(String reminderComments) {
        this.reminderComments = reminderComments;
    }

    /**
     * Method setUUID(String) -
     *
     * This method sets the unique identifier to locate a To Do item in the database.
     *
     * @param uuid A String representing the unique identifier to locate a To Do item in
     *             the database.
     */
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Method generateUUID() -
     *
     * This method generates a unique identifier for locating the To Do item in the database
     * backend.
     */
    public void generateUUID() {
        uuid = String.valueOf(UUID.randomUUID());
    }
    //endregion

    //region -- Getters --

    /**
     * Method getReminderName() -
     *
     * This method gets the name of a To Do item.
     *
     * @return A String representing the name of a To Do item.
     */
    public String getReminderName() {
        return reminderName;
    }

    /**
     * Method getReminderDate() -
     *
     * This method gets the date of a To Do item.
     *
     * @return A String representing the date of a To Do item.
     */
    public String getReminderDate() {
        return reminderDate;
    }

    /**
     * Method getReminderComments() -
     *
     * This method gets the comments of a To Do item.
     *
     * @return A String representing the comments of a To Do item.
     */
    public String getReminderComments() {
        return reminderComments;
    }

    /**
     * Method getUUID() -
     *
     * This method gets the identifier of a To Do item for locating in the database backend.
     *
     * @return A String representing the identifier of a To Do item for locating in the backend.
     */
    public String getUUID() {
        return uuid;
    }
    //endregion
}
