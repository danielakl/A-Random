package no.daniel.random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int RANDOM_REQUEST_CODE = 0;

    private static boolean addition = true;
    private static int value1 = -1;
    private static int value2 = -1;

    private TextView taskHeaderText;
    private TextView taskText;
    private EditText answerInput;
    private EditText boundInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup views.
        taskHeaderText = findViewById(R.id.taskHeaderView);
        taskText = findViewById(R.id.taskView);
        answerInput = findViewById(R.id.answerInput);
        boundInput = findViewById(R.id.boundInput);
        final Button additionButton = findViewById(R.id.additionBtn);
        final Button multiplyButton = findViewById(R.id.multiplyBtn);

        // Set default initial bound.
        boundInput.setText(String.format(Locale.getDefault(), "%d", 10));

        // Setup click listeners.
        additionButton.setOnClickListener(view -> {
            checkAnswer();
            addition = true;
            startGame();
        });

        multiplyButton.setOnClickListener(view -> {
            checkAnswer();
            addition = false;
            startGame();
        });

        startGame();
    }

    private void startGame() {
        taskHeaderText.setText("");
        String bound = boundInput.getText().toString();
        getRandomInt(strToInt(bound), 2);
        int textId = (addition) ? R.string.txt_addition : R.string.txt_multiply;
        taskHeaderText.setText(textId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Only check relevant results.
        if (requestCode == RANDOM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
//                Toast.makeText(MainActivity.this, Integer.toString(data.getIntExtra("result", 0)), Toast.LENGTH_LONG).show();
                int[] results = data.getIntArrayExtra("results");
                if (results != null && results.length > 1) {
                    value1 = results[0];
                    value2 = results[1];
                    int task = (addition) ? R.string.txt_task_addition : R.string.txt_task_multiply;
                    taskText.setText(getResources().getString(task, value1, value2));
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getRandomInt(int upperBound, int amount) {
        Intent intent = new Intent(getString(R.string.action_random));
        intent.putExtra("bound", upperBound);
        intent.putExtra("amount", amount);
        startActivityForResult(intent, RANDOM_REQUEST_CODE);
    }

    private void checkAnswer() {
        int givenAnswer = strToInt(answerInput.getText().toString());
        int correctAnswer = addition ? value1 + value2 : value1 * value2;
        boolean correct = givenAnswer == correctAnswer;
        if (correct) {
            Toast.makeText(MainActivity.this, R.string.txt_correct_answer, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.txt_wrong_answer, correctAnswer), Toast.LENGTH_LONG).show();
        }
        answerInput.setText("");
    }

    private static int strToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
