package mx.x10.reverseeffectapps.assignmentams.mvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import mx.x10.reverseeffectapps.assignmentams.R;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.MainActivityController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.ViewAssignmentsActivityController;

/**
 * Class ViewAssignmentsActivity -> AppCompatActivity -
 *
 * This class handles the front end of an activity for viewing assignments contained in the
 * database backend.
 *
 * @author Robert
 * Date unknown.
 */
public class ViewAssignmentsActivity extends AppCompatActivity {

    private final String LOG_TAG = ViewAssignmentsActivity.class.getName();

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
        setContentView(R.layout.activity_view_assignments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewAssignmentsActivityController.noDataRelativeLayout = (RelativeLayout)findViewById(R.id.relative_layout_view_assignments_activity_no_data);
        ViewAssignmentsActivityController.assignmentsListView = (ListView)findViewById(R.id.list_view_assignments);
        ViewAssignmentsActivityController.reloadData(this);
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
        inflater.inflate(R.menu.view_assignments_menu, menu);

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
        if (item.getItemId() == R.id.menu_view_assignments_add) {
            Intent intent = new Intent(this, AddAssignmentActivity.class);
            intent.putExtra("CLASS_ID", getIntent().getStringExtra("CLASS_ID"));
            startActivityForResult(intent, 102);
        }
        else if (item.getItemId() == R.id.menu_view_assignments_close) {
            setResult(101);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Overridden Method onActivityResult(int, int, Intent) -
     *
     * This method handles the result when children Activities finish their operations.
     *
     * @param requestCode An int representing the initial request for the Activity.
     * @param resultCode An int representing the final result for the Activity.
     * @param data An Intent containing data passed back from the child Activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 102) {
            ViewAssignmentsActivityController.reloadData(this);
            if (MainActivityController.loggingEnabled) Log.d(LOG_TAG, "Table should reload.");
        }
    }

    /**
     * Overridden Method onDestroy() -
     *
     * This method overrides super.onDestroy() to dispose of resources that won't be used.
     */
    @Override
    protected void onDestroy() {
        ViewAssignmentsActivityController.release();

        super.onDestroy();
    }
}
