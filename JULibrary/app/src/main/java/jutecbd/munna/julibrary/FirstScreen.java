package jutecbd.munna.julibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        //Hide ActionBar and TitleBar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                waitForRun();
            }
        });
        thread.start();
    }

    public void waitForRun(){
        for(int i=1; i<=1; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        SharedPreferences sharedPreferences = getSharedPreferences("julibrary", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("phone","").equals("")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("phone", sharedPreferences.getString("phone", ""));
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        }

    }
}
