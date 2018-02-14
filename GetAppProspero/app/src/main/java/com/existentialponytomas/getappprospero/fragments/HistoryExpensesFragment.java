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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.ExpensesAdapter;
import com.existentialponytomas.getappprospero.adapters.IncomeAdapter;
import com.existentialponytomas.getappprospero.adapters.ItemClickSupport;
import com.existentialponytomas.getappprospero.contracts.HistoryExpensesContract;
import com.existentialponytomas.getappprospero.model.Category;
import com.existentialponytomas.getappprospero.model.Transaction;
import com.existentialponytomas.getappprospero.model.User;
import com.existentialponytomas.getappprospero.presenters.HistoryExpensesPresenter;
import com.existentialponytomas.getappprospero.repos.local.TransactionLocalStorage;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.edit;

public class HistoryExpensesFragment extends Fragment implements HistoryExpensesContract.View {

    private List<Transaction> expensesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExpensesAdapter mAdapter;
    private HistoryExpensesContract.Presenter presenter;
    AlertDialog.Builder ad;
    LinearLayout view;
    Spinner categorySpinner;
    String[] data = {"Не назначено", "Еда", "Одежда", "Развлечения", "Транспорт", "Здоровье", "Прочее", "Зарплата"};

    public HistoryExpensesFragment() {
        // Required empty public constructor
    }


    public static HistoryExpensesFragment newInstance() {
        HistoryExpensesFragment fragment = new HistoryExpensesFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history_expenses, container, false);
        // rootView.setTag(TAG);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mAdapter = new ExpensesAdapter(expensesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // set the adapter
        presenter = new HistoryExpensesPresenter(this, TransactionLocalStorage.getInstance());
        recyclerView.setAdapter(mAdapter);



        //Click Listener!
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {

                ad = new AlertDialog.Builder(getContext());
                view = (LinearLayout) getActivity().getLayoutInflater()
                        .inflate(R.layout.dialog_change_category, null);
                categorySpinner = (Spinner) view.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, data);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                categorySpinner.setAdapter(adapter);
                categorySpinner.setSelection(expensesList.get(position).getCategory().getId() +1);

                ad.setView(view);

                ad.setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        //expensesList.get(position).setComment(edit.getText().toString());
                        expensesList.get(position).setCategory(new Category(categorySpinner.getSelectedItemPosition() -1, categorySpinner.getSelectedItem().toString(), Category.Type.EXPENSE));
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


        presenter.loadExpensesHistory();


        return rootView;
    }


    @Override
    public void showExpensesHistory(List<Transaction> expenses) {
        expensesList.clear();
        expensesList.addAll(expenses);
        mAdapter.notifyDataSetChanged();
    }
}
