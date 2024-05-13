package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import android.os.Bundle;
import android.widget.ListView;
import androidx.databinding.BindingAdapter;
import com.example.mytrainingapp.helpers.DateHelper;
import com.example.mytrainingapp.log.MyLogger;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class TransactionListFormVM extends AbstractFormVM{

    public TransactionListFormVM(AbstractForm form) {
        super(form);
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_LIST_SHOWN);
    }

    @BindingAdapter(SET_LIST)
    public static void setListAdapter(ListView listView, TransactionListFormVM viewModel) {
        listView.setAdapter(new ListAdapter(viewModel.getForm().getContext(), TransactionsArray.getTransactions()));
        listView.setOnItemClickListener((adapterView, view, transactionNumber, l) -> {
            MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_PRESSED_TRANSACTION + transactionNumber);
            Bundle bundle = new Bundle();
            bundle.putInt(TRANSACTION_NUMBER, transactionNumber);
            NavigationDispatcher.next(R.id.transactionInfoForm, bundle);
        });
    }
}
