package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogUtils {
    public interface AddRecordListener {
        void onAddRecord(String exerciseName, double time);
    }

    public static void openAddRecordDialog(Context context, final AddRecordListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_add_record, null);

        final EditText editTextExerciseName = dialogView.findViewById(R.id.editTextExerciseName);
        final EditText editTextTime = dialogView.findViewById(R.id.editTextTime);

        builder.setView(dialogView)
                .setTitle("Add Record")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    String ExerciseName = editTextExerciseName.getText().toString().trim();
                    String TimeText = editTextTime.getText().toString().trim();

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String exerciseName = editTextExerciseName.getText().toString().trim();
                        String timeText = editTextTime.getText().toString().trim();

                        if (TextUtils.isEmpty(exerciseName)) {
                            Toast.makeText(context, "Please fill in exercise name fields before saving", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(timeText)) {
                            Toast.makeText(context, "Please fill in time field before saving", Toast.LENGTH_SHORT).show();
                        } else {
                            double time = Double.parseDouble(timeText);

                            if (listener != null) {
                                listener.onAddRecord(exerciseName, time);
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.create().show();
    }
}
