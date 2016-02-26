package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicDepartments extends Activity {

    List<Map<String, String>> departmentList = new ArrayList<Map<String,String>>();

    private void initList(){
        departmentList.add(createDepartment("department","Art"));
        departmentList.add(createDepartment("department","Biological, Environmental & Rural Sciences"));
        departmentList.add(createDepartment("department","Computer Science"));
        departmentList.add(createDepartment("department","Education and Lifelong Learning"));
        departmentList.add(createDepartment("department","English & Creative Writing"));
        departmentList.add(createDepartment("department","English Language"));
        departmentList.add(createDepartment("department","Geography & Earth Sciences"));
        departmentList.add(createDepartment("department","History & Welsh History"));
        departmentList.add(createDepartment("department","Information Studies"));
        departmentList.add(createDepartment("department","International Politics"));
        departmentList.add(createDepartment("department","Law and Criminology"));
        departmentList.add(createDepartment("department","Management & Business"));
        departmentList.add(createDepartment("department","Mathematics"));
        departmentList.add(createDepartment("department","Modern Languages"));
        departmentList.add(createDepartment("department","Physics"));
        departmentList.add(createDepartment("department","Psychology"));
        departmentList.add(createDepartment("department","Theatre, Film & Television Studies"));
        departmentList.add(createDepartment("department","Department of Welsh and Celtic Studies"));


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_departments);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        initList();

        ListView lv = (ListView) findViewById(R.id.academicdepartmentslistView);

        SimpleAdapter simpleAdpt = new SimpleAdapter(this,departmentList,android.R.layout.simple_list_item_1, new String[] {"department"}, new int[] {android.R.id.text1});

        lv.setAdapter(simpleAdpt);

    }

    private HashMap<String, String> createDepartment(String key, String name) {

        HashMap<String, String> department = new HashMap<String, String>();

        department.put(key, name);


        return department;
    }






    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }
}
