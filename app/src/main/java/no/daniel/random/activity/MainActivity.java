package no.daniel.random.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

import no.daniel.random.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    private TextView resultView;
    private EditText numberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views.
        resultView = findViewById(R.id.resultView);
        numberInput = findViewById(R.id.numberInput);
    }

    /**
     * Gets a random value from the RandomActivity and displays it.
     * @param view The view that called this function.
     */
    public void displayRandomNumber(View view) {
//        Toast.makeText(this, String.valueOf(Random.randomInt(0, 100)), Toast.LENGTH_LONG).show();
        Intent intent = new Intent("no.daniel.random.Random");
        intent.putExtra("maxValue", numberInput.getText().toString());
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Display a random number to a textView if all went well.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            resultView.setText(String.valueOf(intent.getIntExtra("result", 0)));
        }
    }
}