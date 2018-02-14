package com.existentialponytomas.getappprospero.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.ShopActivity;
import com.existentialponytomas.getappprospero.adapters.PointsAdapter;
import com.existentialponytomas.getappprospero.contracts.PointsContract;
import com.existentialponytomas.getappprospero.model.PointTransaction;
import com.existentialponytomas.getappprospero.presenters.PointsPresenter;
import com.existentialponytomas.getappprospero.adapters.PointsAdapter;
import com.existentialponytomas.getappprospero.repos.local.PointTransactionLocalStorage;

import java.util.ArrayList;
import java.util.List;


public class PointsFragment extends Fragment implements PointsContract.View {

    private List<PointTransaction> pointsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PointsAdapter mAdapter;
    private PointsContract.Presenter presenter;
    private TextView pointsView;

    public PointsFragment() {
        // Required empty public constructor
    }

    public static PointsFragment newInstance(String param1, String param2) {
        PointsFragment fragment = new PointsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_points, container, false);


        presenter = new PointsPresenter(this, PointTransactionLocalStorage.getInstance());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        pointsView = (TextView) rootView.findViewById(R.id.points);

        //данные о балах вытягиваются из юзера, но объект юзера вытягивается из авторизации
        pointsView.setText("На Вашем счету 7600 баллов");

        mAdapter = new PointsAdapter(pointsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // set the adapter
        recyclerView.setAdapter(mAdapter);

        presenter.loadPointsHistory();

        //prepareData();

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                getActivity().startActivityForResult(intent, 1);
            }
        });

        return rootView;
    }


    @Override
    public void showPointsHistory(List<PointTransaction> pointsHistory) {
        pointsList.clear();
        pointsList.addAll(pointsHistory);
        mAdapter.notifyDataSetChanged();
    }
}
