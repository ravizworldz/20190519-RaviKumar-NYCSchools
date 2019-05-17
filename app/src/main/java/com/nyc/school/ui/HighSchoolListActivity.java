package com.nyc.school.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nyc.school.R;
import com.nyc.school.model.SchoolDetailModel;
import com.nyc.school.model.SchoolListModel;
import com.nyc.school.network.APIInterface;
import com.nyc.school.network.NetworkClient;
import com.nyc.school.presenter.ISchooListResponse;
import com.nyc.school.presenter.ISchoolDetailsResponse;
import com.nyc.school.presenter.ISchoolListPresenter;
import com.nyc.school.presenter.SchoolListPresenter;
import com.nyc.school.ui.adapter.ItemClickListener;
import com.nyc.school.ui.adapter.SchoolListAdapter;
import com.nyc.school.utils.LogUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class HighSchoolListActivity extends AppCompatActivity {


    private ProgressDialog progressDoalog;
    private SchoolListAdapter schoolListAdapter;
    private RecyclerView schoolListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school_list);

        getSchoolList();
    }

    private void getSchoolList() {
        SchoolListPresenter listPresenter = new SchoolListPresenter();
        listPresenter.setListResponseListener(new ISchooListResponse() {
            @Override
            public void onSuccess(List<SchoolListModel> schoolListModels) {
                getSchoolDetails(schoolListModels);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(HighSchoolListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showProgressDialog() {
                showProgress();
            }

            @Override
            public void hideProgressDialog() {
                hideProgress();
            }
        });
        listPresenter.getSchoolList();
    }

    private void getSchoolDetails(final List<SchoolListModel> schoolListModels) {
        SchoolListPresenter detailsPresenter = new SchoolListPresenter();
        detailsPresenter.setDetailsResponseListener(new ISchoolDetailsResponse() {
            @Override
            public void onSuccess(List<SchoolDetailModel> schoolDetailsModels) {
               showSchoolList(schoolListModels, schoolDetailsModels);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(HighSchoolListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showProgressDialog() {
                showProgress();
            }

            @Override
            public void hideProgressDialog() {
                hideProgress();
            }
        });
        detailsPresenter.getSATList();
    }


    /*Method to generate List of data using RecyclerView with school list adapter*/
    private void showSchoolList(final List<SchoolListModel> schoolList, final List<SchoolDetailModel>  detailsList) {
        schoolListRecyclerView = findViewById(R.id.schooListRecyclerView);
        schoolListAdapter = new SchoolListAdapter(this, schoolList, detailsList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
                int position = viewHolder.getAdapterPosition();
                LogUtil.log("HighSchoolListActivity", "item clicked position " + position);
                SchoolListModel schoolListModel = schoolList.get(position);
                String  dbn = schoolListModel.getDbn();
                LogUtil.log("HighSchoolListActivity", "item clicked dbn " + dbn);
                int i = 0;
                SchoolDetailModel detailModel = null;
                for(i =0; i<detailsList.size(); i++) {
                    detailModel  = detailsList.get(i);

                    if(dbn.equals(detailModel.getDbn())){
                        break;
                    }
                }
                Intent intent = new Intent(HighSchoolListActivity.this, SchoolDetailsActivity.class);
                intent.putExtra("detailModel", detailModel);
                intent.putExtra("phone", schoolListModel.getPhone_number());
                intent.putExtra("address", schoolListModel.getLocation());
                intent.putExtra("description",schoolListModel.getAcademicopportunities1()+" "+ schoolListModel.getAcademicopportunities2());
                intent.putExtra("website", schoolListModel.getWebsite());

                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HighSchoolListActivity.this);
        schoolListRecyclerView.setLayoutManager(layoutManager);
        schoolListRecyclerView.setAdapter(schoolListAdapter);
    }

    private void showProgress() {
        progressDoalog = new ProgressDialog(HighSchoolListActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }
    private void hideProgress() {
        if(progressDoalog != null && progressDoalog.isShowing()) {
            progressDoalog.dismiss();
        }
    }
}
