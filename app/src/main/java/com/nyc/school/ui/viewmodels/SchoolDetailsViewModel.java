package com.nyc.school.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.ArrayMap;
import android.widget.Toast;

import com.nyc.school.model.SchoolDetailModel;
import com.nyc.school.model.SchoolListModel;
import com.nyc.school.presenter.ISchooListResponse;
import com.nyc.school.presenter.ISchoolDetailsResponse;
import com.nyc.school.presenter.SchoolListPresenter;
import com.nyc.school.ui.HighSchoolListActivity;

import java.util.List;

public class SchoolDetailsViewModel extends AndroidViewModel {

    private ArrayMap<String, SchoolDetailModel> schoolDetailsModels;

    public SchoolDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSchoolDetails(final ISchoolDetailsResponse responseListener) {
        if(schoolDetailsModels != null ){
            responseListener.onSuccess(schoolDetailsModels);
            return;
        }
        SchoolListPresenter detailsPresenter = new SchoolListPresenter();
        detailsPresenter.setDetailsResponseListener(new ISchoolDetailsResponse() {
            @Override
            public void onSuccess(ArrayMap<String, SchoolDetailModel> schoolDetails) {
                //showSchoolList(schoolListModels, schoolDetailsModels);
                schoolDetailsModels = schoolDetails;
                responseListener.onSuccess(schoolDetailsModels);
            }

            @Override
            public void onFailure(String errorMessage) {
                //Toast.makeText(HighSchoolListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                responseListener.onFailure(errorMessage);
            }

            @Override
            public void showProgressDialog() {
                //showProgress();
                responseListener.showProgressDialog();
            }

            @Override
            public void hideProgressDialog() {
                //hideProgress();
                responseListener.hideProgressDialog();
            }
        });
        detailsPresenter.getSATList();
    }

}
