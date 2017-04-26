package mx.x10.reverseeffectapps.assignmentams.mvc.model;

import java.util.UUID;

/**
 * Class Assignment -
 *
 * This class models the data to be used in both UI and database backend. It also provides
 * convenient getter and setter methods.
 *
 * @author Robert
 * Created 4/20/2017.
 */
public class Assignment {
    public static final String TABLE = "assignments";
    public static final String KEY_ID = "id";
    public static final String KEY_CLASS_ID = "classID";
    public static final String KEY_ASSIGNMENT_NAME = "assignment_name";
    public static final String KEY_ASSIGNMENT_DUE_DATE = "assignment_due_date";
    public static final String KEY_HASH_ID = "uuid";

    private String classID;
    private String assignmentName;
    private String assignmentDueDate;
    private String uuid;

    /**
     * Default Constructor Assignment() -
     *
     * This constructor initializes a new Assignment object with default values.
     */
    public Assignment() {
        assignmentName = "";
        assignmentDueDate = "";
        uuid = "";
    }

    /**
     * Constructor Assignment(String, String) -
     *
     * This constructor initializes a new Assignment object with custom values.
     *
     * @param assignmentName A String representing the name of the Assignment.
     * @param assignmentDueDate A String representing the due date of the Assignment.
     */
    public Assignment(String assignmentName, String assignmentDueDate) {
        this.assignmentName = assignmentName;
        this.assignmentDueDate = assignmentDueDate;
    }

    //region -- Getters --

    /**
     * Method getAssignmentName() -
     *
     * This method gets a String representing the name of the Assignment object.
     *
     * @return A String representing the name of the Assignment.
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Method getAssignmentDueDate() -
     *
     * This method gets a String representing the due date of the Assignment object.
     *
     * @return A String representing the due date of the Assignment.
     */
    public String getAssignmentDueDate() {
        return assignmentDueDate;
    }

    /**
     * Method getAssignmentClassIdentifier() -
     *
     * This method gets a String representing the class number to associate with an Assignment.
     *
     * @return A String representing the identifier of the class this assignment belongs to.
     */
    public String getAssignmentClassIdentifier() {
        return classID;
    }

    /**
     * Method getUUID() -
     *
     * This method gets a String representing a unique identifier to locate a particular
     * assignment in the database.
     *
     * @return A unique String representing the id of the assignment in the database.
     */
    public String getUUID() {
        return uuid;
    }
    //endregion

    //region -- Setters --

    /**
     * Method setAssignmentName(String) -
     *
     * This method sets the name of the assignment.
     *
     * @param assignmentName A String representing the name of the assignment.
     */
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    /**
     * Method setAssignmentDueDate(String) -
     *
     * This method sets the due date of the assignment.
     *
     * @param assignmentDueDate A String representing the due date of the assignment.
     */
    public void setAssignmentDueDate(String assignmentDueDate) {
        this.assignmentDueDate = assignmentDueDate;
    }

    /**
     * Method setAssignmentClassIdentifier(String) -
     *
     * This method sets the class associated with an assignment.
     *
     * @param classID A String representing a class for an assignment.
     */
    public void setAssignmentClassIdentifier(String classID) {
        this.classID = classID;
    }

    /**
     * Method generateUUID() -
     *
     * This method generates a unique identifier to use with the internal database.
     */
    public void generateUUID() {
        uuid = String.valueOf(UUID.randomUUID());
    }

    /**
     * Method setUUID(String) -
     *
     * This method manually sets a UUID identifier to use with the internal database.
     *
     * @param uuid A String identifier to identify an assignment in the database.
     */
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
    //endregion
}
