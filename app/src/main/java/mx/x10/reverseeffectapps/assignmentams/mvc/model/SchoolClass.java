package mx.x10.reverseeffectapps.assignmentams.mvc.model;

import java.util.UUID;

/**
 * Class SchoolClass -
 *
 * This class models the data to be used in both UI and database backend. It also provides
 * convenient getter and setter methods.
 *
 * @author Robert
 * Created 4/15/2017.
 */
public class SchoolClass {
    public static final String TABLE = "school_classes";
    public static final String KEY_ID = "id";
    public static final String KEY_CLASS_NAME = "class_name";
    public static final String KEY_CLASS_ID = "class_id";
    public static final String KEY_CLASS_DATE = "class_date";
    public static final String KEY_CLASS_INSTRUCTOR = "class_instructor";
    public static final String KEY_HASH_ID = "uuid";

    private String className;
    private String classIdentifier;
    private String classTeacherName;
    private String classDates;
    private String uuid;

    /**
     * Default Constructor SchoolClass() -
     *
     * This constructor initializes a new Reminder object with default values.
     */
    public SchoolClass() {
        className = "";
        classIdentifier = "";
        classTeacherName = "";
        classDates = "";
        uuid = "";
    }

    /**
     * Constructor SchoolClass(String, String, String, String) -
     *
     * This constructor initializes a new SchoolClass object with custom values.
     *
     * @param className A String representing the name of the class.
     * @param classIdentifier A String representing the school-assigned id of the class.
     * @param classTeacherName A String representing the name of the class instructor.
     * @param classDates A String representing the days on which the student attends the class.
     */
    public SchoolClass(String className, String classIdentifier, String classTeacherName,
                       String classDates) {
        this.className = className;
        this.classIdentifier = classIdentifier;
        this.classTeacherName = classTeacherName;
        this.classDates = classDates;
    }

    //region -- Getters --

    /**
     * Method getClassName() -
     *
     * This method gets the name of the class the student is attending.
     *
     * @return A String representing the name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Method getClassIdentifier() -
     *
     * This method gets the school-assigned id for the class.
     *
     * @return A String representing the id of the class.
     */
    public String getClassIdentifier() {
        return classIdentifier;
    }

    /**
     * Method getClassTeacherName() -
     *
     * This method gets the name of the instructor for the class.
     *
     * @return A String representing the name of the instructor.
     */
    public String getClassTeacherName() {
        return classTeacherName;
    }

    /**
     * Method getClassDates() -
     *
     * This method gets the days on which the student attends a class.
     *
     * @return A String representing the days on which the Student attends a class.
     */
    public String getClassDates() {
        return classDates;
    }

    /**
     * Method getUUID() -
     *
     * This method gets the unique identifier for locating a class in the database backend.
     *
     * @return A String representing the class for locating in the database backend.
     */
    public String getUUID() {
        return uuid;
    }
    //endregion

    //region -- Setters --

    /**
     * Method setClassName(String) -
     *
     * This method sets the name of a class.
     *
     * @param className A String representing the name of the class.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Method setClassIdentifier(String) -
     *
     * This method sets the school-assigned id of a class.
     *
     * @param classIdentifier A String representing the school-assigned id of a class.
     */
    public void setClassIdentifier(String classIdentifier) {
        this.classIdentifier = classIdentifier;
    }

    /**
     * Method setClassTeacherName(String) -
     *
     * This method sets the instructor's name of a class.
     *
     * @param classTeacherName A String representing the instructor's name of a class.
     */
    public void setClassTeacherName(String classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    /**
     * Method setClassDates(String) -
     *
     * This method sets the dates of a class.
     *
     * @param classDates A String representing the dates in which the student attends a class.
     */
    public void setClassDates(String classDates) {
        this.classDates = classDates;
    }

    /**
     * Method generateUUID() -
     *
     * This method generates a new unique identifier for locating a class in the database backend.
     */
    public void generateUUID() {
        uuid = String.valueOf(UUID.randomUUID());
    }

    /**
     * Method setUUID(String) -
     *
     * This method manually sets a unique identifier for locating a class in the database backend.
     *
     * @param uuid A String representing the unique identifier for locating a class in the
     *             database backend.
     */
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
    //endregion
}
