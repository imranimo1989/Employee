package com.iqrastudio.databaseclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView cardViewShowEmp, cardViewInsertEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardViewInsertEmp = findViewById(R.id.cardViewInsEmp);
        cardViewShowEmp = findViewById(R.id.cardViewShowEmp);



        cardViewInsertEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(Dashboard.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 0);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cardViewShowEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),MainActivity2.class));

            }
        });



    }
}