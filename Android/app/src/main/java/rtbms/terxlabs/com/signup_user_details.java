package rtbms.terxlabs.com;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.concurrent.TimeUnit;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class signup_user_details extends AppCompatActivity implements AdapterView.OnItemSelectedListener  ,
        View.OnClickListener{
    EditText inputname, inputemail, inputage;
    EditText mPhoneNumberField, mVerificationField;
    Button mStartButton, mVerifyButton, mResendButton;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;

    private static final String TAG = "PhoneAuthActivity";
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    String user_state_string, user_blood_group_string, user_gender_string, user_city_String;
    Spinner user_state, user_bloodgroup, user_gender, user_city;
    private ProgressBar progressBar;
    Button Signup;
    Intent intent;
    DatabaseReference  reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_signup_user_details );
        user_bloodgroup = findViewById( R.id.signup_blood_group );
        user_city = findViewById( R.id.sign_up_city );
        user_gender = findViewById( R.id.signup_gender );
        user_state = findViewById( R.id.signup_state );
        progressBar = findViewById( R.id.progressBar );
        this.setRequestedOrientation( SCREEN_ORIENTATION_PORTRAIT );
        user_state.setOnItemSelectedListener( this );
        user_gender.setOnItemSelectedListener( this );
        user_bloodgroup.setOnItemSelectedListener( this );
        inputage = findViewById( R.id.signup_age );

        inputname = findViewById( R.id.signup_name );
        final ProgressDialog dialog = new ProgressDialog( signup_user_details.this );
        dialog.setMessage( "Please Wait" );

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        mPhoneNumberField =  findViewById(R.id.et_phone_number);
        mVerificationField =  findViewById(R.id.et_otp);

        mStartButton =  findViewById(R.id.button_phone_number);
        mVerifyButton =  findViewById(R.id.button_phone_otp);
        mResendButton = findViewById( R.id.button_resend );

        mStartButton.setOnClickListener(this);
        mVerifyButton.setOnClickListener(this);
        mResendButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseApp firebaseApp;
        FirebaseApp.initializeApp( this );
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        user_state_string = String.valueOf( user_state.getSelectedItem() );
        user_gender_string = String.valueOf( user_gender.getSelectedItem() );
        user_blood_group_string = String.valueOf( user_bloodgroup.getSelectedItem() );
        if (user_state_string.contentEquals( "Haryana" )) {
            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Haryana, android.R.layout.simple_list_item_1 );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

            user_city.setAdapter( district );


        }
        if (user_state_string.contentEquals( "Chattisgarh" )) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Chattisgarh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
            user_city.setAdapter( district );

        }
        if (user_state_string.contentEquals( "Chandigarh" )) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Chandigarh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
            user_city.setAdapter( district );

        }
        if (user_state_string.contentEquals( "Punjab" )) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.punjab, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Himachal Pradesh" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.HimachalPradesh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Madhya Pradesh" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Madhya_Pradesh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Maharashtra" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Maharashtra, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Goa" )) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Goa, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Tamil Nadu" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Tamil_Nadu, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Jammu and Kashmir" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Jammu_and_Kashmir, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Assam" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Assam, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Bihar" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Bihar, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Meghalaya" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Meghalaya, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Jharkhand" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Jharkhand, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Manipur" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Manipur, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Gujarat" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Gujarat, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Kerala" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Kerala, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Delhi" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Delhi, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Mizoram" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Mizoram, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Nagaland" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Nagaland, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Arunachal Pradesh" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Arunachal_pradesh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Dadra and Nagar Haveli" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Dadra_and_Haveli, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Lakshadweep" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Lakshadweep, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Puducherry" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Puducherry, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Rajasthan" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Rajasthan, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Sikkim" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Sikkim, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Odisha" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Odisha, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Telangana" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Telangana, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Tripura" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Tripura, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Uttar Pradesh" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Uttar_Pradesh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "West Bengal" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.West_Bengal, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Andaman and Nicobar Islands" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Andaman_and_Nicobar, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Daman and Diu" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Daman_and_Diu, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Andhra Pradesh" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Andhra_Pradesh, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

        }
        if (user_state_string.contentEquals( "Karnataka" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Karnataka, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
        if (user_state_string.contentEquals( "Uttarakhand" )) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource( this, R.array.Uttarakhand, android.R.layout.simple_spinner_item );
            district.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            user_city.setAdapter( district );
            user_city.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user_city_String = parent.getItemAtPosition( position ).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );
        }
    }
        private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d( TAG, "signInWithCredential:success" );
                                FirebaseUser user = task.getResult().getUser();
                                //    startActivity(new Intent(MainActivity.this, hello.class));
                                finish();
                            } else {
                                Log.w( TAG, "signInWithCredential:failure", task.getException() );
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    mVerificationField.setError( "Invalid code." );
                                }
                            }
                        }
                    });
        }


        private void startPhoneNumberVerification(String phoneNumber) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks
        }

        private void verifyPhoneNumberWithCode(String verificationId, String code) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        }

        private void resendVerificationCode(String phoneNumber,
                PhoneAuthProvider.ForceResendingToken token) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks,         // OnVerificationStateChangedCallbacks
                    token);             // ForceResendingToken from callbacks
        }

        private boolean validatePhoneNumber() {
            String phoneNumber = mPhoneNumberField.getText().toString();
            if (TextUtils.isEmpty(phoneNumber)) {
                mPhoneNumberField.setError("Invalid phone number.");
                return false;
            }
            return true;
        }
        @Override
        public void onStart() {
            super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
           //     startActivity(new Intent(MainActivity
             //           .this, hello.class));
                finish();
            }



        Signup = findViewById( R.id.sign_up_button );

        Signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = inputname.getText().toString().trim();
                String Age = inputage.getText().toString().trim();
                double age = Double.valueOf( Age );
                if (TextUtils.isEmpty( name )) {
                    Toast.makeText( getApplicationContext(), "Enter Name!", Toast.LENGTH_SHORT ).show();
                    return;
                }

                 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child( "android" ).child( "users" );

                assert user != null;


                String email_=user.getUid();
                assert email_ != null;
                String email=user.getEmail();
               reference=reference.child( email_ );
if(age<18){
    Toast.makeText( getApplicationContext(),"Please Enter the age >18",Toast.LENGTH_SHORT ).show();
}
                if (age >= 18) {

                    reference.child( "email" ).setValue( email );
                    reference.child( "name" ).setValue( name );
                    reference.child( "age" ).setValue( Age );
                    reference.child( "city" ).setValue( user_city_String );
                    reference.child( "phone" ).setValue( mPhoneNumberField );
                    reference.child( "gender" ).setValue( user_gender_string );

                    //        reference.child( "contact" ).setValue( phone );


                    reference.child( "blood_group" ).setValue( user_blood_group_string );
                    reference.child( "state" ).setValue( user_state_string );
                    progressBar.setVisibility( View.VISIBLE );
                    startActivity( new Intent( signup_user_details.this, signup_user_details.class ) );
                } else {
                    Toast.makeText( getApplicationContext(), "Something is missing or Wrong!", Toast.LENGTH_SHORT ).show();


                }

            }

        } );

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility( View.GONE );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
public void onClick(View view) {
    switch (view.getId()) {
        case R.id.button_phone_number:
            if (!validatePhoneNumber()) {
                return;
            }
            startPhoneNumberVerification(mPhoneNumberField.getText().toString());
            break;
        case R.id.button_phone_otp:
            String code = mVerificationField.getText().toString();
            if (TextUtils.isEmpty(code)) {
                mVerificationField.setError("Cannot be empty.");
                return;
            }

            verifyPhoneNumberWithCode(mVerificationId, code);
            break;
        case R.id.button_resend:
            resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
            break;
    }

}

}

