package rtbms.terxlabs.com;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class blood_request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner s1,s2,s3;

    String sp2;
    String sp1;
    String sp3;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Request" );

        s1 = findViewById(R.id.spinnerState);
        s2 = findViewById(R.id.spinnerCity);
        s3=findViewById(R.id.spinnerBlood);
        s1.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);
        defineButtons();
    }
    public void defineButtons(){
        findViewById(R.id.request).setOnClickListener(buttonClickListner);
    }
    private View.OnClickListener buttonClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent (blood_request.this, blood_results_hospital.class);
            i.putExtra("key3",sp3);
            i.putExtra("key1",sp1);
            i.putExtra("key2",sp2);
            startActivity(i);
        }
    };
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        sp1= String.valueOf(s1.getSelectedItem());
        sp3=String.valueOf(s3.getSelectedItem());
        Log.i(sp2,sp1);
        if(sp1.contentEquals("Haryana")) {
            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Haryana, android.R.layout.simple_list_item_1);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            s2.setAdapter(district);


        }
        if (sp1.contentEquals("Chattisgarh")){


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Chattisgarh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            s2.setAdapter(district);

        }
        if (sp1.contentEquals("Chandigarh")){


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Chandigarh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            s2.setAdapter(district);

        }
        if(sp1.contentEquals("Punjab")) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.punjab, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        if(sp1.contentEquals("Himachal Pradesh")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.HimachalPradesh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        if(sp1.contentEquals("Madhya Pradesh")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Madhya_Pradesh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Maharashtra")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Maharashtra, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Goa")) {


            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Goa, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Tamil Nadu")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Tamil_Nadu, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        if(sp1.contentEquals("Jammu and Kashmir")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Jammu_and_Kashmir, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Assam")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Assam, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Bihar")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Bihar, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }   if(sp1.contentEquals("Meghalaya")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Meghalaya, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Jharkhand")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Jharkhand, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Manipur")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Manipur, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Gujarat")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Gujarat, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Kerala")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Kerala, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Delhi")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Delhi, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Mizoram")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Mizoram, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Nagaland")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Nagaland, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Arunachal Pradesh")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Arunachal_pradesh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Dadra and Nagar Haveli")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Dadra_and_Haveli, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Lakshadweep")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Lakshadweep, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Puducherry")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Puducherry, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("Rajasthan")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Rajasthan, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("Sikkim")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Sikkim, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Odisha")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Odisha, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("Telangana")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Telangana, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("Tripura")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Tripura, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Uttar Pradesh")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Uttar_Pradesh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("West Bengal")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.West_Bengal, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if(sp1.contentEquals("Andaman and Nicobar Islands")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Andaman_and_Nicobar, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        if(sp1.contentEquals("Daman and Diu")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Daman_and_Diu, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }if(sp1.contentEquals("Andhra Pradesh")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Andhra_Pradesh, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }if(sp1.contentEquals("Karnataka")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Karnataka, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if(sp1.contentEquals("Uttarakhand")) {

            ArrayAdapter<CharSequence> district = ArrayAdapter.createFromResource(this,R.array.Uttarakhand, android.R.layout.simple_spinner_item);
            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(district);
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sp2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }




    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



    }
