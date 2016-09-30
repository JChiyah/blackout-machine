package com.blackout.blackoutmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.LikeView;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logOut();

        LikeView likeView = (LikeView) findViewById(R.id.likeView);
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);

        likeView.setObjectIdAndType(
                "https://www.facebook.com/BlackoutSpicyCinnamonLiqueur/",
                LikeView.ObjectType.OPEN_GRAPH);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook like
        callbackManager.onActivityResult(requestCode, responseCode, intent);
        ((ImageView)findViewById(R.id.playblackout)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.putExtra("game_id", getIntent().getIntExtra("game_id", 0));
                startActivity(intent);
            }

        });
    }

}