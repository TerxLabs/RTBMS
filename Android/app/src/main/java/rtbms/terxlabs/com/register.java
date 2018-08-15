package rtbms.terxlabs.com;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class register extends AppCompatActivity {
    Button btnGenerateOTP, btnSignIn;
    EditText etPhoneNumber, etOTP;
    String phoneNumber, otp;
    FirebaseAuth auth;

    private PrefManager prefManager1;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        findViews();


        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber="+91"+etPhoneNumber.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,                     // Phone number to verify
                        60,                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        register.this,        // Activity (for callback binding)
                        mCallback);                      // OnVerificationStateChangedCallbacks
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp=etOTP.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });StartFirebaseLogin();
    }
    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(register.this,phone_otp_success.class));
                            finish();
                        } else {
                            Toast.makeText(register.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void findViews() {
        btnGenerateOTP=findViewById(R.id.btn_generate_otp);
        btnSignIn=findViewById(R.id.btn_sign_in);
        etPhoneNumber=findViewById(R.id.et_phone_number);
        etOTP=findViewById(R.id.et_otp);
    }
    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(register.this,"verification completed",Toast.LENGTH_SHORT).show();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child( "android" ).child( "users" );
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                String name_email = user.getUid();
                assert name_email != null;
                reference = reference.child( name_email );
                reference.child( "contact" ).setValue( phoneNumber );
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(register.this,"verification failed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(register.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
    private void launchHomeScreen() {
        prefManager1.setFirstTimeLaunch(false);
        startActivity(new Intent(register.this, phone_otp_success.class));
        finish();
    }

}