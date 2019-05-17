package com.nyc.school.presenter;

import android.util.Log;

import com.nyc.school.model.SchoolDetailModel;
import com.nyc.school.model.SchoolListModel;
import com.nyc.school.network.APIInterface;
import com.nyc.school.network.NetworkClient;
import com.nyc.school.utils.LogUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolListPresenter implements ISchoolListPresenter {
    private APIInterface apiInterface;
    private ISchooListResponse listResponse;
    private ISchoolDetailsResponse detailsResponse;

    public void setListResponseListener(ISchooListResponse listResponse){
        this.listResponse = listResponse;
    }

    public void setDetailsResponseListener(ISchoolDetailsResponse detailsResponse){
        this.detailsResponse  = detailsResponse;
    }

    @Override
    public void getSchoolList() {
        getSchoolListData();
    }

    @Override
    public void getSATList() {
        getSchoolDetailsData();
    }


    private void getSchoolListData() {
        Log.e("TAG","getSchoolListData called");
        apiInterface = NetworkClient.getClient().create(APIInterface.class);

        this.listResponse.showProgressDialog();

        Call<List<SchoolListModel>> call = apiInterface.doGetSchoolList();

        call.enqueue(new Callback<List<SchoolListModel>>() {
            @Override
            public void onResponse(Call<List<SchoolListModel>> call, Response<List<SchoolListModel>> response) {
                LogUtil.log("TAG","getSchoolListData onResponse called");

                LogUtil.log("TAG",response.code()+"");
                LogUtil.log("TAG",response.body()+"");
                listResponse.hideProgressDialog();
                //showSchoolList(response.body());
                listResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<SchoolListModel>> call, Throwable t) {
                LogUtil.log("TAG","getSchoolListData onFailure called " + t.toString());
                call.cancel();
                listResponse.onFailure(t.getMessage());
                listResponse.hideProgressDialog();
            }
        });
    }

    private void getSchoolDetailsData() {
        Log.e("TAG","getSchoolDetailsData called");
        apiInterface = NetworkClient.getClient().create(APIInterface.class);

        this.detailsResponse.showProgressDialog();

        Call<List<SchoolDetailModel>> call = apiInterface.doGetSchoolDetails();

        call.enqueue(new Callback<List<SchoolDetailModel>>() {
            @Override
            public void onResponse(Call<List<SchoolDetailModel>> call, Response<List<SchoolDetailModel>> response) {
                LogUtil.log("TAG","getSchoolDetailsData onResponse called");

                LogUtil.log("TAG",response.code()+"");
                LogUtil.log("TAG",response.body()+"");
                detailsResponse.hideProgressDialog();
                //showSchoolList(response.body());
                detailsResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<SchoolDetailModel>> call, Throwable t) {
                LogUtil.log("TAG","getSchoolListData onFailure called " + t.toString());
                call.cancel();
                detailsResponse.onFailure(t.getMessage());
                detailsResponse.hideProgressDialog();
            }
        });
    }

}
