package mx.x10.reverseeffectapps.assignmentams.mvc.controller;

import android.content.Context;

import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils.ApplicationDelegate;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Assignment;

/**
 * Class AddAssignmentActivityController -
 *
 * A class for controller methods from interacting from view to backend.
 *
 * @author Robert
 * Created 4/22/2017.
 */
public class AddAssignmentActivityController {

    /**
     * Static method addAssignment(Context, Assignment) -
     *
     * This method interacts with a DatabaseController object to insert a row into the
     * "assignments" table and then display a Toast message to the user.
     *
     * @param context The Context in which to display a Toast message.
     * @param currentAssignment An Assignment object representing the assignment that the
     *                          student wishes to enter into the database.
     */
    public static void addAssignment(Context context, Assignment currentAssignment) {
        DatabaseController controller = new DatabaseController(context);
        controller.insertAssignmentRow(currentAssignment);
        ApplicationDelegate.showToast(context, "Your new assignment has been saved.");
    }
}
