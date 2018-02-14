package com.existentialponytomas.getappprospero.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.BudgetMock;
import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.Budget;

import java.util.ArrayList;

/**
 * Created by Anna on 4/1/2017.
 */


public class BudgetAdapter extends RecyclerView.Adapter{

    ArrayList<Budget> budgets;
    ArrayList<Double> transactionsSums;

    String lightRed = "#FF9292";
    String darkRed = "#E26868";
    String green = "#9BD878";
    String grey = "#E8E8E8";


    public  BudgetAdapter(ArrayList<Budget> budgets, ArrayList<Double> transactionsSums) {
        this.budgets = budgets;
        this.transactionsSums = transactionsSums;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_item, parent, false);

        return new BudgetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Budget budget = budgets.get(position);
        ((BudgetViewHolder)holder).bind(budget, transactionsSums.get(position));
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    public class BudgetViewHolder extends RecyclerView.ViewHolder {
        public TextView category, leftText, rightText;
        public View leftBar, rightBar;

        public BudgetViewHolder(View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.text_category);
            leftText = (TextView) view.findViewById(R.id.text_left);
            rightText = (TextView) view.findViewById(R.id.text_right);
            leftBar = view.findViewById(R.id.view_left_bar);
            rightBar = view.findViewById(R.id.view_right_bar);
        }

        public void bind(Budget budget, double spent) {
            category.setText(budget.getCategory().getName());
            float leftBarWeight;
            float rightBarWeight;
            String leftColor;
            String rightColor;

            if (budget.getSum() >= spent) {
                //within budget
                leftBarWeight =  1 - (float)spent/(float)budget.getSum();
                rightBarWeight = 1 - leftBarWeight;
                leftColor = green;
                rightColor = grey;
                setSpentText(leftText, spent);
                setLeftText(rightText, budget.getSum() - spent);
            }
            else {
                //exceed budget
                leftBarWeight =  1 - (float)budget.getSum()/(float)spent;
                rightBarWeight = 1 - leftBarWeight;
                leftColor = lightRed;
                rightColor = darkRed;
                setBudgetText(leftText, budget.getSum());
                setExceedsText(rightText, spent - budget.getSum());
            }

            setWeight(leftBar, leftBarWeight);
            setWeight(rightBar, rightBarWeight);
            setColor(leftBar, leftColor);
            setColor(rightBar, rightColor);

        }

        private void setColor(View view, String color) {
            view.setBackgroundColor(Color.parseColor(color));
        }



        private void setWeight(View view, float weight) {
            final float scale = view.getContext().getResources().getDisplayMetrics().density;
            int height = (int) (10 * scale + 0.5f);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    height,
                    weight
            );
            view.setLayoutParams(param);
        }

        private void setSpentText(TextView text, double sum) {
            text.setText(String.valueOf(sum) + "р. потрачено");
        }

        private void setLeftText(TextView text, double sum) {
            text.setText(String.valueOf(sum) + "р. осталось");
        }

        private void setExceedsText(TextView text, double sum) {
            text.setText("Превышено на " + String.valueOf(sum) + "р.");
        }

        private void setBudgetText(TextView text, double sum) {
            text.setText(String.valueOf(sum) +"р. запланировано");
        }

    }
}