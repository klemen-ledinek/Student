package com.student.ledo.student2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DogodekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogodek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Opis dogodka");

        Bundle neki = getIntent().getExtras();
        String datum = neki.getString("Datum");
        String hours = neki.getString("Ura");
        String ime = neki.getString("Ime");

        TextView ure = (TextView) findViewById(R.id.hourText);
        ure.setText(hours);


        TextView date = (TextView) findViewById(R.id.dateText);
        date.setText(datum);

        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(ime);

    }

}
