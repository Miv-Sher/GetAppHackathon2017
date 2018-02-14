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
import com.existentialponytomas.getappprospero.adapters.RatingAdapter;
import com.existentialponytomas.getappprospero.model.User;

import java.util.ArrayList;
import java.util.List;


public class RatingFragment extends Fragment {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RatingAdapter mAdapter;

    public RatingFragment() {
        // Required empty public constructor
    }

    public static RatingFragment newInstance() {
        RatingFragment fragment = new RatingFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rating, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new RatingAdapter(userList);
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
        userList.clear();
        User user = new User("Анджелина", R.drawable.av_1, 56000);
        userList.add(user);

        user = new User("Томас", R.drawable.av_2, 44000);
        userList.add(user);

        user = new User("Моника", R.drawable.av_3, 32874);
        userList.add(user);

        mAdapter.notifyDataSetChanged();
    }

}
