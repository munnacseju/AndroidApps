package jutecbd.munna.julibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editText = findViewById(R.id.userPhoneNumberEditTextid);

        findViewById(R.id.continueButtonid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = editText.getText().toString().trim();
                if(mobileNumber.isEmpty()||mobileNumber.length()<10){
                    editText.setError("Enter a valid mobile number");
                    editText.requestFocus();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), OTP.class);
                intent.putExtra("mobile", mobileNumber);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.registerImageViewid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
