package com.example.android.homework18;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final Spinner spinner = findViewById(R.id.spinner_language);
        final Spinner spinnerMargins = findViewById(R.id.spinner_margins);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedItem().equals("Русский")) {
                    locale = new Locale("ru");
                } else locale = new Locale("eng");
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                switch (spinnerMargins.getSelectedItem().toString()) {
                    case "Маленькие отступы":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN1);
                        break;
                    case "Средние отступы":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN3);
                        break;
                    case "Большие отступы":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN10);
                        break;
                }
            }
        });
    }
}

class Utils {
    private static int sTheme;

    public final static int THEME_MARGIN1 = 0;
    public final static int THEME_MARGIN3 = 1;
    public final static int THEME_MARGIN10 = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_MARGIN1:
                activity.setTheme(R.style.Margin1);
                break;
            case THEME_MARGIN3:
                activity.setTheme(R.style.Margin3);
                break;
            case THEME_MARGIN10:
                activity.setTheme(R.style.Margin10);
                break;
        }
    }
}