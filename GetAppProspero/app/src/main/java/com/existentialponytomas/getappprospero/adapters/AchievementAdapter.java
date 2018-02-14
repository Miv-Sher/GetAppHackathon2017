package com.existentialponytomas.getappprospero.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.Achievement;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nessa on 01.04.2017.
 */


public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.MyViewHolder> {

    private List<Achievement> achievementList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, description;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            description = (TextView) view.findViewById(R.id.description);
        }



    }



    public AchievementAdapter(List<Achievement> achievementList) {
        //добавить проверку доход/расход по флажку
        this.achievementList = achievementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievements_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Achievement achievement = achievementList.get(position);



        holder.name.setText(achievement.getType().getName());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(achievement.getDate());
        String theDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR)+ ":" + c.get(Calendar.MINUTE);
        holder.date.setText(theDate);
        holder.description.setText(achievement.getType().getDescription());
    }

    @Override
    public int getItemCount() {
        return achievementList.size();
    }
}
