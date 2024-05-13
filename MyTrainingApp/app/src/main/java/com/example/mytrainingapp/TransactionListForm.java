package com.example.mytrainingapp;

import com.example.mytrainingapp.databinding.FragmentTransactionListBinding;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class TransactionListForm extends AbstractForm<FragmentTransactionListBinding, TransactionListFormVM> {

    public TransactionListForm() {
        super(R.layout.fragment_transaction_list);
    }
}