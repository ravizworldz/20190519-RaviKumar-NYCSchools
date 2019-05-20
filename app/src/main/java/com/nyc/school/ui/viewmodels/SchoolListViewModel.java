package com.nyc.school.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.nyc.school.model.SchoolListModel;
import com.nyc.school.presenter.ISchooListResponse;
import com.nyc.school.presenter.SchoolListPresenter;
import com.nyc.school.ui.HighSchoolListActivity;

import java.util.List;

public class SchoolListViewModel extends AndroidViewModel {

    private List<SchoolListModel> schoolListModels;

    public SchoolListViewModel(@NonNull Application application) {
        super(application);
    }


    public void getSchoolList(final ISchooListResponse responseListener) {

            if(schoolListModels != null) {
                responseListener.onSuccess(schoolListModels);
                return;
            }

            SchoolListPresenter listPresenter = new SchoolListPresenter();
            listPresenter.setListResponseListener(new ISchooListResponse() {
                @Override
                public void onSuccess(List<SchoolListModel> schoolList) {
             //       getSchoolDetails(schoolListModels);
                    schoolListModels =  schoolList;
                    responseListener.onSuccess(schoolList);
                }

                @Override
                public void onFailure(String errorMessage) {
               //     Toast.makeText(HighSchoolListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    responseListener.onFailure(errorMessage);
                }

                @Override
                public void showProgressDialog() {
                 //   showProgress();
                    responseListener.showProgressDialog();
                }

                @Override
                public void hideProgressDialog() {
                   // hideProgress();
                    responseListener.hideProgressDialog();
                }
            });
            listPresenter.getSchoolList();

    }
}
