package com.existentialponytomas.getappprospero.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.Transaction;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nessa on 01.04.2017.
 */

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {

     private List<Transaction> incomeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView comment, date, sum;

        public MyViewHolder(View view) {
            super(view);
            comment = (TextView) view.findViewById(R.id.comment);
            date = (TextView) view.findViewById(R.id.date);
            sum = (TextView) view.findViewById(R.id.sum);
        }



    }



    public IncomeAdapter(List<Transaction> incomeList) {
        //добавить проверку доход/расход по флажку
        this.incomeList = incomeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_income_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Transaction income = incomeList.get(position);
        holder.comment.setText(income.getComment());

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(income.getDate());
        String theDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR)+ ":" + c.get(Calendar.MINUTE);
        holder.date.setText(theDate);
        holder.sum.setText(String.valueOf(income.getAmount()));
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }
}
