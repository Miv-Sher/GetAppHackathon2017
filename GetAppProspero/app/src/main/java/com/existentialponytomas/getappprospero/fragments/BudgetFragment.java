package com.existentialponytomas.getappprospero.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.existentialponytomas.getappprospero.adapters.BudgetAdapter;
import com.existentialponytomas.getappprospero.BudgetMock;
import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.adapters.ItemClickSupport;
import com.existentialponytomas.getappprospero.contracts.BudgetContract;
import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.Transaction;
import com.existentialponytomas.getappprospero.presenters.BudgetPresenter;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;
import com.existentialponytomas.getappprospero.repos.local.BudgetLocalStorage;
import com.existentialponytomas.getappprospero.repos.local.TransactionLocalStorage;

import java.util.ArrayList;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class BudgetFragment extends Fragment implements BudgetContract.View{

    private View rootView;

    private RecyclerView recycler;
    private BudgetAdapter adapter;
    private BudgetContract.Presenter presenter;
    ArrayList<Budget> budgets;
    ArrayList<Double> transactionSums;
    AlertDialog.Builder ad;

    public BudgetFragment() {
        // Required empty public constructor
    }

    public static BudgetFragment newInstance() {
        BudgetFragment fragment = new BudgetFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_budget, container, false);


        recycler = (RecyclerView) rootView.findViewById(R.id.budget_recycler);
        budgets = new ArrayList<>();
        transactionSums = new ArrayList<>();
        adapter = new BudgetAdapter(budgets,transactionSums);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setAdapter(adapter);

        presenter = new BudgetPresenter(this, BudgetLocalStorage.getInstance(),
                TransactionLocalStorage.getInstance());

        presenter.loadBudgets();


        //Click Listener!
        ItemClickSupport.addTo(recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {


                ad = new AlertDialog.Builder(getContext());

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                final EditText input = new EditText(getContext());
                input.setInputType(TYPE_CLASS_NUMBER);
                //input.setHint("Новый бюджет: ");

                input.setLayoutParams(lp);
                ad.setView(input);

                ad.setTitle("Категория");  // заголовок
                ad.setMessage("Старый бюджет: " + budgets.get(position).getSum() + "\n" + "Новый бюджет: "); // сообщение
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                      //  Toast.makeText(getContext(), "Да", Toast.LENGTH_LONG)
                       //         .show();
                        budgets.get(position).setSum(Double.parseDouble(input.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                       // Toast.makeText(getContext(), "Нет", Toast.LENGTH_LONG)
                       //         .show();
                        dialog.cancel();
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                      //  Toast.makeText(getContext(), "Вы ничего не выбрали",
                       //         Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
            }
        });
        
        return rootView;
    }

    @Override
    public void showBudgets(List<Budget> budgets, List<Double> transactionSums) {
        this.budgets.clear();
        this.transactionSums.clear();
        this.budgets.addAll(budgets);
        this.transactionSums.addAll(transactionSums);
        adapter.notifyDataSetChanged();
    }
}
