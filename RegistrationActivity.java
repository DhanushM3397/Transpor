package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    EditText name, email, phonenum, password;
    Button reg;
    FunctionsCall functionsCall;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    public void init() {
        functionsCall = new FunctionsCall();
        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.reg_name);
        email = findViewById(R.id.reg_email);
        phonenum = findViewById(R.id.reg_phone);
        password = findViewById(R.id.reg_password);
        reg = findViewById(R.id.reg_signin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    public void validation() {
        int s = phonenum.getText().toString().length();
        if (name.getText().toString().isEmpty()) {
            functionsCall.showToastMethod(this, "please enter the  name");
            return;
        }
        if (!isValidEmailId(email.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phonenum.getText().toString().isEmpty()) {
                functionsCall.showToastMethod(this, "please enter the  phonenum");
            return;
        }
        else if(phonenum.getText().toString().length() != 10){
            functionsCall.showToastMethod(this,"please enter correct num");
            return;
        }
        if (password.getText().toString().isEmpty()) {
            functionsCall.showToastMethod(this, "please enter the  password");
            return;
        }

        RegModel regModel = new RegModel();
        regModel.setNAME(name.getText().toString());
        regModel.setEMAIL(email.getText().toString());
        regModel.setPHONE(phonenum.getText().toString());
        regModel.setPASSWORD(password.getText().toString());
        databaseHelper.Regdata_insert(regModel);

        Toast.makeText(this, "Registration success", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


}