package com.student.ledo.student2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Dogodek;
/**
 * Created by Ledo on 1. 06. 2016.
 */
public class AdapterDogodek extends RecyclerView.Adapter<AdapterDogodek.ViewHolder> {
    private final Activity ac;
    private DataAll mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtHeader;
        public TextView txtFooter;
        public LinearLayout background;
        public Button btn;

        public ViewHolder(View v){
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            btn  = (Button) v.findViewById(R.id.button);
            background = (LinearLayout) v.findViewById(R.id.backGround);
        }
    }
    public AdapterDogodek(DataAll myDataset, Activity ac) {
        this.ac = ac;
        mDataset = myDataset;
    }



    @Override
    public AdapterDogodek.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Dogodek trenutni = mDataset.getDogodki(position);
        holder.txtHeader.setText(trenutni.getIme());


        holder.txtFooter.setText(trenutni.getDatum());
        if(trenutni.getTip() == 1){
            holder.background.setBackgroundColor(Color.RED);
        }
        else if(trenutni.getTip() == 2){
            holder.background.setBackgroundColor(Color.BLUE);
        }
        else if(trenutni.getTip() == 3){
            holder.background.setBackgroundColor(Color.GREEN);
        }
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novo = new Intent(ac, DogodekActivity.class);
                novo.putExtra("Ime",trenutni.getIme());
                novo.putExtra("Datum",trenutni.getDatum());
                novo.putExtra("Ura",trenutni.getUra());
                ac.startActivity(novo);

            }
        });



        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.getAll().remove(position);
                //activity.finish();
                //activity.startActivity(activity.getIntent());

                notifyDataSetChanged();
            }
        });

    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
