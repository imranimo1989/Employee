package com.iqrastudio.databaseclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText empName = findViewById(R.id.empName);
        final EditText empDesig = findViewById(R.id.empDesig);
        final  EditText empDep =findViewById(R.id.empDep);

        final CardView EnterButton = findViewById(R.id.enterButton);


        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String empN = empName.getText().toString();
                String empD = empDesig.getText().toString();
                String empDepart = empDep.getText().toString();


                String url = "https://blazeincorporation.com/class-work/emp.php?emp_name="+empN+"&emp_desig="+empD+"&emp_dep="+empDepart;


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
        });


    }
}