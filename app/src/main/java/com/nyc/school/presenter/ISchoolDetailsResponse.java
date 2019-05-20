package com.nyc.school.presenter;

import android.util.ArrayMap;

import com.nyc.school.model.SchoolDetailModel;

import java.util.List;
import java.util.Map;

public interface ISchoolDetailsResponse {
    public void onSuccess(ArrayMap<String, SchoolDetailModel> detailModelList);
    public void onFailure(String errorMessage);
    public void showProgressDialog();
    public void hideProgressDialog();
}
