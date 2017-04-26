package mx.x10.reverseeffectapps.assignmentams.mvc.controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mx.x10.reverseeffectapps.assignmentams.R;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils.ApplicationDelegate;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Reminder;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.SchoolClass;
import mx.x10.reverseeffectapps.assignmentams.mvc.view.AddClassActivity;
import mx.x10.reverseeffectapps.assignmentams.mvc.view.AddReminderActivity;
import mx.x10.reverseeffectapps.assignmentams.mvc.view.MainActivity;
import mx.x10.reverseeffectapps.assignmentams.mvc.view.ViewAssignmentsActivity;

/**
 * Class MainActivityController
 *
 * A class for controller methods from interacting from view to backend.
 *
 * @author Robert
 * Created 4/15/2017.
 */
@SuppressWarnings("FieldCanBeLocal")
public class MainActivityController {
    public static ListView assignmentMainMenuListView;
    public static ListView toDoListListView;
    public static RelativeLayout dashboardRelativeLayout;
    public static ListView settingsMainMenuListView;
    public static RelativeLayout noDataRelativeLayout;
    public static TextView noDataTextView;
    public static FloatingActionButton fab;
    public static TextView dashboardClassCountTextView;
    public static TextView dashboardAssignmentCountTextView;
    public static TextView dashboardReminderCountTextView;
    private static int currentPage = 0;

    private static final String LOG_TAG = "MainActivityController";
    public static boolean loggingEnabled = true;

    /**
     * Static method activatePage(int) -
     *
     * This method modifies the view based on the page that was selected on the navigation bar.
     *
     * @param page An int representing the selected page.
     */
    public static void activatePage(int page) {
        switch (page) {
            case 0: {
                assignmentMainMenuListView.setVisibility(View.VISIBLE);
                toDoListListView.setVisibility(View.GONE);
                dashboardRelativeLayout.setVisibility(View.GONE);
                settingsMainMenuListView.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
                currentPage = 0;

                if (ApplicationDelegate.currentClasses != null) {
                    if (ApplicationDelegate.currentClasses.size() == 0) {
                        toDoListListView.setVisibility(View.GONE);
                        noDataRelativeLayout.setVisibility(View.VISIBLE);
                        noDataTextView.setText("You have no classes! Press the button in the bottom right to add more!");
                    }
                    else {
                        noDataRelativeLayout.setVisibility(View.GONE);
                    }
                }
            } break;

            case 1: {
                assignmentMainMenuListView.setVisibility(View.GONE);
                toDoListListView.setVisibility(View.VISIBLE);
                dashboardRelativeLayout.setVisibility(View.GONE);
                settingsMainMenuListView.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
                currentPage = 1;

                if (ApplicationDelegate.currentReminders != null) {
                    if (ApplicationDelegate.currentReminders.size() == 0) {
                        toDoListListView.setVisibility(View.GONE);
                        noDataRelativeLayout.setVisibility(View.VISIBLE);
                        noDataTextView.setText("You have no To Dos! Press the button in the bottom right to add more!");
                    }
                    else {
                        noDataRelativeLayout.setVisibility(View.GONE);
                    }
                }
            } break;

            case 2: {
                assignmentMainMenuListView.setVisibility(View.GONE);
                toDoListListView.setVisibility(View.GONE);
                dashboardRelativeLayout.setVisibility(View.VISIBLE);
                settingsMainMenuListView.setVisibility(View.GONE);
                noDataRelativeLayout.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                currentPage = 2;
            } break;

            case 3: {
                assignmentMainMenuListView.setVisibility(View.GONE);
                toDoListListView.setVisibility(View.GONE);
                dashboardRelativeLayout.setVisibility(View.GONE);
                settingsMainMenuListView.setVisibility(View.VISIBLE);
                noDataRelativeLayout.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                currentPage = 3;
            } break;
        }
    }

    /**
     * Static method fabHandler(Context) -
     *
     * This method handles the press of the FloatingActionButton inside of MainActivity based on
     * selected page.
     *
     * @param context The Context in which the button press will be handled.
     */
    public static void fabHandler(Context context) {
        switch (currentPage) {
            case 0: {
                Intent intent = new Intent(context, AddClassActivity.class);
                ((MainActivity)context).startActivityForResult(intent, 101);
            } break;

            case 1: {
                Intent intent = new Intent(context, AddReminderActivity.class);
                ((MainActivity)context).startActivityForResult(intent, 101);
            } break;

            default: {
                if (loggingEnabled) Log.d(LOG_TAG, "Unable to handle action. Page doesn't exist or is invalid.");
            } break;
        }
    }

    /**
     * Static method release() -
     *
     * This method releases resources no longer needed.
     */
    public static void release() {
        assignmentMainMenuListView = null;
        toDoListListView = null;
        settingsMainMenuListView = null;
        noDataRelativeLayout = null;
        noDataTextView = null;
        fab = null;

        dashboardRelativeLayout = null;
    }

    /**
     * Static method reloadCallback(final Context) -
     *
     * This method handles the UI update after the data source has been reloaded.
     *
     * @param context The Context in which to update the UI.
     */
    private static void reloadCallback(final Context context) {
        ArrayAdapter<SchoolClass> classAdapter = new ArrayAdapter<SchoolClass>(context, R.layout.activity_main, ApplicationDelegate.currentClasses) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = ((MainActivity)context).getLayoutInflater().inflate(R.layout.item_class, null);
                }

                SchoolClass currentClass = ApplicationDelegate.currentClasses.get(position);

                TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_class_item_title);
                titleTextView.setText(currentClass.getClassName() + " - " + currentClass.getClassIdentifier());

                TextView subtitleTextView = (TextView)convertView.findViewById(R.id.text_view_class_item_subtitle);
                subtitleTextView.setText(currentClass.getClassTeacherName() + " (" + currentClass.getClassDates() + ")");

                return convertView;
            }
        };

        assignmentMainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ViewAssignmentsActivity.class);
                intent.putExtra("CLASS_ID", ApplicationDelegate.currentClasses.get(position).getClassIdentifier());
                ((MainActivity)context).startActivityForResult(intent, 101);
            }
        });
        assignmentMainMenuListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Do you really want to delete this class?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SchoolClass selectedClass = ApplicationDelegate.currentClasses.get(position);
                                DatabaseController controller = new DatabaseController(context);
                                controller.deleteClassRow(selectedClass);
                                reloadData(context);
                            }
                        })
                        .create();

                dialog.show();

                return false;
            }
        });

        assignmentMainMenuListView.setAdapter(classAdapter);

        ArrayAdapter<Reminder> reminderAdapter = new ArrayAdapter<Reminder>(context, R.layout.activity_main, ApplicationDelegate.currentReminders) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = ((MainActivity)context).getLayoutInflater().inflate(R.layout.item_class, null);
                }

                Reminder currentReminder = ApplicationDelegate.currentReminders.get(position);

                TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_class_item_title);
                titleTextView.setText(currentReminder.getReminderName());

                TextView subtitleTextView = (TextView)convertView.findViewById(R.id.text_view_class_item_subtitle);
                subtitleTextView.setText(currentReminder.getReminderDate());

                return convertView;
            }
        };

        toDoListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: View Reminder Activity and Controller
            }
        });
        toDoListListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Do you really want to delete this To Do?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Reminder selectedReminder = ApplicationDelegate.currentReminders.get(position);
                                DatabaseController controller = new DatabaseController(context);
                                controller.deleteReminderRow(selectedReminder);
                                reloadData(context);
                            }
                        })
                        .create();

                dialog.show();

                return false;
            }
        });
        toDoListListView.setAdapter(reminderAdapter);

        final String[] options = { "Developer", "Version", "About" };
        final String[] detail = { "Robert Brown", "1.0b", "[Arrow]" };
        ArrayAdapter<String> settingsAdapter = new ArrayAdapter<String>(context, R.layout.activity_main, options) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    if (detail[position].equals("[Arrow]")) {
                        convertView = ((MainActivity)context).getLayoutInflater().inflate(R.layout.item_settings_disclosure, null);
                    }
                    else {
                        convertView = ((MainActivity)context).getLayoutInflater().inflate(R.layout.item_settings_detail, null);
                    }
                }

                if (detail[position].equals("[Arrow]")) {
                    TextView textView = (TextView)convertView.findViewById(R.id.text_view_disclosure_title);
                    textView.setText(options[position]);
                }
                else {
                    TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_title);
                    titleTextView.setText(options[position]);

                    TextView subtitleTextView = (TextView)convertView.findViewById(R.id.text_view_subtitle);
                    subtitleTextView.setText(detail[position]);
                }

                return convertView;
            }
        };
        settingsMainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("About Assignment AMS")
                        .setMessage("This is an app intended for students to keep track of their" +
                                " assignments and when they are due" +
                                ". I intend to use a more detailed Date and Time system as" +
                                " well as possibly integrate cloud functionality. Please enjoy the " +
                                "app and let me know if you have any issues.")
                        .setPositiveButton("Ok", null)
                        .create();
                dialog.show();
            }
        });
        settingsMainMenuListView.setAdapter(settingsAdapter);

        // TODO: Fix dashboardAssignmentCountTextView not updating.
        dashboardClassCountTextView.setText(Integer.toString(ApplicationDelegate.currentClasses.size()));
        dashboardAssignmentCountTextView.setText(Integer.toString(ApplicationDelegate.currentAssignments.size()));
        dashboardReminderCountTextView.setText(Integer.toString(ApplicationDelegate.currentReminders.size()));

        if (currentPage == 0) {
            if (ApplicationDelegate.currentClasses.size() == 0) {
                assignmentMainMenuListView.setVisibility(View.GONE);
                noDataRelativeLayout.setVisibility(View.VISIBLE);
                noDataTextView.setText("You have no classes! Press the button in the bottom right to add more!");
            }
            else {
                noDataRelativeLayout.setVisibility(View.GONE);
            }
        }
        else if (currentPage == 1) {
            if (ApplicationDelegate.currentReminders.size() == 0) {
                toDoListListView.setVisibility(View.GONE);
                noDataRelativeLayout.setVisibility(View.VISIBLE);
                noDataTextView.setText("You have no To Dos! Press the button in the bottom right to add more!");
            }
            else {
                toDoListListView.setVisibility(View.VISIBLE);
                noDataRelativeLayout.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Static method reloadData(Context) -
     *
     * This method initiates the reloading of the data source and then the UI.
     *
     * @param context The Context in which to update the UI.
     */
    public static void reloadData(Context context) {
        new ReloadDataTask(context).execute();
    }

    /**
     * Internal class ReloadDataTask -> AsyncTask -
     *
     * This class handles background reloading of data. Will refactor at a later date. Will not
     * document due to that.
     */
    // TODO: Move to ApplicationDelegate class
    private static class ReloadDataTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        Context currentContext;

        private ReloadDataTask(Context context) {
            currentContext = context;

            if (context != null) {
                dialog = new ProgressDialog(context);
                dialog.setIndeterminate(true);
                dialog.setMessage("Reloading Data. Please Wait.");
                dialog.show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            ApplicationDelegate.reloadDataSource(currentContext);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (currentContext != null) {
                dialog.dismiss();
                reloadCallback(currentContext);
            }
        }
    }
}
