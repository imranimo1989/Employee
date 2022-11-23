package com.iqrastudio.databaseclass;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    GridView gridView;
    SearchView searchView;
    ArrayAdapter<String> adapter;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList();
    HashMap<String,String> hashMap;

    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.listView);
        progressBar=findViewById(R.id.progressBar);
searchView = findViewById(R.id.searchView);
        final  TextView textView = findViewById(R.id.header_title);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

        LoadEmpData();


/*

        hashMap = new HashMap<>();
        hashMap.put("Name", "Mohammed Imran");
        hashMap.put("Des", "A. Manager");
        hashMap.put("Dep", "ICT");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("Name", "Mohammed");
        hashMap.put("Des", "A. Manager");
        hashMap.put("Dep", "ICT");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("Name", "Imran");
        hashMap.put("Des", "A. Manager");
        hashMap.put("Dep", "ICT");
        arrayList.add(hashMap);
*/
/*

        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);

*/


    }




    private  class  MyAdapter extends BaseAdapter {

        LayoutInflater layoutInflater;


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View myView = layoutInflater.inflate(R.layout.emp_inf,viewGroup,false);

            TextView tvName = myView.findViewById(R.id.name);
            TextView tvDes = myView.findViewById(R.id.desig);
            TextView tvDep = myView.findViewById(R.id.dep);
            TextView tvEmail = myView.findViewById(R.id.tvEmail);
            TextView tvPhone = myView.findViewById(R.id.tvPhone);


            Button btDel = myView.findViewById(R.id.buttonDel);
            Button btUpd = myView.findViewById(R.id.buttonUpdate);


            HashMap<String , String>    hashMap = arrayList.get(position);

           String id    = hashMap.get("id");
            String name = hashMap.get("emp_name");
            String des = hashMap.get("Designation");
            String dep = hashMap.get("Department");
            String email= hashMap.get("emp_email");
            String phone = hashMap.get("emp_phone");




            tvName.setText(position+1+": Name: "+name);
            tvDes.setText("Designation: "+des);
            tvDep.setText("Department: "+dep);
            tvEmail.setText("Email: "+email);
            tvPhone.setText("Phone: "+phone);



btUpd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


Intent intent = new Intent(MainActivity2.this,MainActivity.class);
intent.putExtra("id",id);
        intent.putExtra("emp_name",name);
        intent.putExtra("Designation",des);
        intent.putExtra("Department",dep);
        intent.putExtra("emp_email",email);
        intent.putExtra("emp_phone",phone);

        Bundle bundle = new Bundle();
        bundle.putInt("VAL", 1);
        intent.putExtras(bundle);

        startActivity(intent);




    }
});





            btDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(MainActivity2.this)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    //===============================================
                                    String url = "https://blazeincorporation.com/class-work/del_emp.php?id="+id;


                                    // Request a string response from the provided URL.
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    // Display the first 500 characters of the response string.



                                                    Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();

                                                    LoadEmpData();

                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });

                                    RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
                                    queue.add(stringRequest);
                                    //===============================================

                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setTitle("Delete")
                            .setMessage("Are you sure want to Delete?")
                            .show();







                }




            });





            return myView;
        }

        //=================================


    }


    private void LoadEmpData() {

        arrayList = new ArrayList<>();
        String url = "https://blazeincorporation.com/class-work/emp_view.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);


                try {
                    for (int x =0; x<response.length();x++){
                        JSONObject jsonObject = response.getJSONObject(x);
                        String id   = jsonObject.getString("id");
                        String name=  jsonObject.getString("emp_name");
                        String desi=  jsonObject.getString("Designation");
                        String depa=  jsonObject.getString("Department");
                        String emai=  jsonObject.getString("emp_email");
                        String pho=  jsonObject.getString("emp_phone");

                        // textView.append(name+"\n"+desi+"\n"+depa);


                        hashMap = new HashMap<>();
                        hashMap.put("id",id);
                        hashMap.put("emp_name", name);
                        hashMap.put("Designation",desi );
                        hashMap.put("Department", depa);
                        hashMap.put("emp_email",emai );
                        hashMap.put("emp_phone", pho);
                        arrayList.add(hashMap);


                        if (arrayList.size()>0){
                            MyAdapter myAdapter = new MyAdapter();
                            gridView.setAdapter(myAdapter);

                        }



                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {



                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String s) {
                                return false;
                            }
                        });

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    progressBar.setVisibility(View.GONE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        requestQueue.add(jsonArrayRequest);



    }



}