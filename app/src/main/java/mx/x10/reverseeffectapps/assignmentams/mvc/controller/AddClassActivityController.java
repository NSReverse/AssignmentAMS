package mx.x10.reverseeffectapps.assignmentams.mvc.controller;

import android.content.Context;

import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils.ApplicationDelegate;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.SchoolClass;

/**
 * Class AddClassActivityController -
 *
 * A class for controller methods from interacting from view to backend.
 *
 * @author Robert
 * Created 4/21/2017.
 */
public class AddClassActivityController {

    /**
     * Static method addClass(Context, SchoolClass) -
     *
     * This method interacts with a DatabaseController object to insert a row into the
     * "school_classes" table and then display a Toast message to the user.
     *
     * @param context The Context in which to display a Toast message.
     * @param currentClass A SchoolClass object representing the class that the student wishes
     *                     to enter into the database.
     */
    public static void addClass(Context context, SchoolClass currentClass) {
        DatabaseController controller = new DatabaseController(context);
        controller.insertClassRow(currentClass);
        ApplicationDelegate.showToast(context, "Your class has been saved.");
    }
}
