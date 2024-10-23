package com.example.cis183_database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FindUserId extends AppCompatActivity
{
    Button btn_j_back;
    Button btn_j_find;
    ListView lv_j_usersFound;
    EditText et_j_fname;
    EditText et_j_lname;
    DatabaseHelper db;
    ArrayList<String> foundUsers;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_user_id);

        btn_j_back      = findViewById(R.id.btn_v_findUser_back);
        btn_j_find      = findViewById(R.id.btn_v_findUser_Find);
        lv_j_usersFound = findViewById(R.id.lv_v_findUser_listOfUsers);
        et_j_fname      = findViewById(R.id.et_v_findUser_fname);
        et_j_lname      = findViewById(R.id.et_v_findUser_lName);

        db = new DatabaseHelper(this);
        foundUsers = new ArrayList<String>();

        backButtonClickListener();
        findUsersButtonClickListener();

    }

    private void backButtonClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindUserId.this, MainActivity.class));
            }
        });
    }

    private void findUsersButtonClickListener()
    {
        btn_j_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //grab the data that is in the textboxes.
                //the user will not have to fill out both
                String fname = "";
                String lname = "";

                //grab data from the textboxes
                fname = et_j_fname.getText().toString();
                lname = et_j_lname.getText().toString();

                foundUsers = db.findUserGivenCriteria(fname, lname);

                //Object[] arr = foundUsers.toArray();

                //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
                for(int i = 0; i < foundUsers.size(); i++)
                {
                    Log.d("User: " , foundUsers.get(i));
                }
            }
        });
    }
}