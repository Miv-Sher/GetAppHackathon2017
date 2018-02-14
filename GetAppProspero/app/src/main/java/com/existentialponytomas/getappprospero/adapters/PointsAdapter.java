package com.existentialponytomas.getappprospero.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.PointTransaction;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nessa on 01.04.2017.
 */

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private List<PointTransaction> pointsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView comment, date, sum;

        public MyViewHolder(View view) {
            super(view);
            comment = (TextView) view.findViewById(R.id.comment);
            date = (TextView) view.findViewById(R.id.date);
            sum = (TextView) view.findViewById(R.id.sum);
        }



    }



    public PointsAdapter(List<PointTransaction> pointsList) {
        //добавить проверку доход/расход по флажку
        this.pointsList = pointsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.points_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PointTransaction points = pointsList.get(position);

        holder.comment.setText(points.getReason());


        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(points.getDate());
        String theDate = c.get(Calendar.DAY_OF_MONTH) + "/" +
                c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " +
                c.get(Calendar.HOUR)+ ":" + c.get(Calendar.MINUTE);
        holder.date.setText(theDate);
        holder.sum.setText(String.valueOf(points.getAmount()));
    }


    @Override
    public int getItemCount() {
        return pointsList.size();
    }
}

