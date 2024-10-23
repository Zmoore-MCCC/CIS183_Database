package com.example.cis183_database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    EditText et_j_main_userId;
    Button btn_j_main_login;
    TextView tv_j_userFname;
    Button btn_j_register;
    Button btn_j_findId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //setup GUI connections
        et_j_main_userId = findViewById(R.id.et_v_main_userId);
        btn_j_main_login = findViewById(R.id.btn_v_main_login);
        tv_j_userFname   = findViewById(R.id.tv_v_main_userFname);
        btn_j_register   = findViewById(R.id.btn_v_main_register);
        btn_j_findId     = findViewById(R.id.btn_v_main_FindId);

        //make a new instance of the dbHelper.
        dbHelper = new DatabaseHelper(this);

        //initialize all of the tables with dummy data
        //there is login in this function to ensure this is not done more than once.
        dbHelper.initAllTables();

        //Just used for testing
        checkAllTableCounts();

        loginButtonClickListener();
        registerButtonClickListener();
        findIdButtonClickListener();
    }
    private void findIdButtonClickListener()
    {
        btn_j_findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FindUserId.class));
            }
        });
    }
    //This is just used for testing.  I want to make sure that I can add data to my database.
    private void checkAllTableCounts()
    {
        Log.d("USERS COUNT:  ", dbHelper.countRecordsFromTable(dbHelper.getUserDbName()) + "");
        Log.d("POSTS COUNT:  ", dbHelper.countRecordsFromTable(dbHelper.getPostsDbName()) + "");
    }
    private void registerButtonClickListener()
    {
        btn_j_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
    private void loginButtonClickListener()
    {
        btn_j_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //get the id that was entered by the user
                int enteredId = Integer.parseInt(et_j_main_userId.getText().toString());
                //set the session data so we know who is logged int
                //this is important so we only show posts written by a specific person
                //SessionData.setLoggedInUser(enteredId);
                dbHelper.getAllUserDataGivenId(enteredId);
                //get the first name associated with this userid if it exists
                if(SessionData.getLoggedInUser() != null)
                {
                    //tv_j_userFname.setText(SessionData.getLoggedInUser().getFname());
                    startActivity(new Intent(MainActivity.this, WelcomePage.class));
                }
                else {
                    tv_j_userFname.setText("Error");
                }

            }
        });
    }

}