package com.example.mytrainingapp;

import static com.example.mytrainingapp.ListOfConstants.*;
import com.example.mytrainingapp.helpers.DateHelper;
import com.example.mytrainingapp.helpers.DoubleConvertHelper;
import com.example.mytrainingapp.helpers.SharedPreferencesHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class Transaction {

    private static final String COLON = ": ";
    private static final String OPENED_PARENTHESIS = "\n(";
    private static final String CLOSED_PARENTHESIS = ") ";
    private String name;
    private String address;
    private Date date;
    private double amount;
    private String currency;
    private String transactionType;
    private String transactionHash;
    private List<String> transactionInfo = new ArrayList<>();

    public Transaction(JSONObject jsonTransaction, String transactionType) {
        this.transactionType = transactionType;
        try {
            address = transactionType.equals(WITHDRAWAL) ? jsonTransaction.getString(TO) : jsonTransaction.getString(FROM);
            name = SharedPreferencesHelper.getAddressName(address, ADDRESS_NAMES);
            date = new Date(jsonTransaction.getLong(TIME_STAMP)*1000);
            amount = DoubleConvertHelper.convertInDouble(jsonTransaction.getString(VALUE), 18);
            currency = BNB;
            transactionHash = jsonTransaction.getString(HASH);
            transactionInfo.add(INFO_TRANSACTION_HASH + COLON + transactionHash);
            transactionInfo.add(INFO_STATUS + COLON + ("0".equals(jsonTransaction.getString(IS_ERROR)) ? SUCCESS : FAIL));
            transactionInfo.add(INFO_BLOCK + COLON + jsonTransaction.getString(BLOCK_NUMBER));
            transactionInfo.add(INFO_TIMESTAMP + COLON + this.date.toString());
            transactionInfo.add(INFO_FROM + COLON + jsonTransaction.getString(FROM) + OPENED_PARENTHESIS +(transactionType.equals(WITHDRAWAL) ? MY_WALLET : name) + CLOSED_PARENTHESIS);
            transactionInfo.add(INFO_TO + COLON + jsonTransaction.getString(TO) + OPENED_PARENTHESIS +(transactionType.equals(DEPOSIT) ? MY_WALLET : name) + CLOSED_PARENTHESIS);
            transactionInfo.add(INFO_VALUE + COLON + amount + " " + currency);
            transactionInfo.add(INFO_TRANSACTION_FEE + COLON + String.format("%,.10f", DoubleConvertHelper.convertInDouble(jsonTransaction.getString(GAS_PRICE), 18)*DoubleConvertHelper.convertInDouble(jsonTransaction.getString(GAS_USED), 3)) + " " + currency);
        } catch (Exception e) {
            throw MyException.error(e);
        }
    }

    public String getAddress() {
        return NO_NAME.equals(name) ? address : name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return DateHelper.formatDateToString(date, DATE_FORMAT_PATTERN);
    }

    public void setDate(String date) {
        this.date = DateHelper.formatStringToDate(date);
    }

    public String getAmount() {
        return Double.toString(amount);
    }

    public void setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public List<String> getInfo() {
        return transactionInfo;
    }

    public String getTransactionHash(){
        return transactionHash;
    }
}
