package com.example.transport;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;

public class FunctionsCall extends Application {

    public String filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), AppFolderName()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.toString();
    }

    public String AppFolderName() {
        return "TRANSPORT_FILES" + File.separator + "data" + File.separator + "files";
    }

    public void showToastMethod(Context context,String msg) {
        Toast.makeText(context, " "+msg, Toast.LENGTH_SHORT).show();

    }


}
