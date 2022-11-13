package com.iqrastudio.databaseclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    GridView gridView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList();
    HashMap<String,String> hashMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.listView);

        final  TextView textView = findViewById(R.id.header_title);

        String url = "https://blazeincorporation.com/class-work/emp_view.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                try {
                    for (int x =0; x<response.length();x++){
                        JSONObject jsonObject = response.getJSONObject(x);
                      String name=  jsonObject.getString("Employee Name");
                        String desi=  jsonObject.getString("Designation");
                        String depa=  jsonObject.getString("Department");

                        textView.append(name+"\n"+desi+"\n"+depa);




                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        requestQueue.add(jsonArrayRequest);









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


            HashMap<String , String>    hashMap = arrayList.get(position);

            String name = hashMap.get("Name");
            String des = hashMap.get("Des");
            String dep = hashMap.get("Dep");

            tvName.setText(name);
            tvDes.setText(des);
            tvDep.setText(dep);

            return myView;
        }
    }






}