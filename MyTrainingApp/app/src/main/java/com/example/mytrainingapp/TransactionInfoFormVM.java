package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.databinding.BindingAdapter;
import com.example.mytrainingapp.helpers.DateHelper;
import com.example.mytrainingapp.log.MyLogger;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class TransactionInfoFormVM extends AbstractFormVM {

    private static int transactionNumber =  0;

    public TransactionInfoFormVM(AbstractForm form) {
        super(form);
        setTransactionNumber(getForm().getArguments().getInt(TRANSACTION_NUMBER));
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_INFO_OF_TRANSACTION + TransactionsArray.getTransactionByNum(transactionNumber).getTransactionHash() + LOG_WITH_NUMBER + transactionNumber + LOG_WAS_SHOWN);
    }

    @BindingAdapter(SET_LIST)
    public static void setListAdapter(ListView listView, TransactionInfoFormVM viewModel) {
        listView.setAdapter(new ArrayAdapter<>(viewModel.getForm().getActivity(), android.R.layout.simple_list_item_1, TransactionsArray.getTransactionByNum(transactionNumber).getInfo()));
    }

    public void returnToList() {
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_RETURNED);
        NavigationDispatcher.back();
    }

    public static void setTransactionNumber(int number) {
        transactionNumber = number;
    }
}
