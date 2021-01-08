package com.example.grabfood.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.example.grabfood.R;

public class CustomAlertDialog {
    public static void showAlertDialog(final Context context)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Confirmation").setMessage("Do you want to close this app?");

        //
        builder.setCancelable(true);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"You choose Yes button",
                        Toast.LENGTH_SHORT).show();
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
    }
}
