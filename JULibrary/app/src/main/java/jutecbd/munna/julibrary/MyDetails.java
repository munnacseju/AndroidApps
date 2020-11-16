package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDetails extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText nameEditText, hallEditText, departmentEditText, batchEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        sharedPreferences  = getSharedPreferences("julibrary", Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameEditText = findViewById(R.id.myDetailsNametextViewid);
        hallEditText = findViewById(R.id.myDetailsHallttextViewid);
        departmentEditText = findViewById(R.id.myDetailsDepartmenttextViewid);
        batchEditText = findViewById(R.id.myDetailsBatchtextViewid);


        findViewById(R.id.myDetailsSubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDetails(){
        if(nameEditText.getText().toString().isEmpty()){
            nameEditText.setError("Enter Your Name");
            nameEditText.requestFocus();
            return;
        }
        if(hallEditText.getText().toString().isEmpty()){
            hallEditText.setError("Enter Your Hall");
            hallEditText.requestFocus();
            return;
        }
        if(departmentEditText.getText().toString().isEmpty()){
            departmentEditText.setError("Enter Your Department");
            departmentEditText.requestFocus();
            return;
        }
        if(batchEditText.getText().toString().isEmpty()){
            batchEditText.setError("Enter Your Batch");
            batchEditText.requestFocus();
            return;
        }

        String name = nameEditText.getText().toString().trim();
        String hall = hallEditText.getText().toString().trim();
        String department = departmentEditText.getText().toString().trim();
        String batch = batchEditText.getText().toString().trim();


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("hall", hall);
        editor.putString("department", department);
        editor.putString("batch", batch);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Successfully Profile Edited!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {

        batchEditText.setText(sharedPreferences.getString("batch", ""));
        nameEditText.setText(sharedPreferences.getString("name", ""));
        departmentEditText.setText(sharedPreferences.getString("department", ""));
        hallEditText.setText(sharedPreferences.getString("hall", ""));

        super.onStart();
    }


}
