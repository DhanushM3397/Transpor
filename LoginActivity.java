package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button login, bt_reg;
    Spinner sp_role;
    EditText userId, password;
    String role;

    DatabaseHelper databaseHelper;
    ArrayList<RegModel> reglist;
    FunctionsCall functionsCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        functionsCall = new FunctionsCall();
        sp_role = findViewById(R.id.spinner);
        sp_role.setOnItemSelectedListener(this);
        userId = findViewById(R.id.et_user_name);
        userId.setText("");
        password = findViewById(R.id.et_password);
        password.setText("");
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        bt_reg = findViewById(R.id.bt_registration);
        bt_reg.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.open();
        reglist = databaseHelper.regModels();
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        role = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            for (int i = 0; i < reglist.size(); i++) {
                if (userId.getText().toString().equals(reglist.get(i).getNAME())) {
                    if (password.getText().toString().equals(reglist.get(i).getPASSWORD())) {
                        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i1);
                        finish();
                        return;
                    } else {
                        functionsCall.showToastMethod(this, "Please check your password");
                    }
                }else
                    functionsCall.showToastMethod(this,"please Register before login ");
            }


        }
        if (v.getId() == R.id.bt_registration) {
            Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(i);
        }
    }
}