package com.example.myfinance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinance.data.db.FinanceDatabase;
import com.example.myfinance.data.model.CD;
import com.example.myfinance.data.model.Checking;
import com.example.myfinance.data.model.Loan;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAccountNumber, editTextInitialBalance, editTextPaymentAmount, editTextCurrentBalance, editTextInterestRate;
    private Type selectedAccountType = Type.CD;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAccountNumber = findViewById(R.id.edt_account_number);
        editTextInitialBalance = findViewById(R.id.edt_initial_balance);
        editTextInterestRate = findViewById(R.id.edt_interest_rate);
        editTextCurrentBalance = findViewById(R.id.edt_current_balance);
        editTextPaymentAmount = findViewById(R.id.edt_payment_amount);

        Button buttonSave = findViewById(R.id.btn_save_account);
        Button buttonClear = findViewById(R.id.btn_clear_inputs);
        RadioGroup radioGroupAccountType = findViewById(R.id.rg_account_type);

        buttonSave.setOnClickListener(view -> save());
        radioGroupAccountType.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton selectedAccount = radioGroup.findViewById(i);
            if (selectedAccount.getText().toString().equals("CD Account")) {
                selectedAccountType = Type.CD;
            } else if (selectedAccount.getText().toString().equals("Loan Account")) {
                selectedAccountType = Type.LOAN;
            } else {
                selectedAccountType = Type.CHECKING;
            }
            changeInputVisibility();
        });
        buttonClear.setOnClickListener(view -> {
            editTextCurrentBalance.setText("");
            editTextInterestRate.setText("");
            editTextAccountNumber.setText("");
            editTextInitialBalance.setText("");
            editTextPaymentAmount.setText("");
        });
    }

    /**
     * Saves account data based on the selected account type.
     */
    private void save() {
        FinanceDatabase financeDatabase = FinanceDatabase.getInstance(this);
        switch (selectedAccountType) {
            case CD:
                showMessage(financeDatabase.saveCDData(createCDModel()) ? "CD account details saved successfully" : "Something went wrong. Please try again.");
                break;
            case LOAN:
                showMessage(financeDatabase.saveLoanData(createLoanModel()) ? "Loan account details saved successfully" : "Something went wrong. Please try again.");
                break;
            default:
                showMessage(financeDatabase.saveCheckingData(createCheckingModel()) ? "Checking account details saved successfully" : "Something went wrong. Please try again.");
        }
    }

    private Checking createCheckingModel() {
        return new Checking(
                editTextAccountNumber.getText().toString(),
                Double.parseDouble(editTextCurrentBalance.getText().toString())
        );
    }

    private Loan createLoanModel() {
        return new Loan(
                editTextAccountNumber.getText().toString(),
                Double.parseDouble(editTextInitialBalance.getText().toString()),
                Double.parseDouble(editTextCurrentBalance.getText().toString()),
                Double.parseDouble(editTextInterestRate.getText().toString()),
                Double.parseDouble(editTextPaymentAmount.getText().toString())
        );
    }

    private CD createCDModel() {
        return new CD(
                editTextAccountNumber.getText().toString(),
                Double.parseDouble(editTextInitialBalance.getText().toString()),
                Double.parseDouble(editTextCurrentBalance.getText().toString()),
                Double.parseDouble(editTextInterestRate.getText().toString())
        );
    }

    private void changeInputVisibility() {
        switch (selectedAccountType) {
            case CD:
                editTextPaymentAmount.setEnabled(false);
                editTextInitialBalance.setEnabled(true);
                editTextInterestRate.setEnabled(true);
                break;
            case LOAN:
                editTextPaymentAmount.setEnabled(true);
                editTextInitialBalance.setEnabled(true);
                editTextInterestRate.setEnabled(true);
                break;
            default:
                editTextPaymentAmount.setEnabled(false);
                editTextInitialBalance.setEnabled(false);
                editTextInterestRate.setEnabled(false);
        }
    }

    /**
     * Displays a message using Snackbar.
     */
    private void showMessage(String message) {
        View rootView = findViewById(R.id.root_layout);
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    public enum Type {
        CD, LOAN, CHECKING
    }
}
