package rtbms.terxlabs.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class Profile extends AppCompatActivity {
TextView gender_user,state,blood_group,phonenumber,city_user,age_user;
String gender_string,state_string,blood_group_string,user_city_String;
String phonenumber_string,user_age_String;
//private  GoogleSignInClient Pli;
     FirebaseAuth.AuthStateListener authListener;
    FirebaseAuth auth;
    private ActionBar actionbar;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#DD2C00" ) ) );
        actionbar.setTitle( "Profile" );
        gender_user=findViewById( R.id.textview_gender );
        age_user=findViewById( R.id.user_age );
        city_user=findViewById( R.id.user_city );
        state=findViewById( R.id.textview_state );
        blood_group=findViewById( R.id.textview_blood );
        phonenumber=findViewById( R.id.textview_phone );
        Button signout=findViewById(R.id.signout);
        auth = FirebaseAuth.getInstance();




signout.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ApplySharedPref")
    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
FirebaseAuth auth  = FirebaseAuth.getInstance();
auth.signOut();
        assert user != null;
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "User Logout", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        SharedPreferences pref=getSharedPreferences( "ActivityPref", Context.MODE_PRIVATE );
        SharedPreferences.Editor edty=pref.edit();
        edty.putBoolean( "activity_executed",false );
        edty.commit();
        startActivity( new Intent( Profile.this, signwithgoogle.class ) );
        }
});

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String uid = user.getUid();
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            TextView tv = findViewById( R.id.profile_name );
            TextView te = findViewById( R.id.profile_email );
            ImageView im = findViewById( R.id.profile_image );

            te.setText( email );
            tv.setText( name );
            Picasso.with( this ).load( photoUrl ).into( im );
        }
        DatabaseReference user_references;

        user_references = FirebaseDatabase.getInstance().getReference().child("android").child( "users" );
        assert user != null;
        Query query=user_references.orderByChild( user.getUid());
            query.addValueEventListener( new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                        final info_user_details android_users = locationSnapshot.getValue( info_user_details.class );
                        assert android_users != null;
                        phonenumber_string=android_users.getContact();
                        user_city_String=android_users.getCity();
                        user_age_String=android_users.getAge();
                        gender_string=android_users.getGender();
                        state_string=android_users.getState();
                        blood_group_string=android_users.getBlood_group();
                        gender_user.setText( gender_string );
                        state.setText( state_string );
                        age_user.setText( user_age_String );
                        city_user.setText( user_city_String );
                        blood_group.setText( blood_group_string );
                        phonenumber.setText( phonenumber_string ) ;
                    }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            } );

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity( new Intent( Profile.this, signwithgoogle.class ) );
                    finish();
                }
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}

