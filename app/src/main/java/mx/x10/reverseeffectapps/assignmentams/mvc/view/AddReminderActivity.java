package mx.x10.reverseeffectapps.assignmentams.mvc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import mx.x10.reverseeffectapps.assignmentams.R;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.AddReminderActivityController;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;

/**
 * Class AddReminderActivity -> AppCompatActivity -
 *
 * This class handles the front end of an activity for adding a new To Do item.
 *
 * @author Robert
 * Date unknown.
 */
public class AddReminderActivity extends AppCompatActivity {

    /**
     * Overridden Method onCreate(Bundle) -
     *
     * Standard method for an entry point into the Activity.
     *
     * @param savedInstanceState A Bundle for restoring instance state. Unused.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Overridden Method onCreateOptionsMenu(Menu) -
     *
     * This method creates the menu in the ActionBar.
     *
     * @param menu The Menu object where the options will be inflated.
     * @return A boolean returned from the super class method indicating success.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_reminders_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Overridden Method onOptionsItemSelected(MenuItem) -
     *
     * This method handles the selection of an ActionBar menu item selection.
     *
     * @param item A MenuItem representing the item selected.
     * @return A boolean returned from the super class method indicating handling success.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_reminder_cancel) {
            finish();
        }
        else if (item.getItemId() == R.id.menu_item_add_reminder_save) {
            EditText reminderNameEditText = (EditText)findViewById(R.id.edit_text_add_reminder_name);
            EditText reminderDateEditText = (EditText)findViewById(R.id.edit_text_add_reminder_date);
            EditText reminderCommentEditText = (EditText)findViewById(R.id.edit_text_add_reminder_comments);

            Reminder currentReminder = new Reminder();
            currentReminder.setReminderName(reminderNameEditText.getText().toString());
            currentReminder.setReminderDate(reminderDateEditText.getText().toString());
            currentReminder.setReminderComments(reminderCommentEditText.getText().toString());
            currentReminder.generateUUID();

            AddReminderActivityController.addReminder(this, currentReminder);

            setResult(101);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
