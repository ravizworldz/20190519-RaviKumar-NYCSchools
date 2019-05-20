package com.nyc.school.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nyc.school.R;
import com.nyc.school.constants.AppConstants;
import com.nyc.school.model.SchoolDetailModel;

public class SchoolDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);

        Bundle bundle = getIntent().getExtras();
        SchoolDetailModel detailModel = bundle.getParcelable(AppConstants.SCHOOL_OBJ);
        String phone =  bundle.getString(AppConstants.SCHOOL_PHONE);
        String name =  bundle.getString(AppConstants.SCHOOL_NAME);
        String address =  bundle.getString(AppConstants.SCHOOL_ADDRESS);
        String description =  bundle.getString(AppConstants.SCHOOL_DESC);
        String website =  bundle.getString(AppConstants.SCHOOL_WEBSITE);
        setupUI(name, phone,address, description,website, detailModel);
    }

    private void setupUI(final String name, final String phone, final String address, final String description,final String website, final SchoolDetailModel detailModel){
        ((TextView) findViewById(R.id.title)).setText(name);
        ((TextView) findViewById(R.id.tv_description)).setText(description);
        ((TextView) findViewById(R.id.tv_phone)).setText("Tel: "+phone);
        ((TextView) findViewById(R.id.tv_address)).setText("Address: "+address);
        ((TextView) findViewById(R.id.tv_website)).setText(website);

        if(detailModel !=null) {
            ((TextView) findViewById(R.id.tv_satreading)).setText("SAT average critical reading score : "+detailModel.getSat_critical_reading_avg_score());
            ((TextView) findViewById(R.id.tv_satmath)).setText("SAT average math score : "+detailModel.getSat_math_avg_score());
            ((TextView) findViewById(R.id.tv_satwriting)).setText("SAT average writign score : "+detailModel.getSat_writing_avg_score());

        } else {
            ((TextView) findViewById(R.id.tv_satreading)).setText("SAT average critical reading score not found.");
            ((TextView) findViewById(R.id.tv_satmath)).setText("SAT average math score not found.");
            ((TextView) findViewById(R.id.tv_satwriting)).setText("SAT average writign score not found.");
        }
    }
}
