package com.existentialponytomas.getappprospero.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.BoughtCertificateAdapter;
import com.existentialponytomas.getappprospero.adapters.IncomeAdapter;
import com.existentialponytomas.getappprospero.adapters.ItemClickSupport;
import com.existentialponytomas.getappprospero.contracts.HistoryIncomeContract;
import com.existentialponytomas.getappprospero.model.Certificate;
import com.existentialponytomas.getappprospero.model.Transaction;
import com.existentialponytomas.getappprospero.presenters.HistoryIncomePresenter;
import com.existentialponytomas.getappprospero.repos.local.TransactionLocalStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopBoughtFragment extends Fragment {

    private List<Certificate> certificatesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BoughtCertificateAdapter mAdapter;

    Button usebtn;




    public ShopBoughtFragment() {
        // Required empty public constructor
    }


    public static ShopBoughtFragment newInstance() {
        ShopBoughtFragment fragment = new ShopBoughtFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_shop_bought, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new BoughtCertificateAdapter(certificatesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // set the adapter
        recyclerView.setAdapter(mAdapter);





        //Click Listener!
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {

                        Uri address = Uri.parse("https://vsesrazu-raiffeisen.ru/#/");
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);


            }
        });



        prepareData();

        return rootView;
    }

    public void prepareData()
    {
        Certificate gift = new Certificate(R.drawable.airlines, "1000 миль", "200 баллов", "https://vsesrazu-raiffeisen.ru/#/");
        certificatesList.add(gift);

        gift = new Certificate(R.drawable.yandextaxi, "300 р.", "400 баллов", "https://vsesrazu-raiffeisen.ru/#/");
        certificatesList.add(gift);

        gift = new Certificate(R.drawable.ozon, "300 р.", "600 баллов", "https://vsesrazu-raiffeisen.ru/#/");
        certificatesList.add(gift);

        mAdapter.notifyDataSetChanged();
    }

}