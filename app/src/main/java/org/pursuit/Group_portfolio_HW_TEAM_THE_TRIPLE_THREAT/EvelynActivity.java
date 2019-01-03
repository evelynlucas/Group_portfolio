package org.pursuit.Group_portfolio_HW_TEAM_THE_TRIPLE_THREAT;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EvelynActivity extends AppCompatActivity {
    MediaPlayer song;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evelyn);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;
                long repeatInterval = AlarmManager.INTERVAL_DAY;
                long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
                if (isChecked) {
                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating
                                (AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
                    }
                    toastMessage = "Notification On!";
                    } else {
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    notificationManager.cancelAll();
                    toastMessage = "Notification Off!";
                }
                Toast.makeText(EvelynActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        createNotificationChannel();

        song = MediaPlayer.create(this, R.raw.ramones);
        song.start();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getString(R.string.evelyn_email);
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(email));
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(EvelynActivity.this, R.string.fab_error_message, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.evelyn_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.madlibs_app:
                String url1 = getString(R.string.evelyn_url_one);
                openWebPage(url1);
                return true;
            case R.id.text_based_app:
                String url2 = getString(R.string.evelyn_url_two);
                openWebPage(url2);
                return true;
            case R.id.java_bank_app:
                String url3 = getString(R.string.evelyn_url_three);
                openWebPage(url3);
                return true;
                default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.noweb_toast_text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Check Evelyn Page",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        song.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        song.start();
    }


}
