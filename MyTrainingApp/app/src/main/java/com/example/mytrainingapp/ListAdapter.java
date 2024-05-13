package com.example.mytrainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.databinding.DataBindingUtil;
import com.example.mytrainingapp.databinding.ListAdapterBinding;
import java.util.List;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class ListAdapter extends ArrayAdapter<Transaction> {

    private List<Transaction> transactionList;

    public ListAdapter(Context context, List<Transaction> transactionList) {
        super(context, R.layout.list_adapter, transactionList);
        this.transactionList = transactionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListAdapterBinding listAdapterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_adapter, parent, false);
        listAdapterBinding.setLa(transactionList.get(position));
        return listAdapterBinding.getRoot();
    }
}
