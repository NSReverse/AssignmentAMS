package mx.x10.reverseeffectapps.assignmentams.mvc.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.x10.reverseeffectapps.assignmentams.R;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.database.DatabaseController;
import mx.x10.reverseeffectapps.assignmentams.mvc.controller.utils.ApplicationDelegate;
import mx.x10.reverseeffectapps.assignmentams.mvc.model.Assignment;
import mx.x10.reverseeffectapps.assignmentams.mvc.view.ViewAssignmentsActivity;

/**
 * Class ViewAssignmentsActivityController -
 *
 * A class for controller methods from interacting with view and backend.
 *
 * @author Robert
 * Created 4/22/2017.
 */
public class ViewAssignmentsActivityController {
    public static ListView assignmentsListView;         // ViewAssignmentsActivity.onDestroy()
    public static RelativeLayout noDataRelativeLayout;  // ViewAssignmentsActivity.onDestroy()

    /**
     * Static method reloadData(final Context) -
     *
     * This method reloads the data source and then updates the UI to display a fresh list of
     * assignments in a given class.
     *
     * @param context The Context in which to update the UI.
     */
    public static void reloadData(final Context context) {
        ApplicationDelegate.reloadDataSource(context);

        List<Assignment> assignments = new ArrayList<>(ApplicationDelegate.currentAssignments);
        final List<Assignment> temp = new ArrayList<>();

        String classID = ((ViewAssignmentsActivity)context).getIntent().getStringExtra("CLASS_ID");

        for (Assignment currentAssignment : assignments) {
            if (currentAssignment.getAssignmentClassIdentifier().equals(classID)) {
                temp.add(currentAssignment);
            }
        }

        ArrayAdapter<Assignment> adapter = new ArrayAdapter<Assignment>(context, R.layout.content_view_assignments, temp) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = ((ViewAssignmentsActivity)context).getLayoutInflater().inflate(R.layout.item_assignment, null);
                }

                Assignment currentAssignment = temp.get(position);

                TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_assignment_item_title);
                titleTextView.setText(currentAssignment.getAssignmentName());

                TextView subtitleTextView = (TextView)convertView.findViewById(R.id.text_view_assignment_item_subtitle);
                subtitleTextView.setText(currentAssignment.getAssignmentDueDate());

                return convertView;
            }
        };

        assignmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle(temp.get(position).getAssignmentName())
                        .setMessage("Due on: " + temp.get(position).getAssignmentDueDate())
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Assignment selectedAssignment = temp.get(position);

                                DatabaseController controller = new DatabaseController(context);
                                controller.deleteAssignmentRow(selectedAssignment);
                                ApplicationDelegate.showToast(context, "The assignment (" + selectedAssignment.getAssignmentName() + ") has been deleted.");

                                ViewAssignmentsActivityController.reloadData(context);
                            }
                        })
                        .create();

                dialog.show();
            }
        });

        assignmentsListView.setAdapter(adapter);

        if (temp.size() == 0) {
            assignmentsListView.setVisibility(View.GONE);
            noDataRelativeLayout.setVisibility(View.VISIBLE);
        }
        else {
            assignmentsListView.setVisibility(View.VISIBLE);
            noDataRelativeLayout.setVisibility(View.GONE);
        }
    }

    public static void release() {
        assignmentsListView = null;
        noDataRelativeLayout = null;
    }
}
