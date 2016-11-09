package rwt.kevin.memories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ImageView profileImageView = (ImageView) findViewById(R.id.profile_imageview);
        toolbar = (Toolbar) findViewById(R.id.my_profile_toolbar);
        if (toolbar != null) {
            toolbar.setTitle("My Profile");
            setSupportActionBar(toolbar);
        }
        TextView usernameTextView = (TextView) findViewById(R.id.usernameText);
        //get username here and settext
        Button backButton = (Button) findViewById(R.id.backbutton);
        if(backButton != null){
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        if(logoutButton != null) {
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}