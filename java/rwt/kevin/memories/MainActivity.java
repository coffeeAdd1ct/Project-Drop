package rwt.kevin.memories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends FragmentActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        

        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
        
        /*toolbar = (Toolbar) findViewById(R.id.login_toolbar);
                if (toolbar != null) {
                    toolbar.setTitle("Login");
                }*/
        /*Button aboutButton = (Button) findViewById(R.id.aboutButton);
        if (aboutButton != null) {
            aboutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(i);
                }
            });
        }
        Button noLoginButton = (Button) findViewById(R.id.noLoginButton);
        if (noLoginButton != null) {
            noLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(i);
                }
            });
        }
        Button signinButton = (Button) findViewById(R.id.signinButton);
        if(signinButton != null){
            signinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            });
        }
        Button signupButton = (Button) findViewById(R.id.signupButton);
        if(signupButton != null){
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(i);
                }
            });
        }*/
        
        
        
        
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
    public boolean loggedIn(){
        //design api calls here to check if username is logged in
        //save username and password in shared preferences
            //http://stackoverflow.com/questions/22526950/how-to-check-if-current-user-is-logged-in-android
        return true;
    }
    public void loading(){
    }
}