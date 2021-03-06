package org.pursuit.Group_portfolio_HW_TEAM_THE_TRIPLE_THREAT;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class JohnActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private TextView textView;
    private ImageView imgView;
    private ScrollView scrollView;
    private Button troll;
    private MediaPlayer astley;
    private MediaPlayer queen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_john);

        queen = MediaPlayer.create(this, R.raw.dancingqueen);
        queen.start();

        scrollView = findViewById(R.id.john_scrollview);
        imgView = findViewById(R.id.john_img);
//        troll = findViewById(R.id.john_cool_btn);
//        troll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                neverGonnaGiveYouUp();
//            }
//        });


        fab = findViewById(R.id.john_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = getString(R.string.john_email);
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(mail));
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(JohnActivity.this, R.string.fab_error_message, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        queen.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        queen.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queen.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.john_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.git_one:
                gitJuan();
                break;
            case R.id.git_two:
                gitDos();
                break;
            case R.id.git_three:
                gitTres();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void gitJuan() {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_one)));
        startActivity(webIntent);
    }

    public void gitDos() {
        Intent webIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_two)));
        startActivity(webIntent2);
    }

    public void gitTres() {
        Intent webIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_three)));
        startActivity(webIntent3);
    }

    // used in onClick for @id john_img
    public void neverGonnaGiveYouUp(View view) {
        Intent astley = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lXMskKTw3Bc"));
        startActivity(astley);
    }
}


