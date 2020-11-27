package com.example.database;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    sqlLite db =new sqlLite(this);
    TextView s = findViewById(R.id.searchOut);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.delete,R.id.insert,R.id.search,R.id.display,R.id.modify)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }


    public void search(View view) {
        EditText n = findViewById(R.id.sName);
        String name = n.getText().toString();
      ArrayList se=  db.Select(name);
      
      s.setText((CharSequence) se);

    }
    public void insert(View view) {

        EditText n = findViewById(R.id.inName);
        String name = n.getText().toString();

        EditText s = findViewById(R.id.inSex);
        String sex = s.getText().toString();

        EditText r = findViewById(R.id.inRate);
        String ru=r.getText().toString();

        EditText b = findViewById(R.id.inBase_Salary);
        String bs =b.getText().toString();

        EditText t = findViewById(R.id.inTotal_Salary);
        String ts =t.getText().toString();

        if(!name.isEmpty()&&!sex.isEmpty()&&!ru.isEmpty()&&!bs.isEmpty()&&!ts.isEmpty()) {
            float Base_Salary = Float.parseFloat(bs);
            float Total_Salary = Float.parseFloat(ts);
            float Rate = Float.parseFloat(ru);


            Boolean res = db.insert(name, sex, Base_Salary, Total_Salary, Rate);
            if (res)
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "ERORe", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "please enter value of all row", Toast.LENGTH_SHORT).show();
        }

    }


    public void modify(View view) {

        EditText n = findViewById(R.id.mdName);
        String name = n.getText().toString();


        EditText s = findViewById(R.id.mdSex);
        String sex = s.getText().toString();

        EditText r = findViewById(R.id.mdRate);
        String ru=r.getText().toString();


        EditText b = findViewById(R.id.mdBase_Salary);
        String bs =b.getText().toString();



        EditText t = findViewById(R.id.mdTotal_Salary);
        String ts =t.getText().toString();


        EditText i = findViewById(R.id.mdid);
        String id =i.getText().toString();

        if(!id.isEmpty()&&!name.isEmpty()&&!sex.isEmpty()&&!ru.isEmpty()&&!bs.isEmpty()&&!ts.isEmpty()) {
            float Base_Salary = Float.parseFloat(bs);
            float Total_Salary = Float.parseFloat(ts);
            float Rate = Float.parseFloat(ru);

            Boolean res = db.modify(id,name, sex, Base_Salary, Total_Salary, Rate);
            if(res)
                Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "not fount id", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "please enter value of all row", Toast.LENGTH_SHORT).show();
        }

    }
    public void delete(View view) {
        EditText i = findViewById(R.id.dlid);
        String id =i.getText().toString();
        if(!id.isEmpty()) {

            int res = db.delete(id);
            if(res==1){
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), "error ", Toast.LENGTH_SHORT).show();

            }

        }else {

            Toast.makeText(getApplicationContext(), "please enter id to delete", Toast.LENGTH_SHORT).show();
        }

    }

}