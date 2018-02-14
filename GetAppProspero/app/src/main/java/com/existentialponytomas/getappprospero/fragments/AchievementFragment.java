package com.existentialponytomas.getappprospero.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.AchievementAdapter;
import com.existentialponytomas.getappprospero.model.Achievement;
import com.existentialponytomas.getappprospero.model.AchievementType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AchievementFragment extends Fragment {

    private List<Achievement> achievementList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AchievementAdapter mAdapter;


    public AchievementFragment() {
        // Required empty public constructor
    }

    public static AchievementFragment newInstance() {
        AchievementFragment fragment = new AchievementFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_achievement, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new AchievementAdapter(achievementList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(mAdapter);

        prepareData();

        return rootView;
    }


    //метод заглушка, генерит дату
    private void prepareData()
    {
        String name = "Дядюшка Тыква";
        String description = "Ваши сбережения достигли уровня минимального взноса на ипотеку.";


        Achievement achievement = new Achievement(new AchievementType(description, name), Calendar.getInstance().getTimeInMillis());
        achievementList.add(achievement);

        name = "Гений планирования";
        description = "Вы не выбились за рамки запланированного на месяц бюджета.";
        achievement = new Achievement(new AchievementType(description, name), Calendar.getInstance().getTimeInMillis());
        achievementList.add(achievement);

        name = "Мастер самоконтроля";
        description = "Вы не посещали опасные для вашего кошелька места уже месяц.";
        achievement = new Achievement(new AchievementType(description, name), Calendar.getInstance().getTimeInMillis());
        achievementList.add(achievement);
        mAdapter.notifyDataSetChanged();
    }
}
