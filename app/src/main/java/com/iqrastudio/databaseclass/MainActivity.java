package com.iqrastudio.databaseclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class MainActivity extends AppCompatActivity {

    public CardView cardViewEnterButton;
    public CardView cardViewUpdateButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText empName = findViewById(R.id.empName);
        final EditText empDesig = findViewById(R.id.empDesig);
        final  EditText empDep =findViewById(R.id.empDep);
        final EditText empEmail = findViewById(R.id.empEmail);
        final  EditText empPhone =findViewById(R.id.empPhone);



        cardViewEnterButton = findViewById(R.id.enterButton);
        cardViewUpdateButton = findViewById(R.id.cardViewUpdateButton);













        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        String updName= intent.getStringExtra("emp_name");
        String updDes= intent.getStringExtra("Designation");
        String updDep= intent.getStringExtra("Department");
        String updEmail= intent.getStringExtra("emp_email");
        String updPho= intent.getStringExtra("emp_phone");

        Bundle bun=getIntent().getExtras();
        int val =0;
        val =bun.getInt("VAL");

        if (val==1){
            cardViewUpdateButton.setVisibility(View.VISIBLE);
            cardViewEnterButton.setVisibility(View.GONE);
        }
        else {
            cardViewEnterButton.setVisibility(View.VISIBLE);
            cardViewUpdateButton.setVisibility(View.GONE);
        }




        empName.setText(updName);
        empDesig.setText(updDes);
        empDep.setText(updDep);
        empEmail.setText(updEmail);
        empPhone.setText(updPho);



        String empN = empName.getText().toString();
        String empD = empDesig.getText().toString();
        String empDepart = empDep.getText().toString();
        String empEm = empEmail.getText().toString();
        String empPh = empPhone.getText().toString();




        cardViewEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urlInsert = "https://blazeincorporation.com/class-work/Insert_emp.php?name="+empN+"&desig="+empD+"&dep="+empDepart+"&email="+empEm+"&phone="+empPh;

                DataLoad(urlInsert);


            }
        });
        //=====================================================


        cardViewUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String urlUpdate = "https://blazeincorporation.com/class-work/emp_update.php?id="+id+"&name="+empN+"&desig="+empD+"&dep="+empDepart+"&email="+empEm+"&phone="+empPh;




                DataLoad(urlUpdate);


            }
        });


    }

    private  void DataLoad(String url){


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);

    }
}