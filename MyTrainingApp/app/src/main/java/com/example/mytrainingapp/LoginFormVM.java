package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import com.example.mytrainingapp.helpers.DateHelper;
import com.example.mytrainingapp.log.MyLogger;
import com.google.android.material.textfield.TextInputLayout;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class LoginFormVM extends AbstractFormVM {

    private String address;
    private static MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LoginFormVM(AbstractForm form) {
        super(form);
    }

    public void getTransactions() {
        MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_FIND_TRANSACTION);
        if (address != null && address.replaceAll("\\s+","").length() != 0) {
            if (address.matches(VALIDATION_PATTERN)) {
                Data data = new Data.Builder().putString(ADDRESS, address).build();
                WorkRequest request = new OneTimeWorkRequest.Builder(TransactionWorker.class).setInputData(data).build();
                WorkManager.getInstance(getForm().getActivity()).enqueue(request);
                WorkManager.getInstance(getForm().getContext()).getWorkInfoByIdLiveData(request.getId()).observe(getForm().getActivity(), this::onWorking);
            }
            else{
                MyException.validation(VALUE_IS_INVALID);
                MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_ADDRESS_VALUE + address + LOG_IS_INVALID);
            }
        }
        else{
            MyException.validation(FIELD_IS_EMPTY);
            MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_FIELD_EMPTY);
        }
    }

    private void onWorking(WorkInfo workInfo) {
        if (workInfo.getState().isFinished()) {
            if (workInfo.getState().name().equals(SUCCEEDED)) {
                if (!TransactionsArray.isListEmpty()) {
                    MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_ADDRESS + address + LOG_ADDRESS_CORRECT);
                    NavigationDispatcher.next(R.id.transactionListForm);
                }
                else {
                    MyException.validation(TRANSACTION_NOT_FOUND);
                    MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_ADDRESS + address + LOG_NO_TRANSACTION);
                }
            }
            else {
                MyException.validation(PROBLEMS_WITH_CONNECTION);
                MyLogger.info(DateHelper.currentDate() + LOG_SLASH + LOG_ADDRESS + address + LOG_NO_TRANSACTION);
            }
        }
    }

    @BindingAdapter(ERROR)
    public static void setError(TextInputLayout layout, String errorMessage) {
            layout.setError(errorMessage);
    }

    public MutableLiveData<String> getError() {
        return errorMessage;
    }

    public static void setError(String message) {
        errorMessage.postValue(message);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
