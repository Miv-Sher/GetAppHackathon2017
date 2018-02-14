package com.existentialponytomas.getappprospero.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.IncomeAdapter;
import com.existentialponytomas.getappprospero.adapters.ItemClickSupport;
import com.existentialponytomas.getappprospero.contracts.HistoryExpensesContract;
import com.existentialponytomas.getappprospero.contracts.HistoryIncomeContract;
import com.existentialponytomas.getappprospero.model.Transaction;
import com.existentialponytomas.getappprospero.model.User;
import com.existentialponytomas.getappprospero.presenters.HistoryIncomePresenter;
import com.existentialponytomas.getappprospero.repos.local.TransactionLocalStorage;

import java.util.ArrayList;
import java.util.List;


public class HistoryIncomeFragment extends Fragment implements HistoryIncomeContract.View {

    private List<Transaction> incomeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IncomeAdapter mAdapter;
    AlertDialog.Builder ad;
    LinearLayout view;
    EditText edit;

    private HistoryIncomeContract.Presenter presenter;

    public HistoryIncomeFragment() {
        // Required empty public constructor
    }


    public static HistoryIncomeFragment newInstance() {
        HistoryIncomeFragment fragment = new HistoryIncomeFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_history_income, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new IncomeAdapter(incomeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(mAdapter);


        presenter = new HistoryIncomePresenter(this, TransactionLocalStorage.getInstance());

        //Click Listener!
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {

                ad = new AlertDialog.Builder(getContext());
                view = (LinearLayout) getActivity().getLayoutInflater()
                        .inflate(R.layout.dialog_change, null);
                edit = (EditText) view.findViewById(R.id.comment);
                ad.setView(view);

                ad.setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        incomeList.get(position).setComment(edit.getText().toString());
                        mAdapter.notifyDataSetChanged();

                    }
                });
                ad.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {


                    }
                });
                ad.setCancelable(true);

                ad.show();
            }
        });


        presenter.loadIncomeHistory();

        return rootView;
    }


    @Override
    public void showIncomeHistory(List<Transaction> incomes) {
        incomeList.clear();
        incomeList.addAll(incomes);
        mAdapter.notifyDataSetChanged();
    }
}
