package no.daniel.random.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.daniel.random.R;
import no.daniel.random.Random;

public class RandomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        // Get and parse data from another activity.
        Intent intent = getIntent();
        if (intent != null) {
            int maxValue = 0;
            if (intent.getStringExtra("maxValue") != null) {
                maxValue = Integer.parseInt(intent.getStringExtra("maxValue"));
            }

            // Return data to the activity that started this one.
            Intent resultData = new Intent();
            resultData.putExtra("result", Random.randomInt(0, maxValue));
            setResult(RESULT_OK, resultData);
            finish();
        }
    }
}
