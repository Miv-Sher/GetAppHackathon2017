package com.existentialponytomas.getappprospero.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.Certificate;

import java.util.List;

/**
 * Created by Nessa on 02.04.2017.
 */

public class BuyCertificateAdapter extends RecyclerView.Adapter<BuyCertificateAdapter.MyViewHolder> {

    private List<Certificate> certificatesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sum, points;
        // public Button use;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            sum = (TextView) view.findViewById(R.id.sum);
            points = (TextView) view.findViewById(R.id.points);
            // use = (Button) view.findViewById(R.id.button_use);
            icon = (ImageView) view.findViewById(R.id.logo);

        }



    }



    public BuyCertificateAdapter(List<Certificate> cetrtificatesList) {
        //добавить проверку доход/расход по флажку
        this.certificatesList = cetrtificatesList;
    }

    @Override
    public BuyCertificateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buy_list_row, parent, false);

        return new BuyCertificateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyCertificateAdapter.MyViewHolder holder, int position) {
        Certificate certificate = certificatesList.get(position);

        holder.icon.setImageResource(certificate.getIcon());
        holder.sum.setText(certificate.getSum_amount());
        holder.points.setText(certificate.getPoints_amount());
        //holder.points.setText("200 баллов");


    }



    @Override
    public int getItemCount() {
        return certificatesList.size();
    }
}
