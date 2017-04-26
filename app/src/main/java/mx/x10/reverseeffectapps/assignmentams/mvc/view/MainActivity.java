package mx.x10.reverseeffectapps.assignmentams.mvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mx.x10.reverseeffectapps.assignmentams.R;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.MainActivityController;

/**
 * Class MainActivity -> AppCompatActivity -
 *
 * This class handles the front end of an activity for the main interface of the app.
 *
 * @author Robert
 * Date unknown.
 */
public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MainActivityController.activatePage(0);
                    return true;
                case R.id.navigation_other:
                    MainActivityController.activatePage(1);
                    return true;
                case R.id.navigation_dashboard:
                    MainActivityController.activatePage(2);
                    return true;
                case R.id.navigation_notifications:
                    MainActivityController.activatePage(3);
                    return true;
            }
            return false;
        }

    };

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
        setContentView(R.layout.activity_main);

        MainActivityController.assignmentMainMenuListView = (ListView)findViewById(R.id.listView_assignments_mainMenu);
        MainActivityController.toDoListListView = (ListView)findViewById(R.id.listView_toDoList);
        MainActivityController.dashboardRelativeLayout = (RelativeLayout)findViewById(R.id.relative_layout_dashboard);
        MainActivityController.settingsMainMenuListView = (ListView)findViewById(R.id.listView_settings_mainMenu);
        MainActivityController.noDataRelativeLayout = (RelativeLayout)findViewById(R.id.relative_layout_main_activity_no_data);
        MainActivityController.noDataTextView = (TextView)findViewById(R.id.text_view_no_data);
        MainActivityController.fab = (FloatingActionButton)findViewById(R.id.fab);
        MainActivityController.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityController.fabHandler(MainActivity.this);
            }
        });

        MainActivityController.dashboardClassCountTextView = (TextView)findViewById(R.id.text_view_dashboard_class_count);
        MainActivityController.dashboardAssignmentCountTextView = (TextView)findViewById(R.id.text_view_dashboard_assignment_count);
        MainActivityController.dashboardReminderCountTextView = (TextView)findViewById(R.id.text_view_dashboard_reminder_count);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        MainActivityController.reloadData(this);
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

        // If statements preserved in case I wanted to add more functionality to it.
        if (requestCode == 101) {
            MainActivityController.reloadData(this);
        }
    }

    /**
     * Overridden Method onDestroy() -
     *
     * This method overrides super.onDestroy() to remove unneeded resources from memory.
     */
    @Override
    protected void onDestroy() {
        MainActivityController.release();

        super.onDestroy();
    }
}
