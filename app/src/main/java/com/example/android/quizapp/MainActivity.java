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
    private CheckBox checkBox[] = new CheckBox[6];
    private EditText editText[] = new EditText[2];
    private RadioGroup radioButtonGroup[] = new RadioGroup[2];
    private RadioButton radioButton;
    private int points = 0;
    private int allAnswered = 0;
    private String mode = "answer mode";
    private String textValues[] = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Radio Buttons obj
        radioButtonGroup[0] = (RadioGroup) findViewById(R.id.radioGroupOne);
        radioButtonGroup[1] = (RadioGroup) findViewById(R.id.radioGroupTwo);

        // Check Boxes obj
        checkBox[0] = (CheckBox) findViewById(R.id.checkbox51);
        checkBox[1] = (CheckBox) findViewById(R.id.checkbox52);
        checkBox[2] = (CheckBox) findViewById(R.id.checkbox53);
        checkBox[3] = (CheckBox) findViewById(R.id.checkbox61);
        checkBox[4] = (CheckBox) findViewById(R.id.checkbox62);
        checkBox[5] = (CheckBox) findViewById(R.id.checkbox63);

        // Edit Text obj
        editText[0] = (EditText) findViewById(R.id.textFieldOne);
        editText[1] = (EditText) findViewById(R.id.textFieldTwo);


    }

    //    Handle radioButtons
    public void handleRadioButtons() {
        // Radio Group One
        int checkedId = radioButtonGroup[0].getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(checkedId);

        if (checkedId != -1) {
            updateRadioButtons(radioButton);
        }

        // Radio Group Two
        int checkedId2 = radioButtonGroup[0].getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(checkedId2);

        if (checkedId2 != -1) {
            updateRadioButtons(radioButton);
        }
    }

    public void updateRadioButtons(RadioButton radioButton) {
        if (mode.equals("reset mode")) {
            //radioButton.setChecked(false);
            radioButtonGroup[0].clearCheck();
            radioButtonGroup[1].clearCheck();
        } else {
            allAnswered++;
            if ((radioButton.getText()).equals("Peanut butter")) {
                points++;
            } else if ((radioButton.getText()).equals("Cannelloni")) {
                points++;
            } else {
                points--;
            }
        }
    }

    //    Handle checkBoxes
    public void handleCheckBoxes() {
        int checkBoxGroupOne = 0;
        int checkBoxGroupTwo = 0;

        if (checkBox[0].isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, 1);
        }
        if (checkBox[1].isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, -1);
        }
        if (checkBox[2].isChecked()) {
            checkBoxGroupOne = updateCheckBoxes(checkBoxGroupOne, 1);
        }
        if (checkBox[3].isChecked()) {
            checkBoxGroupTwo = updateCheckBoxes(checkBoxGroupTwo, 1);
        }
        if (checkBox[4].isChecked()) {
            checkBoxGroupTwo = updateCheckBoxes(checkBoxGroupTwo, 1);
        }
        if (checkBox[5].isChecked()) {
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
            //checkBox.setChecked(false);
            for (int i = 0; i < 6; i++) {
                checkBox[i].setChecked(false);
            }
        } else {
            checkBoxCounter++;
            points += point;
        }

        return checkBoxCounter;
    }

    //    Handle text
    public void handleText() {
        if (!mode.equals("reset mode")) {
            textValues[0] = editText[0].getText().toString();
            textValues[0] = textValues[0].replaceAll("\\p{Z}", "");
            if (textValues[0].equalsIgnoreCase("Banana")) { // Ignore white-space and case
                allAnswered++;
                points++;
            } else if (textValues[0].equalsIgnoreCase("Bananas")) {
                allAnswered++;
                points++;
            } else if (textValues[0].matches("")) {
                points--;
                editText[0].setError("Empty field");
            } else {
                points--;
                allAnswered++;
                editText[0].setError("C'mon");
            }

            textValues[1] = editText[1].getText().toString();
            textValues[1] = textValues[1].replaceAll("\\p{Z}", "");
            if (textValues[1].equalsIgnoreCase("red")) {
                allAnswered++;
                points++;
            } else if (textValues[1].matches("")) {
                points--;
                editText[1].setError("Empty field");
            } else {
                points--;
                allAnswered++;
                editText[1].setError("C'mon");
            }

        } else {    // Reset mode on
            editText[0].getText().clear();
            editText[1].getText().clear();
        }
    }

    //    Handle button
    public void buttonAnswerHandler(View view) {
        mode = "answer mode";
        Toast toast;
        handleText();
        handleCheckBoxes();
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
        mode = "reset mode";
        points = 0;
        allAnswered = 0;


        handleCheckBoxes();
        handleRadioButtons();
        handleText();
    }

}
