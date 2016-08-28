package com.student.ledo.student2;

import android.app.Application;
import android.os.Environment;

import com.example.DataAll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by klemen on 26. 05. 2016.
 */
public class ApplicationMy extends Application {
    private static final String DATA_MAP = "Dogodki";
    private static final String FILE_NAME = "dogodki.json";

    private DataAll all;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!load()) {
            all = DataAll.getScenarijData();
        }
    }

    public DataAll getAll() {
        return all;
    }

    private void setAll(DataAll all) {
        this.all = all;
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean save() {
        return save(all, FILE_NAME);
    }

    public boolean load() {
        DataAll tmp = load(FILE_NAME);
        if (tmp != null) all = tmp;
        else return false;
        return true;
    }


    private boolean save(DataAll a, String filename) {
        if (isExternalStorageWritable()) {
            File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                    + filename);
            try {
                long start = System.currentTimeMillis();
                System.out.println("Save " + file.getAbsolutePath() + " " + file.getName());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                PrintWriter pw = new PrintWriter(file);
                String sss = gson.toJson(a);
                System.out.println("Save time gson:" + (double) (System.currentTimeMillis() - start) / 1000);
                pw.println(sss);
                pw.close();
                System.out.println("Save time s:" + (double) (System.currentTimeMillis() - start) / 1000);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error save! (FileNotFoundException)");
            } catch (IOException e) {
                System.out.println("Error save! (IOException)");
            }
        } else {
            System.out.println(this.getClass().getCanonicalName() + " NOT Writable");
        }
        return false;
    }

    private DataAll load(String name) {
        if (isExternalStorageReadable()) {
            try {
                File file = new File(this.getExternalFilesDir(DATA_MAP), "" + name);
                System.out.println("Load " + file.getAbsolutePath() + " " + file.getName());
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuffer sb = new StringBuffer();
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    sb.append(strLine).append('\n');
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                DataAll a = gson.fromJson(sb.toString(), DataAll.class);
                if (a == null) {
                    System.out.println("Error: fromJson Format error");
                } else {
                    System.out.println(a.toString());
                }
                return a;
            } catch (IOException e) {
                System.out.println("Error load " + e.toString());
            }
        }
        System.out.println("ExternalStorageAvailable is not avaliable");
        return null;
    }
}
