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
import com.existentialponytomas.getappprospero.model.Transaction;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nessa on 02.04.2017.
 */

public class BoughtCertificateAdapter extends RecyclerView.Adapter<BoughtCertificateAdapter.MyViewHolder> {

    private List<Certificate> certificatesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sum, points;
       // public Button use;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            sum = (TextView) view.findViewById(R.id.sum);
            points = (TextView) view.findViewById(R.id.date);
           // use = (Button) view.findViewById(R.id.button_use);
            icon = (ImageView) view.findViewById(R.id.logo);

        }



    }



    public BoughtCertificateAdapter(List<Certificate> cetrtificatesList) {
        //добавить проверку доход/расход по флажку
        this.certificatesList = cetrtificatesList;
    }

    @Override
    public BoughtCertificateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bought_list_row, parent, false);

        return new BoughtCertificateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BoughtCertificateAdapter.MyViewHolder holder, int position) {
        Certificate certificate = certificatesList.get(position);

        holder.icon.setImageResource(certificate.getIcon());
        holder.sum.setText(certificate.getSum_amount());


    }



    @Override
    public int getItemCount() {
        return certificatesList.size();
    }
}
