package com.example.myfinance.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myfinance.data.model.CD;
import com.example.myfinance.data.model.Checking;
import com.example.myfinance.data.model.Loan;

public class FinanceDatabase extends SQLiteOpenHelper {
    private static volatile FinanceDatabase instance = null;
    private static final String TABLE_NAME = "accounts";

    private static final String COL_INITIAL_BALANCE = "initial_balance";
    private static final String COL_INTEREST_RATE = "interest_rate";
    private static final String COL_CURRENT_BALANCE = "current_balance";
    private static final String COL_ACCOUNT_NUMBER = "account_number";
    private static final String COL_PAYMENT_AMOUNT = "payment_amount";


    private FinanceDatabase(@Nullable Context context) {
        super(context, "my_finance.db", null, 1);
    }

    public static FinanceDatabase getInstance(@Nullable Context context) {
        if (instance == null) {
            synchronized (FinanceDatabase.class) {
                if (instance == null) {
                    instance = new FinanceDatabase(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ACCOUNT_NUMBER + " VARCHAR(100) NOT NULL PRIMARY KEY," +
                COL_INITIAL_BALANCE + " DECIMAL DEFAULT 0," +
                COL_INTEREST_RATE + " DECIMAL DEFAULT 0," +
                COL_CURRENT_BALANCE + " DECIMAL DEFAULT 0," +
                COL_PAYMENT_AMOUNT + " DECIMAL DEFAULT 0)";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean saveData(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result > 0;
    }

    public boolean saveCDData(CD cdModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACCOUNT_NUMBER, cdModel.getAccountNumber());
        contentValues.put(COL_CURRENT_BALANCE, cdModel.getCurrentBalance());
        contentValues.put(COL_INITIAL_BALANCE, cdModel.getInitialBalance());
        contentValues.put(COL_INTEREST_RATE, cdModel.getInterestRate());
        return saveData(contentValues);
    }

    public boolean saveLoanData(Loan loanModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACCOUNT_NUMBER, loanModel.getAccountNumber());
        contentValues.put(COL_PAYMENT_AMOUNT, loanModel.getPaymentAmount());
        contentValues.put(COL_INITIAL_BALANCE, loanModel.getInitialBalance());
        contentValues.put(COL_INTEREST_RATE, loanModel.getInterestRate());
        contentValues.put(COL_CURRENT_BALANCE, loanModel.getCurrentBalance());
        return saveData(contentValues);
    }

    public boolean saveCheckingData(Checking checkingModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACCOUNT_NUMBER, checkingModel.getAccountNumber());
        contentValues.put(COL_CURRENT_BALANCE, checkingModel.getCurrentBalance());
        return saveData(contentValues);
    }
}
