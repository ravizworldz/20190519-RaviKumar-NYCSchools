package com.nyc.school.network;

import com.nyc.school.model.SchoolDetailModel;
import com.nyc.school.model.SchoolListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("s3k6-pzi2.json")
    Call<List<SchoolListModel>> doGetSchoolList();

    @GET("f9bf-2cp4.json")
    Call<List<SchoolDetailModel>> doGetSchoolDetails();

}
