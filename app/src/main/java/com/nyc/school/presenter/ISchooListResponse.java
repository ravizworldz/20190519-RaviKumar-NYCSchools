package com.nyc.school.presenter;

import com.nyc.school.model.SchoolListModel;

import java.util.List;

public interface ISchooListResponse {

    public void onSuccess(List<SchoolListModel> schoolListModels);
    public void onFailure(String errorMessage);
    public void showProgressDialog();
    public void hideProgressDialog();
}
