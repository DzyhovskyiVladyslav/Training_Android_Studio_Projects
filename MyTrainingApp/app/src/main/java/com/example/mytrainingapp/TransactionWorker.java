package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import android.content.Context;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.mytrainingapp.helpers.ArrayListHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class TransactionWorker extends Worker {

    private String address = "";

    public TransactionWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        Data data = getInputData();
        address = data.getString(ADDRESS);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(FIRST_URL_PART + address + SECOND_URL_PART).openConnection().getInputStream()))){
            String allTextLines = getTextLinesFromBuffer(bufferedReader);
            if(!allTextLines.isEmpty()){
                TransactionsArray.setTransactions(createListFromLines(allTextLines));
            }
        }catch (Exception e){
            throw MyException.error(e);
        }
        return Result.success();
    }

    private String getTextLinesFromBuffer(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw MyException.error(e);
        }
        return sb.toString();
    }

    private List<Transaction> createListFromLines(String lines) {
        List<Transaction> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(lines);
            JSONArray listTransactions = jsonObject.getJSONArray(RESULT);
            for(int i = 0; i < listTransactions.length(); i++) {
                JSONObject jsonTransaction = listTransactions.getJSONObject(i);
                String transactionType = jsonTransaction.getString(FROM).equals(address) ? WITHDRAWAL: DEPOSIT;
                list.add(new Transaction(jsonTransaction, transactionType));
            }
        } catch (Exception e) {
            throw MyException.error(e);
        }
        return ArrayListHelper.changeOrder(list);
    }
}
