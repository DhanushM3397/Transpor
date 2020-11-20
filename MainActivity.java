package com.example.transport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int CAMERA_REQUEST = 1888;
    EditText et_name, et_phoneno, et_type_of_tank, et_ro_name, et_district_office_name, et_project_office_name,
            et_village_name, et_tank_name, et_work_start_time, et_number_of_machine_deployed, et_hitachi_work_start_time,
            et_hitachi_work_end_time, et_jcb_work_start_time, et_jcb_work_end_time,
            et_silt_transportation, et_no_silt_transportation;
    Button bt_submit, bt_report;
    ImageView take_image;
    FunctionsCall functionsCall;
    DatabaseHelper databaseHelper;
    Spinner spinner;
    String spiner_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        functionsCall = new FunctionsCall();
        et_name = findViewById(R.id.et_name);
        bt_report = findViewById(R.id.bt_report);
        et_phoneno = findViewById(R.id.et_phoneno);
        et_type_of_tank = findViewById(R.id.et_type_of_tank);
        et_ro_name = findViewById(R.id.et_ro_name);
        et_district_office_name = findViewById(R.id.et_district_office_name);
        et_project_office_name = findViewById(R.id.et_project_office_name);
        et_village_name = findViewById(R.id.et_village_name);
        et_tank_name = findViewById(R.id.et_tank_name);
        et_work_start_time = findViewById(R.id.et_work_start_time);
        et_number_of_machine_deployed = findViewById(R.id.et_number_of_machine_deployed);
        et_hitachi_work_start_time = findViewById(R.id.et_hitachi_work_start_time);
        et_hitachi_work_end_time = findViewById(R.id.et_hitachi_work_end_time);
        et_jcb_work_start_time = findViewById(R.id.et_jcb_work_start_time);
        et_jcb_work_end_time = findViewById(R.id.et_jcb_work_end_time);
        et_silt_transportation = findViewById(R.id.et_silt_transportation);
        et_no_silt_transportation = findViewById(R.id.et_no_silt_transportation);
        bt_submit = findViewById(R.id.bt_submit);

       /* take_image = findViewById(R.id.take_image);
        take_image.setOnClickListener(this);*/
        spinner = findViewById(R.id.spinner1);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(this);


        databaseHelper = new DatabaseHelper(this);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inertData();
            }
        });
        bt_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReportDisplay.class));
            }
        });
    }

    private void inertData() {
        if (TextUtils.isEmpty(spiner_value)) {
            Toast.makeText(this, "please select the Role", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            et_name.setError("Enter Name");
            return;
        }

        if (TextUtils.isEmpty(et_phoneno.getText().toString())) {
            et_phoneno.setError("Enter Phone");
            return;
        } else if (et_phoneno.getText().toString().length() != 10) {
            functionsCall.showToastMethod(this, "please enter correct num");
            return;
        }


        if (TextUtils.isEmpty(et_type_of_tank.getText().toString())) {
            et_type_of_tank.setError("Enter Type of Tank");
            return;
        }

        if (TextUtils.isEmpty(et_ro_name.getText().toString())) {
            et_ro_name.setError("Enter RO Number");
            return;
        }

        if (TextUtils.isEmpty(et_district_office_name.getText().toString())) {
            et_district_office_name.setError("Enter DISTRICT OFFICE NAME");
            return;
        }

        if (TextUtils.isEmpty(et_project_office_name.getText().toString())) {
            et_project_office_name.setError("Enter PROJECT OFFICE NAME");
            return;
        }

        if (TextUtils.isEmpty(et_village_name.getText().toString())) {
            et_village_name.setError("Enter VILLAGE NAME");
            return;
        }

        if (TextUtils.isEmpty(et_tank_name.getText().toString())) {
            et_tank_name.setError("Enter TANK NAME");
            return;
        }

        if (TextUtils.isEmpty(et_work_start_time.getText().toString())) {
            et_work_start_time.setError("Enter WORK START TIME");
            return;
        }

        if (TextUtils.isEmpty(et_number_of_machine_deployed.getText().toString())) {
            et_number_of_machine_deployed.setError("Enter NO MACHINE DEPLOYED");
            return;
        }


        if (TextUtils.isEmpty(et_hitachi_work_start_time.getText().toString())) {
            et_hitachi_work_start_time.setError("Enter HITACHI WORK START TIME");
            return;
        }

        if (TextUtils.isEmpty(et_hitachi_work_end_time.getText().toString())) {
            et_hitachi_work_end_time.setError("Enter HITACHI WORK END TIME");
            return;
        }

        if (TextUtils.isEmpty(et_jcb_work_start_time.getText().toString())) {
            et_jcb_work_start_time.setError("Enter HITACHI WORK END TIME");
            return;
        }

        if (TextUtils.isEmpty(et_jcb_work_end_time.getText().toString())) {
            et_jcb_work_end_time.setError("Enter JCB WORK END TIME");
            return;
        }
        if (TextUtils.isEmpty(et_silt_transportation.getText().toString())) {
            et_silt_transportation.setError("Enter SILT TRANSPORTATION");
            return;
        }

        if (TextUtils.isEmpty(et_no_silt_transportation.getText().toString())) {
            et_no_silt_transportation.setError("Enter No SILT TRANSPORTATION");
            return;
        }

        Transport_Model transport_model = new Transport_Model();
        transport_model.setRole(spiner_value);
        transport_model.setNAME(et_name.getText().toString());
        transport_model.setPHONENO(et_phoneno.getText().toString());
        transport_model.setTYPE_OF_TANK(et_type_of_tank.getText().toString());
        transport_model.setRO_NAME(et_ro_name.getText().toString());
        transport_model.setDISTRICT_OFFICE_NAME(et_district_office_name.getText().toString());
        transport_model.setPROJECT_OFFICE_NAME(et_project_office_name.getText().toString());
        transport_model.setVILLAGE_NAME(et_village_name.getText().toString());
        transport_model.setTANK_NAME(et_tank_name.getText().toString());
        transport_model.setWORK_START_TIME(et_work_start_time.getText().toString());
        transport_model.setNO_MACHINE_DEPLOYED(et_number_of_machine_deployed.getText().toString());
        transport_model.setHITACHI_WORK_START_TIME(et_hitachi_work_start_time.getText().toString());
        transport_model.setHITACHI_WORK_END_TIME(et_hitachi_work_end_time.getText().toString());
        transport_model.setJCB_WORK_START_TIME(et_jcb_work_start_time.getText().toString());
        transport_model.setJCB_WORK_END_TIME(et_jcb_work_end_time.getText().toString());
        transport_model.setSILT_TRANSPORTATION(et_silt_transportation.getText().toString());
        transport_model.setNO_SILT_TRANSPORTATION(et_no_silt_transportation.getText().toString());

        databaseHelper.insertdata(transport_model, databaseHelper);
        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
    }

    //---------------------------------------- spinner value selection---------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spiner_value = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Role = " + spiner_value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

  /*  @Override
    public void onClick(View v) {
        if (v.getId() == R.id.take_image) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            take_image.setImageBitmap(photo);
        }
    }*/
    /* public void camera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile()));//
        startActivityForResult(intent, REQUEST_CODE);
    }*/

  /*  private File getOutputMediaFile() {
        // make a new file directory inside the "sdcard" folder
        File mediaStorageDir = new File(
                Environment.getExternalStorageDirectory(), "/myApp");

        // if this "Camera folder does not exist
        if (!mediaStorageDir.exists()) {
            // if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // take the current timeStamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date(System.currentTimeMillis()));
        File mediaFile;
        // and make a media file:
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        String currentFilePath = mediaFile.getAbsolutePath();
        return mediaFile;
    }*/
}