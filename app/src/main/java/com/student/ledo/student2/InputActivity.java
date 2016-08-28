package com.student.ledo.student2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Dogodek;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {
    private DataAll allData;
    private ApplicationMy app;

    public int selectId = 3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("Dodaj dogodek");


        app = (ApplicationMy) getApplication();
        allData = app.getAll();


        final String time = getIntent().getStringExtra("id");
        final TextView date = (TextView) findViewById(R.id.textBoxDate);
        final TextView name = (TextView) findViewById(R.id.textBoxName);
        final TextView hour = (TextView) findViewById(R.id.editTextHour);
        final DateFormat datum = new SimpleDateFormat("dd/M/yyyy");
        final DateFormat ura = new SimpleDateFormat("HH:mm");
        final Calendar cal = Calendar.getInstance();
        date.setText(datum.format(cal.getTime()));
        hour.setText(ura.format(cal.getTime()));


        Button dodajDan = (Button) findViewById(R.id.addDay);
        Button dodajMesec = (Button) findViewById(R.id.addMonth);
        Button odštejDan = (Button) findViewById(R.id.substractDay);
        Button odštejMesec = (Button) findViewById(R.id.substractMonth);
        Button dodajUro = (Button) findViewById(R.id.addHour);
        Button dodajMinuto = (Button) findViewById(R.id.addMinute);
        Button odštejUro = (Button) findViewById(R.id.substractHour);
        Button odštejMinuto = (Button) findViewById(R.id.substractMinute);

        assert dodajDan != null;
        dodajDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leto = cal.get(Calendar.YEAR);
                int mesec = cal.get(Calendar.MONTH);
                int dan = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(leto, mesec, dan + 1);
                date.setText(datum.format(cal.getTime()));
            }
        });
        assert dodajMesec != null;
        dodajMesec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leto = cal.get(Calendar.YEAR);
                int mesec = cal.get(Calendar.MONTH);
                int dan = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(leto, mesec + 1, dan);
                date.setText(datum.format(cal.getTime()));
            }
        });

        assert odštejDan != null;
        odštejDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leto = cal.get(Calendar.YEAR);
                int mesec = cal.get(Calendar.MONTH);
                int dan = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(leto, mesec, dan - 1);
                date.setText(datum.format(cal.getTime()));
            }
        });

        assert odštejMesec != null;
        odštejMesec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leto = cal.get(Calendar.YEAR);
                int mesec = cal.get(Calendar.MONTH);
                int dan = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(leto, mesec - 1, dan);
                date.setText(datum.format(cal.getTime()));
            }
        });


        assert dodajMinuto != null;
        dodajMinuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ura1 = cal.get(Calendar.HOUR_OF_DAY);
                int minuta = cal.get(Calendar.MINUTE);
                cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),ura1,minuta+1);
                hour.setText(ura.format(cal.getTime()));
            }
        });
        assert dodajUro != null;
        dodajUro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ura1 = cal.get(Calendar.HOUR_OF_DAY);
                int minuta = cal.get(Calendar.MINUTE);
                cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),ura1+1,minuta);
                hour.setText(ura.format(cal.getTime()));
            }
        });

        assert odštejMinuto != null;
        odštejMinuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ura1 = cal.get(Calendar.HOUR_OF_DAY);
                int minuta = cal.get(Calendar.MINUTE);
                cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),ura1,minuta-1);
                hour.setText(ura.format(cal.getTime()));
            }
        });

        assert odštejUro != null;
        odštejUro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ura1 = cal.get(Calendar.HOUR_OF_DAY);
                int minuta = cal.get(Calendar.MINUTE);
                cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),ura1-1,minuta);
                hour.setText(ura.format(cal.getTime()));
            }
        });


        RadioGroup group1 = (RadioGroup) findViewById(R.id.groupRadio);
        assert group1 != null;
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group,int checkedId){
                switch (checkedId){
                    case R.id.FaksType:
                        selectId =1;
                        break;
                    case R.id.DormType:
                        selectId = 2;
                        break;
                }
            }
        });


        //   allData = app.getAll();
        // allData = app.getAll().getScenarijData();
        Button addd = (Button) findViewById(R.id.buttonAdd);
        if (addd != null) {
            addd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dogodek dog = new Dogodek();
                    assert date != null;
                    dog.setDatum(date.getText().toString());
                    assert name != null;
                    dog.setIme(name.getText().toString());
                    assert hour != null;
                    dog.setUra(hour.getText().toString());



                    dog.setTip(selectId);




                    allData.add(dog);


                    app.save();
                    Intent i = new Intent(InputActivity.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Input Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.student.ledo.student2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Input Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.student.ledo.student2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
