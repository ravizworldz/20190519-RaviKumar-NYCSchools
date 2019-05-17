package com.nyc.school.presenter;

import com.nyc.school.model.SchoolDetailModel;

import java.util.List;

public interface ISchoolDetailsResponse {
    public void onSuccess(List<SchoolDetailModel> detailModelList);
    public void onFailure(String errorMessage);
    public void showProgressDialog();
    public void hideProgressDialog();
}
