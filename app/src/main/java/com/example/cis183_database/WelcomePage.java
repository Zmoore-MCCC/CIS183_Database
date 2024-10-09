package com.example.cis183_database;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomePage extends AppCompatActivity
{
    TextView tv_j_loggedInUserName;
    TextView tv_j_numOfPosts;
    TextView tv_j_recentPost;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_page);

        //setup GUI
        tv_j_loggedInUserName = findViewById(R.id.tv_v_wp_name);
        tv_j_numOfPosts       = findViewById(R.id.tv_v_wp_numPosts);
        tv_j_recentPost       = findViewById(R.id.tv_v_wp_recentPost);

        dbHelper = new DatabaseHelper(this);

        setWelcomeMessage();
        setNumberOfPosts();
        setRecentPost();
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
        tv_j_recentPost.setText(dbHelper.getMostRecentPostGivenId(SessionData.getLoggedInUser().getId()).getPost());
    }
}