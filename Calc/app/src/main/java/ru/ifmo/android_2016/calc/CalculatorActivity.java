package ru.ifmo.android_2016.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


/**
 * Created by alexey.nikitin on 13.09.16.
 */

public final class CalculatorActivity extends Activity {
    private static boolean isReady = false;
    private static char sign = 'n';
    private double result = 0;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        output = (TextView) findViewById(R.id.output);

    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putChar("sign", sign);
        state.putDouble("result", output.getText().toString() == "" ? 0 : Double.parseDouble(output.getText().toString()));
        state.putBoolean("isReady", true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        sign = state.getChar("sign");
        result = state.getDouble("result");
        isReady = state.getBoolean("isReady");
        if (result == 0) {
            output.setText("");
        } else {
            output.setText(String.valueOf(result));
        }
    }

    public void number(View v) {
        Button tmp = (Button) v;
        if (!isReady) {
            output.append(tmp.getText());
        } else {
            output.setText(tmp.getText());
            isReady = false;
        }
    }

    public void sign(View v) {
        Button tmp = (Button) v;
        String str = ((Button) v).getText().toString();
        if (output.getText().toString() != "") {
            if (sign != 'n') {
                double secondParam = Double.parseDouble(output.getText().toString());
                switch (sign) {
                    case '+':
                        result += secondParam;
                        output.setText(String.valueOf(result));
                        break;
                    case '-':
                        result -= secondParam;
                        output.setText(String.valueOf(result));
                        break;
                    case '/':
                        if (secondParam != 0) {
                            result /= secondParam;
                            output.setText(String.valueOf(result));
                        } else {
                            output.setText("Error");
                        }
                        break;
                    case '*':
                        result *= secondParam;
                        output.setText(String.valueOf(result));
                        break;
                }
                sign = str.charAt(0);
                isReady = true;
                output.setText(String.valueOf(result));
            } else {
                switch (str) {
                    case "+":
                        sign = '+';
                        break;
                    case "-":
                        sign = '-';
                        break;
                    case "/":
                        sign = '/';
                        break;
                    case "*":
                        sign = '*';
                        break;
                }
                result = Double.parseDouble(output.getText().toString());
                isReady = true;
            }
        }
    }

    public void clean(View v) {
        sign = 'n';
        output.setText("");
        isReady = false;
        result = 0;
    }

    public void changeSign(View v) {
        double tmp = Double.parseDouble(output.getText().toString());
        if (tmp != 0) {
            tmp *= -1;
            String str = String.valueOf(tmp);
            output.setText(str);
        }
    }

    public void percent(View v) {
        double tmp = Double.parseDouble(output.getText().toString());
        tmp /= 100;
        String str = String.valueOf(tmp);
        output.setText(str);
    }

    public void equal(View v) {
        String str = output.getText().toString();
        if (!(sign == 'n') && str != "") {
            double secondParam = Double.parseDouble(output.getText().toString());
            switch (sign) {
                case '+':
                    result += secondParam;
                    output.setText(String.valueOf(result));
                    break;
                case '-':
                    result -= secondParam;
                    output.setText(String.valueOf(result));
                    break;
                case '/':
                    if (secondParam != 0) {
                        result /= secondParam;
                        output.setText(String.valueOf(result));
                    } else {
                        output.setText("Error");
                    }
                    break;
                case '*':
                    result *= secondParam;
                    output.setText(String.valueOf(result));
                    break;
            }
            sign = 'n';
            isReady = true;
        }
    }

}
