package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private EditText editText;
    private RadioGroup radioButtonGroup;
    private RadioButton radioButton;
    private int points = 0;
    private int allAnswered = 0;
    private String mode = "answer mode";
    private String textValues[] = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //    Handle radioButtons
    public void handleRadioButtons() {
        // Radio Group One
        radioButtonGroup = (RadioGroup) findViewById(R.id.radioGroupOne);
        int checkedId = radioButtonGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(checkedId);

        if (checkedId != -1) {
            updateRadioButtons(radioButton);
        }

        // Radio Group Two
        radioButtonGroup = (RadioGroup) findViewById(R.id.radioGroupTwo);
        int checkedId2 = radioButtonGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(checkedId2);

        if (checkedId2 != -1) {
            updateRadioButtons(radioButton);
        }
    }

    public void updateRadioButtons(RadioButton radioButton) {
        if (mode.equals("reset mode")) {
            //radioButton.setChecked(false);
            radioButtonGroup.clearCheck();
        } else {
            allAnswered++;
            if ((radioButton.getText()).equals("Peanut butter")) {
                points++;
            } else if ((radioButton.getText()).equals("Cannelloni")) {
                points++;
            }
        }
    }

    //    Handle checkBoxes
    public void handleCheckBoxes() {
        int checkBoxGroupOne = 0;
        int checkBoxGroupTwo = 0;

        checkBox = (CheckBox) findViewById(R.id.checkbox51);
        if (checkBox.isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, 1);
        }
        checkBox = (CheckBox) findViewById(R.id.checkbox52);
        if (checkBox.isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, -1);
        }
        checkBox = (CheckBox) findViewById(R.id.checkbox53);
        if (checkBox.isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, 1);
        }
        checkBox = (CheckBox) findViewById(R.id.checkbox61);
        if (checkBox.isChecked()) {
            checkBoxGroupTwo = updateCheckBoxes(checkBoxGroupTwo, 1);
        }
        checkBox = (CheckBox) findViewById(R.id.checkbox62);
        if (checkBox.isChecked()) {
            checkBoxGroupTwo = updateCheckBoxes(checkBoxGroupTwo, 1);
        }
        checkBox = (CheckBox) findViewById(R.id.checkbox63);
        if (checkBox.isChecked()) {
            checkBoxGroupTwo = updateCheckBoxes(checkBoxGroupTwo, -1);
        }

        if ((checkBoxGroupOne == 0 || checkBoxGroupTwo == 0) && mode.equals("answer mode")) {
            allAnswered -= 3;
        } else {
            allAnswered += checkBoxGroupOne + checkBoxGroupTwo;
        }
    }

    public int updateCheckBoxes(int checkBoxCounter, int point) {
        if (mode.equals("reset mode")) {
            checkBox.setChecked(false);
        } else {
            checkBoxCounter++;
            points += point;
        }

        return checkBoxCounter;
    }

    //    Handle text
    public void handleText() {
        if (!mode.equals("reset mode")) {
            editText = (EditText) findViewById(R.id.textFieldOne);
            textValues[0] = editText.getText().toString();
            textValues[0] = textValues[0].replaceAll("\\p{Z}", "");
            if (textValues[0].equalsIgnoreCase("Banana")) { // Ignore white-space and case
                allAnswered++;
                points++;
            } else if (textValues[0].equalsIgnoreCase("Bananas")) {
                allAnswered++;
                points++;
            } else if (textValues[0].matches("")) {
                editText.setError("Empty field");
            } else {
                allAnswered++;
                editText.setError("C'mon");
            }

            editText = (EditText) findViewById(R.id.textFieldTwo);
            textValues[1] = editText.getText().toString();
            textValues[1] = textValues[1].replaceAll("\\p{Z}", "");
            if (textValues[1].equalsIgnoreCase("red")) {
                allAnswered++;
                points++;
            } else if (textValues[1].matches("")) {
                editText.setError("Empty field");
            } else {
                allAnswered++;
                editText.setError("C'mon");
            }

        } else {    // Reset mode on
            editText = (EditText) findViewById(R.id.textFieldOne);
            editText.getText().clear();
            editText = (EditText) findViewById(R.id.textFieldTwo);
            editText.getText().clear();
        }
    }

    //    Handle button
    public void buttonAnswerHandler(View view) {
        mode = "answer mode";
        Toast toast;
        handleCheckBoxes();
        handleText();
        handleRadioButtons();


        if (allAnswered < 6) {

            toast = Toast.makeText(this, "Enter all fields and retry", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            String finalResults = "Good job, Score: " + points + "/8";
            toast = Toast.makeText(this, finalResults, Toast.LENGTH_SHORT);
            toast.show();
        }
        allAnswered = 0;
        points = 0;
    }

    public void buttonResetHandler(View view) {
        allAnswered = 0;
        points = 0;
        mode = "reset mode";

        handleCheckBoxes();
        handleRadioButtons();
        handleText();
    }

}
