package mx.x10.reverseeffectapps.assignmentams.mvc.controller;

import android.content.Context;

import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils.ApplicationDelegate;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;

/**
 * Class AddReminderActivityController -
 *
 * A class for controller methods from interacting from view to backend.
 *
 * @author Robert
 * Created 4/22/2017.
 */
public class AddReminderActivityController {

    /**
     * Static method addReminder(Context, Reminder) -
     *
     * This method interacts with a DatabaseController object to insert a row into the
     * "reminders" table and the display a Toast message to the user.
     *
     * @param context The Context in which to display a Toast message.
     * @param currentReminder A Reminder object representing the To Do item that the student
     *                        wishes to enter into the database.
     */
    public static void addReminder(Context context, Reminder currentReminder) {
        DatabaseController controller = new DatabaseController(context);
        controller.insertReminderRow(currentReminder);
        ApplicationDelegate.showToast(context, "Your To Do has been saved.");
    }
}
