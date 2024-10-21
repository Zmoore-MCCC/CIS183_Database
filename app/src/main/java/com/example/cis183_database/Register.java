package com.example.cis183_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    EditText et_j_fname;
    EditText et_j_lname;
    EditText et_j_email;
    Button btn_j_back;
    Button btn_j_register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        et_j_fname = findViewById(R.id.et_v_register_fname);
        et_j_lname = findViewById(R.id.et_v_register_lname);
        et_j_email = findViewById(R.id.et_v_register_email);
        btn_j_back = findViewById(R.id.btn_v_register_back);
        btn_j_register = findViewById(R.id.btn_v_register_register);

        db = new DatabaseHelper(this);

        backButtonClickEventListener();
        registerButtonClickEventListener();

    }

    private void backButtonClickEventListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }
    private void registerButtonClickEventListener()
    {
        btn_j_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fname = et_j_fname.getText().toString();
                String lname = et_j_lname.getText().toString();
                String email = et_j_email.getText().toString();

                if(!fname.isEmpty() && !lname.isEmpty() && !email.isEmpty())
                {
                    User u = new User();
                    u.setFname(fname);
                    u.setLname(lname);
                    u.setEmail(email);
                    db.addUserToDB(u);
                }
            }
        });
    }
}