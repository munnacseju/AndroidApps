package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {

    private String mVerificationId;
    private EditText editTextCode;
    private FirebaseAuth mAuth;
    private String phoneNumber;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.varificationPhoneNumberEditTextid);
        textView = findViewById(R.id.otpTextViewid);

        phoneNumber = getIntent().getStringExtra("mobile");
        sendVerificationCode(phoneNumber);

        findViewById(R.id.varificationButtonid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });

    }


    private void sendVerificationCode(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88" + mobileNumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {



            String code = phoneAuthCredential.getSmsCode();
            Toast.makeText(getApplicationContext(), "Verifying '"+code+"' Code", Toast.LENGTH_SHORT).show();

            if (code != null) {
                editTextCode.setText(code);
                textView.setText("Automatic Verifying");
                verifyVerificationCode(code);
            }


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), "Failed! \n"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(getApplicationContext(), "Verification Code sending", Toast.LENGTH_SHORT).show();
            mVerificationId = s;
        }


    };


    private void verifyVerificationCode(String code) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTP.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(getApplicationContext(), "Verification Successfull", Toast.LENGTH_SHORT).show();


                            SharedPreferences sharedPreferences = getSharedPreferences("julibrary", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("phone",phoneNumber);
                            editor.commit();


                            if(!sharedPreferences.getString("name","").equals("")){
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("phone", sharedPreferences.getString("phone", ""));
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(), MyDetails.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Sorry!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(OTP.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
