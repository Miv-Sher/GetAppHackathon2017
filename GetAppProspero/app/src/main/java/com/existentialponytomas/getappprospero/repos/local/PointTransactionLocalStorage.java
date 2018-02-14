package com.existentialponytomas.getappprospero.repos.local;

import com.existentialponytomas.getappprospero.model.PointTransaction;
import com.existentialponytomas.getappprospero.repos.PointTransactionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class PointTransactionLocalStorage implements PointTransactionRepository {

    List<PointTransaction> pointTransactions;

    private static PointTransactionLocalStorage instance;


    private PointTransactionLocalStorage() {
        pointTransactions = new ArrayList<>();
        pointTransactions.add(new PointTransaction(50, 100500, "Распределил бюджет"));
        pointTransactions.add(new PointTransaction(-20, 500500, "Купил недорогую еду в опасном месте"));
        pointTransactions.add(new PointTransaction(5000, 100500, "За достижение Дядюшка Тыква"));
        pointTransactions.add(new PointTransaction(1000, 500500, "За успешное соблюдение месячного бюджета"));
        pointTransactions.add(new PointTransaction(80, 100500, "Воспользовался акцией партнера Coffee House"));
        pointTransactions.add(new PointTransaction(-20, 500500, "Купил недорогую еду в опасном месте"));
    }
    public static PointTransactionLocalStorage getInstance() {
        if (instance == null) {
            instance = new PointTransactionLocalStorage();
        }
        return  instance;
    }

    @Override
    public List<PointTransaction> get() {
        return pointTransactions;
    }

    @Override
    public List<PointTransaction> getPositive() {
        List<PointTransaction> result = new ArrayList<>();
        for (int i = 0; i < pointTransactions.size(); i++) {
            if (pointTransactions.get(i).isPositive()) {
                result.add(pointTransactions.get(i));
            }
        }
        return result;
    }

    @Override
    public List<PointTransaction> getNegative() {
        List<PointTransaction> result = new ArrayList<>();
        for (int i = 0; i < pointTransactions.size(); i++) {
            if (pointTransactions.get(i).isNegative()) {
                result.add(pointTransactions.get(i));
            }
        }
        return result;
    }
}
