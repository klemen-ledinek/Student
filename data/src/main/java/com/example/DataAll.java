package com.example;

import java.util.ArrayList;

/**
 * Created by Ledo on 25. 05. 2016.
 */
public class DataAll {
    private ArrayList<Dogodek> dogodki;


    public DataAll() {
        dogodki = new ArrayList<Dogodek>();
    }



    @Override
    public String toString() {
        return "DataAll{" +
                "dogodki=" + dogodki +
                '}';
    }


    public void add(Dogodek d){dogodki.add(d);}

    public Dogodek getDogodki(int position) {
        return dogodki.get(position);
    }

    public ArrayList<Dogodek> getAll(){
        ArrayList<Dogodek> test = dogodki;
        return test;
    }

    public void deleteDogodeg(int position){
        dogodki.remove(position);
    }

    public void setDogodki(ArrayList<Dogodek> dogodki) {
        this.dogodki = dogodki;
    }

    public int size(){
        return dogodki.size();
    }

    public static DataAll getScenarijData(){
        DataAll allData = new DataAll();
        allData.add(new Dogodek("Menjava posteljnine","24/5/2016","13:15",2));
        allData.add(new Dogodek("Oddaja PJ","10/6/2016","23:55",1));
        allData.add(new Dogodek("Zagovor Algoritmi","28/5/2016","14:00",1));
        allData.add(new Dogodek("Oddaja PJ","10/6/2016","23:55",1));
        allData.add(new Dogodek("Zagovor algoritmov","12/6/2016","23:55",1));
        allData.add(new Dogodek("Predstavitev aplikacije","15/6/2016","23:55",1));
        allData.add(new Dogodek("Preselitev","15/6/2016","23:55",2));
        allData.add(new Dogodek("Učenje za kolokvij","17/6/2016","23:55",1));
        allData.add(new Dogodek("Seminarska naloga","18/6/2016","23:55",1));
        allData.add(new Dogodek("Menjava posteljnine","20/6/2016","23:55",2));
        allData.add(new Dogodek("Naloga alogritmi","1/7/2016","23:55",1));
        allData.add(new Dogodek("Oddaja projekta","5/7/2016","23:55",1));

        return allData;
    }

}
