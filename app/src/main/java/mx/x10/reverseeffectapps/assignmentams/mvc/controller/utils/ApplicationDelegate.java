package mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Assignment;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.SchoolClass;

/**
 * Class ApplicationDelegate -
 *
 * This class is intended to hold the data source as well as provide other application-wide
 * operations. This class is intended to be kept small as to avoid the "God class" construct.
 *
 * @author Robert
 * Created 4/20/2017.
 */
public class ApplicationDelegate {
    public static List<SchoolClass> currentClasses;
    public static List<Assignment> currentAssignments;
    public static List<Reminder> currentReminders;

    /**
     * Method showToast(Context, String) -
     *
     * This convenience method provides quick Toast functionality.
     *
     * @param context The Context in which to display the Toast.
     * @param message A String representing the message to display in the Toast.
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method reloadDataSource(Context) -
     *
     * This convenience method provides quick reloading of data source from the database.
     *
     * @param context A context to pass to the DatabaseController and then the Database objects.
     */
    public static void reloadDataSource(Context context) {
        DatabaseController controller = new DatabaseController(context);
        currentClasses = controller.getAllClasses();
        currentAssignments = controller.getAllAssignments();
        currentReminders = controller.getAllReminders();
    }
}
