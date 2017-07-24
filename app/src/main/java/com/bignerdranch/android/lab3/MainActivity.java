package com.bignerdranch.android.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int VALUE_A_REQUEST_CODE = 0;
    private static final int VALUE_B_REQUEST_CODE = 1;

    private static final String VALUE_A_KEY = "value_A_key";
    private static final String VALUE_B_KEY = "value_B_key";

    private int mValueA;
    private int mValueB;

    private Button mButtonA;
    private Button mButtonB;

    private TextView mTextViewA;
    private TextView mTextViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewA = (TextView) findViewById(R.id.value_A_textView);
        mTextViewB = (TextView) findViewById(R.id.value_B_textView);

        mButtonA = (Button) findViewById(R.id.value_A_button);
        mButtonB = (Button) findViewById(R.id.value_B_button);

        mButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SliderActivity.class);
                i.putExtra(SliderActivity.SLIDER_VALUE_KEY, mValueA);
                startActivityForResult(i, VALUE_A_REQUEST_CODE);
            }
        });

        mButtonB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SliderActivity.class);
                i.putExtra(SliderActivity.SLIDER_VALUE_KEY, mValueB);
                startActivityForResult(i, VALUE_B_REQUEST_CODE);
            }
        });

        if (savedInstanceState != null) {
            mValueA = savedInstanceState.getInt(VALUE_A_KEY, 0);
            mValueB = savedInstanceState.getInt(VALUE_B_KEY, 0);
            mTextViewA.setText("" + mValueA);
            mTextViewB.setText("" + mValueB);
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == VALUE_A_REQUEST_CODE) {
                mValueA = SliderActivity.getSliderValueFromReturnIntent(data);
                mTextViewA.setText("" + mValueA);
            }
            else if (requestCode == VALUE_B_REQUEST_CODE) {
                mValueB = SliderActivity.getSliderValueFromReturnIntent(data);
                mTextViewB.setText("" + mValueB);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(VALUE_A_KEY, mValueA);
        savedInstanceState.putInt(VALUE_B_KEY, mValueB);
    }
}
