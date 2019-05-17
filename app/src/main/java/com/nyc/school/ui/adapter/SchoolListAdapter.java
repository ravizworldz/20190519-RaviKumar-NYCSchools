package com.nyc.school.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.nyc.school.R;
import com.nyc.school.model.SchoolDetailModel;
import com.nyc.school.model.SchoolListModel;
import com.nyc.school.utils.LogUtil;

import java.util.List;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.CustomViewHolder> {

    private List<SchoolListModel> dataList;
    private List<SchoolDetailModel> detailsList;
    private View.OnClickListener  clickListener;
    private Context context;

    public SchoolListAdapter(Context context, List<SchoolListModel> dataList, List<SchoolDetailModel> detailsList, View.OnClickListener  listener){
        this.context = context;
        this.clickListener = listener;
        this.dataList = dataList;
        this.detailsList = detailsList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

       // private final View mView;
        private TextView txtTitle, txtAddress, txtPhone;

        CustomViewHolder(View itemView) {
            super(itemView);
          //  mView = itemView;

            txtTitle = itemView.findViewById(R.id.title);
            txtAddress = itemView.findViewById(R.id.address);
            txtPhone = itemView.findViewById(R.id.phone);
            itemView.setTag(this);
            itemView.setOnClickListener(clickListener);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.txtAddress.setText("Address : " +dataList.get(position).getLocation());
        holder.txtPhone.setText("Phone : " +dataList.get(position).getPhone_number());

        String  dbn = dataList.get(position).getDbn();
        int i = 0;
        SchoolDetailModel detailModel = null;
        for(i =0; i<detailsList.size(); i++) {
            detailModel  = detailsList.get(i);
            if(dbn.equals(detailModel.getDbn())){
                break;
            }
        }
        holder.txtTitle.setText(detailModel.getSchool_name());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
