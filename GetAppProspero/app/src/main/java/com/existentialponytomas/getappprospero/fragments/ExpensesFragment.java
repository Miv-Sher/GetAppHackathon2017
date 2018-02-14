package com.existentialponytomas.getappprospero.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.BudgetMock;
import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.Category;
import com.existentialponytomas.getappprospero.repos.local.BudgetLocalStorage;
import com.existentialponytomas.getappprospero.repos.local.CategoryLocalStorage;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class ExpensesFragment extends Fragment {

    View rootView;
    private PieChart costsChart;


    public TextView category, leftText, rightText;
    public View leftBar, rightBar;


    String lightRed = "#FF9292";
    String darkRed = "#E26868";
    String green = "#9BD878";
    String grey = "#E8E8E8";

    public static ExpensesFragment newInstance() {
        ExpensesFragment fragment = new ExpensesFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        initCostPieChart();
       // initCategoryInfo();
        return rootView;
    }

    /**
     * Method for setting Pie Chart
     */
    public void initCostPieChart () {
        // add PieChart
        costsChart = (PieChart)rootView.findViewById(R.id.costs_chart);
        // set parameters
        costsChart.getDescription().setEnabled(false);


        costsChart.setCenterText(generateCenterSpannableText());
        costsChart.setDrawCenterText(true);

        costsChart.setDrawHoleEnabled(true);
        costsChart.setHoleColor(Color.WHITE);
        costsChart.setHoleRadius(50f);

        costsChart.setRotationEnabled(true);
        costsChart.setHighlightPerTapEnabled(true);

        costsChart.setTransparentCircleColor(Color.WHITE);
        costsChart.setTransparentCircleAlpha(110);
        costsChart.setTransparentCircleRadius(50f);

        // click on category listener
        costsChart.setOnChartValueSelectedListener (new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                if (e == null)
                    return;
                String categoryName = xValues[h.getDataIndex()];
                CategoryLocalStorage.getInstance().getByName(categoryName);
                BudgetLocalStorage.getInstance();
                initCategoryInfo(e.toString());

            }



            @Override
            public void onNothingSelected() { }
        });

        // legend
        Legend l = costsChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setEnabled(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        costsChart.setEntryLabelColor(Color.WHITE);
        costsChart.setEntryLabelTextSize(12f);
        // set data
        setDataForPieChart();

        costsChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    /**
     * Method for setting Category info
     */
    public void initCategoryInfo(String name) {
        category = (TextView) rootView.findViewById(R.id.text_category);
        leftText = (TextView) rootView.findViewById(R.id.text_left);
        rightText = (TextView) rootView.findViewById(R.id.text_right);
        leftBar = rootView.findViewById(R.id.view_left_bar);
        rightBar = rootView.findViewById(R.id.view_right_bar);
        // default

        category.setText(name);

//        bind(new BudgetMock(
//                "Eда",
//                21,
//                13
//        ));

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
        text.setText("$" + String.valueOf(sum) + " потрачено");
    }

    private void setLeftText(TextView text, double sum) {
        text.setText("$" + String.valueOf(sum) + " осталось");
    }

    private void setExceedsText(TextView text, double sum) {
        text.setText("Превышено на $" + String.valueOf(sum));
    }

    private void setBudgetText(TextView text, double sum) {
        text.setText("$ " + String.valueOf(sum) +" запланировано");
    }


    // stub data for Pie Chart
    private int[] yValues = {21, 25, 15, 41, 23, 20};

    //private int[] xValues = {11, 22, 33, 44, 55, 66};

    private String[] xValues = {"Еда", "Одежда", "Развлечения", "Транспорт", "Здоровье", "Прочее"};

    // colors for different sections in Pie Chart
    public static  final int[] CATEGORIES_COLORS = {
            Color.rgb(250, 128, 114), Color.rgb(255, 165, 0), Color.rgb(240, 230, 140),
            Color.rgb(238, 130, 238), Color.rgb(64, 224, 208), Color.rgb(100, 149, 237)
    };


    public void get() {

    }

    /**
     * Method for setting data into Pie Chart
     */
    public void setDataForPieChart() {

        ArrayList<PieEntry> pieValues = new ArrayList<PieEntry>();
        for (int i = 0; i < yValues.length; i++)
            pieValues.add(new PieEntry(yValues[i], xValues[i]));

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(pieValues, "");

        // adding colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : CATEGORIES_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        costsChart.setData(data);
        costsChart.highlightValues(null);
        costsChart.invalidate();
    }

    // stubs for pie center
    int totalSum = 100500;
    int spendSum = 100600;

    /**
     * Create string for pie center
     * @return string
     */
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("Ваш баланс: " + totalSum
                +"\nВы потратили: " + spendSum);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 18, 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 19, s.length(), 0);
        return s;
    }
}
