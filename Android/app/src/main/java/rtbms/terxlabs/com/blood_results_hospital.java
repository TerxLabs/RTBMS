package rtbms.terxlabs.com;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.fabric.sdk.android.Fabric;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class blood_results_hospital extends AppCompatActivity {
    private FirebaseRecyclerAdapter<info_hospital, Holder> adapter;
    String s;
    String dis;
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
            setContentView(R.layout.activity_blood_results_hospital);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Result" );
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService( Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                answer="You are connected to a WiFi Network";
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                answer="You are connected to a Mobile Network";
        }
        else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        blood_results_hospital.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Sorry");

                // Setting Dialog Message
                alertDialog.setMessage("No Internet connection found");

                // Setting Icon to Dialog


                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        Toast.makeText(getApplicationContext(), "No Internet Connection Found", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog.show();


            }


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String blood_group= bundle.getString("key3");
        final String state_selection= bundle.getString("key1");
        assert blood_group != null;
        if(blood_group.equals( "A+" ))
        {
            blood_group = "aPCount";
        }
        if(blood_group.equals("A-"))
        {
            blood_group = "aNCount";
        }
        if(blood_group.equals("B+"))
        {
            blood_group = "bPCount";
        }
        if(blood_group.equals("B-"))
        {
            blood_group = "bNCount";
        }
        if(blood_group.equals("AB+"))
        {
            blood_group = "abPCount";
        }
        if(blood_group.equals("AB-"))
        {
            blood_group = "abNCount";
        }
        if(blood_group.equals("O+"))
        {
            blood_group = "oPCount";
        }
        if(blood_group.equals("O-"))
        {
            blood_group = "oNCount";
        }
        final String District_selection=bundle.getString("key2");
        setTitle("web");
        assert District_selection != null;
        assert state_selection != null;
        DatabaseReference datarefer = FirebaseDatabase.getInstance().getReference().child( "web" ).child("hospitals").child(state_selection).child(District_selection);
        datarefer.keepSynced(true);
        RecyclerView obj_hospital = findViewById(R.id.hospital_recycleView);
        final ProgressDialog Dialog = new ProgressDialog(blood_results_hospital.this);

        Dialog.setMessage("Searching Hospitals");
        Dialog.show();
        assert state_selection != null;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child( "web" ).child("hospitals").child(state_selection).child(District_selection);
        Log.i("destination", String.valueOf(blood_group));
          Query bloodquery=ref.orderByChild( blood_group );



            Query bloodcount = bloodquery.startAt(1);
            obj_hospital.hasFixedSize();
            obj_hospital.setLayoutManager(new LinearLayoutManager(this));
            bloodcount.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Intent intent = new Intent( getApplicationContext(), hospital_result_notfound.class );
                    Dialog.hide();
                    startActivity( intent );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i( "oncancelled ","Exception",databaseError.toException() );
            }
        } );
        final FirebaseRecyclerOptions<info_hospital> firerecycleoptions = new FirebaseRecyclerOptions.Builder<info_hospital>().setQuery(bloodcount, info_hospital.class).build();

        adapter = new FirebaseRecyclerAdapter<info_hospital, Holder>(firerecycleoptions) {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected void onBindViewHolder(@NonNull Holder holder, final int position, @NonNull final info_hospital model) {
                holder.setName(model.getName());
                holder.setAddress(model.getAddress());
                    Dialog.hide();
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      String id = model.getHid();
                        s=id;
                        dis=District_selection;
                        Intent intent = new Intent(getApplicationContext(), Detail_Hospital.class);
                        intent.putExtra("district",dis);
               intent.putExtra("HID", id);
               intent.putExtra( "state",state_selection );
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.hospital_name_recycleresult, parent, false);

                return new Holder(view);
            }
        };

        obj_hospital.setAdapter(adapter);
    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        View v;
        Holder(View itemView){
            super(itemView);
            v = itemView;
        }
        @SuppressLint("SetTextI18n")
        public void setName(String title){
            TextView tname = v.findViewById(R.id.Hospital_Name);
            tname.setText("Name :- "+title);
        }
        @SuppressLint("SetTextI18n")
        public void setAddress(String desc){
            TextView taddr = v.findViewById(R.id.Hospital_Address);
            taddr.setText("Address :- "+desc);
        }

    }
}