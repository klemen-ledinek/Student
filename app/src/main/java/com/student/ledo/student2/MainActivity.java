package com.student.ledo.student2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.DataAll;
import com.example.Dogodek;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataAll dogodki;

    private ApplicationMy app;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Moji dogodki");




        app = (ApplicationMy) getApplication();


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewData);

        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        dogodki = app.getAll();
        mAdapter = new AdapterDogodek(dogodki,MainActivity.this);

        mRecyclerView.setAdapter(mAdapter);


        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.refresh);

        assert refresh != null;
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new getInfo().execute();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, InputActivity.class));
                MainActivity.this.finish();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(MainActivity.this,Settings.class));
        }
        if(id==R.id.google_logout){
           Intent i = new Intent(MainActivity.this,SignIn.class);
            i.putExtra(SignIn.LOGOUT,true);
            startActivity(i);
            MainActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class getInfo extends AsyncTask<Void,Void,Void> {
        String url = "https://feri.um.si/odeska/";
        int test1 = 0;



        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Get the html document title
                Elements oglasi = document.select("article");
                test1 = oglasi.size();





                dogodki = app.getAll();


                int i = 0;
                while(i<dogodki.size()){
                    Dogodek dog = dogodki.getDogodki(i);
                    if(dog.getUra() == "" ){
                        dogodki.deleteDogodeg(i);
                        i--;
                    }
                    i++;
                }

                app.save();


                dogodki = app.getAll();
                for (Element oglas : oglasi) {
                    Elements ime = oglas.select("p");
                    Elements novice = oglas.select("div");

                    Dogodek dog = new Dogodek();
                    dog.setDatum(ime.first().text());
                    dog.setIme(novice.text());
                    dog.setUra("");
                    dog.setTip(3);

                    dogodki.add(dog);

                }


                app.save();


            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            mAdapter = new AdapterDogodek(dogodki,MainActivity.this);

            mRecyclerView.setAdapter(mAdapter);
        }
    }

    }

