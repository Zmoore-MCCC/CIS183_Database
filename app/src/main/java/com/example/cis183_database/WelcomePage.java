package com.example.cis183_database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class WelcomePage extends AppCompatActivity
{
    TextView tv_j_loggedInUserName;
    TextView tv_j_numOfPosts;
    ListView lv_j_posts;
    DatabaseHelper dbHelper;
    Button btn_j_makePost;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_page);

        //setup GUI
        tv_j_loggedInUserName = findViewById(R.id.tv_v_wp_name);
        tv_j_numOfPosts       = findViewById(R.id.tv_v_wp_numPosts);
        lv_j_posts            = findViewById(R.id.lv_v_welcome_posts);
        btn_j_makePost        = findViewById(R.id.btn_v_welcome_makePost);

        dbHelper = new DatabaseHelper(this);

        setWelcomeMessage();
        setNumberOfPosts();
        setRecentPost();
        makePostButtonClickListener();
    }
    private void makePostButtonClickListener()
    {
        btn_j_makePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomePage.this, MakePost.class));
            }
        });
    }
    private void setWelcomeMessage()
    {
        tv_j_loggedInUserName.setText(SessionData.getLoggedInUser().getFname() + " " + SessionData.getLoggedInUser().getLname());
    }

    private void setNumberOfPosts()
    {
        int numPosts = dbHelper.getNumPostsGivenId(SessionData.getLoggedInUser().getId());

        tv_j_numOfPosts.setText(tv_j_numOfPosts.getText().toString() + " " + numPosts);
    }

    private void setRecentPost()
    {
//        Post p = dbHelper.getMostRecentPostGivenId(SessionData.getLoggedInUser().getId());
//        if(p != null)
//        {
//            tv_j_recentPost.setText(p.getPost());
//        }
//        else {
//            tv_j_recentPost.setText("You do not have any posts.  You should make one!");
//        }

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dbHelper.getAllPostsGivenUser());
        //lv_j_posts.setAdapter(adapter);
        ArrayList<String> posts = dbHelper.getAllPostsGivenUser();

        for(int i = 0; i < posts.size(); i++)
        {
            Log.d("Post: " , posts.get(i));
        }

    }
}