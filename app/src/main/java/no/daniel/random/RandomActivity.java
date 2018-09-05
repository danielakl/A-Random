package no.daniel.random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

//import android.util.Log;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_random);

        Intent intent = getIntent();
        if (intent != null) {
            int amount = intent.getIntExtra("amount", 1);
            int bound = intent.getIntExtra("bound", 10);
            bound = (bound <= 0) ? 1 : bound;
            intent.removeExtra("amount");
            intent.removeExtra("bound");
            amount = amount < 1 ? 1 : amount;
            Random random = new Random();
            if (amount == 1) {
                intent.putExtra("result", random.nextInt(bound));
            } else {
                int[] randoms = new int[amount];
                for (int i = 0; i < randoms.length; i++) {
                    randoms[i] = random.nextInt(bound);
                }
                intent.putExtra("results", randoms);
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
