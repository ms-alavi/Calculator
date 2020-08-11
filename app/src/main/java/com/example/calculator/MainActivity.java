package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String BUNDLE_STRING = "BundleString";
    public static final String BUNDLE_NUMBER_1 = "BundleNumber1";
    private Button mBtnDot;
    private Button mBtn0;
    private Button mBtnAssignment;
    private Button mBtnAddition;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;
    private Button mBtnSubmission;
    private Button mBtnMultiple;
    private Button mBtnDivision;
    private Button mBtnDelete;
    private TextView mMonitor;
    private String mNumber = "0";
    private Double mNumber1;
    private Double mDoubleNumber1;
    private Double mNumber2;
    private Double mDoubleNumber2;
    private Double mStorageD;
    private Integer mStorageI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        if (savedInstanceState!=null){
            mNumber=savedInstanceState.getString(BUNDLE_STRING);
            //mNumber1=savedInstanceState.getDouble(BUNDLE_NUMBER_1);
            mMonitor.setText(mNumber);
        }
        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_STRING,mNumber);
        outState.putDouble(BUNDLE_NUMBER_1,mNumber1);
    }

    private void setListeners() {
        mBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("0");
            }
        });
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("1");
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("2");
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("3");
            }
        });
        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("4");
            }
        });
        mBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("5");
            }
        });
        mBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("6");
            }
        });
        mBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("7");
            }
        });
        mBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("8");
            }
        });
        mBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberMaker("9");
            }
        });
        mBtnAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculate("=");
            }
        });
        mBtnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculate("+");

            }
        });
        mBtnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printOperator(".");
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber="0";
                mMonitor.setText(mNumber);
            }
        });
        mBtnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculate("÷");
            }
        });
        mBtnSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculate("-");

            }
        });
        mBtnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculate("×");

            }
        });
    }

    private void numberMaker(String num) {
        if (mNumber.equals("0")) {
            mNumber = "";
        }
        mNumber += num;
        mMonitor.setText(mNumber);
    }

    private void printOperator(String str) {
        String perToken = String.valueOf(mNumber.charAt(mNumber.length() - 1));
        if (perToken.equals("+") || perToken.equals("-") || perToken.equals("÷") || perToken.equals("×") || perToken.equals(".")) {
            String substring = mNumber.substring(0, mNumber.length() - 1);
            substring += str;
            mNumber = substring;
        } else {
            mNumber += str;
        }
        mMonitor.setText(mNumber);
    }

    private void doCalculate(String str) {
        mBtnDot.setEnabled(true);
        String lastToken = String.valueOf(mNumber.charAt(mNumber.length() - 1));
        String firstToken = String.valueOf(mNumber.charAt(0));
        if ((!(mNumber.contains("+") || mNumber.contains("-") || mNumber.contains("÷") ||
                mNumber.contains("×"))) || lastToken.equals("+") || lastToken.equals("-") || lastToken.equals("÷")
                || lastToken.equals("×")) {
            printOperator(str);
            mNumber1 = Double.parseDouble(mNumber.substring(0, mNumber.length() - 1));
        } else {
            int operator = 5;
            int index = 0;
            int[] n = new int[4];
            n[0] = mNumber.indexOf("+");
            n[1] = mNumber.indexOf("-");
            n[2] = mNumber.indexOf("÷");
            n[3] = mNumber.indexOf("×");
            for (int i = 0; i < 4; i++) {
                if (n[i]==0)continue;
                if (n[i] != -1) {
                    operator = i;
                    index = n[i];
                }
            }
            mNumber2 = Double.parseDouble(mNumber.substring(index + 1));
            switch (operator) {
                case 0:
                    mStorageD = mNumber1 + mNumber2;
                    break;
                case 1:
                    mStorageD = mNumber1 - mNumber2;
                    break;
                case 2:
                    mStorageD = mNumber1 / mNumber2;
                    break;
                case 3:
                    mStorageD = mNumber1 * mNumber2;
                    break;
                default:
                    Log.d("Calculator", "switch error");
                    break;
            }

            mNumber = (mStorageD.toString());
            mNumber1 = mStorageD;
             printOperator(str);

        }
    }

    private void findViews() {
        mMonitor = findViewById(R.id.txt_main_monitor);
        mBtn0 = findViewById(R.id.btn_main_0);
        mBtn1 = findViewById(R.id.btn_main_1);
        mBtn2 = findViewById(R.id.btn_main_2);
        mBtn3 = findViewById(R.id.btn_main_3);
        mBtn4 = findViewById(R.id.btn_main_4);
        mBtn5 = findViewById(R.id.btn_main_5);
        mBtn6 = findViewById(R.id.btn_main_6);
        mBtn7 = findViewById(R.id.btn_main_7);
        mBtn8 = findViewById(R.id.btn_main_8);
        mBtn9 = findViewById(R.id.btn_main_9);
        mBtnAddition = findViewById(R.id.btn_main_add);
        mBtnSubmission = findViewById(R.id.btn_main_Submission);
        mBtnMultiple = findViewById(R.id.btn_main_multiple);
        mBtnDivision = findViewById(R.id.btn_main_division);
        mBtnDot = findViewById(R.id.btn_main_dot);
        mBtnDelete = findViewById(R.id.btn_main_delete);
        mBtnAssignment = findViewById(R.id.btn_main_assignment);
    }
}