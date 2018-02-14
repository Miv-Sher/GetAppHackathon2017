package com.existentialponytomas.getappprospero.adapters;

import android.graphics.Color;
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


public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.MyViewHolder> {

    String notAssignedCategoryColor = "##3E26868";
    String normalCategoryColor = "F51B5";

    private List<Transaction> expensesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category, date, sum, source;

        public MyViewHolder(View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.category);
            date = (TextView) view.findViewById(R.id.date);
            sum = (TextView) view.findViewById(R.id.sum);
            source = (TextView) view.findViewById(R.id.source);
        }
    }



    public ExpensesAdapter(List<Transaction> expensesList) {
        //добавить проверку доход/расход по флажку
        this.expensesList = expensesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_expenses_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Transaction expenses = expensesList.get(position);
        holder.category.setText(expenses.getCategory().getName());

        String categoryColor;

        if (expenses.getCategory().getId() < 0) {
            categoryColor = notAssignedCategoryColor;
        }
        else {
            categoryColor = normalCategoryColor;
        }
        setTextColor(holder.category, categoryColor);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(expenses.getDate());
        String theDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR)+ ":" + c.get(Calendar.MINUTE);
        holder.date.setText(theDate);

        holder.sum.setText(String.valueOf(expenses.getAmount()));
        holder.source.setText(expenses.getSource());

    }


    private void setTextColor(TextView view, String color) {
        view.setTextColor(Color.parseColor(color));
    }
    @Override
    public int getItemCount() {
        return expensesList.size();
    }
}
