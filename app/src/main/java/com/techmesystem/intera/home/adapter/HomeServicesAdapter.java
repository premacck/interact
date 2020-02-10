package com.techmesystem.intera.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techmesystem.intera.R;
import com.techmesystem.intera.home.model.HomeDataModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeServicesAdapter extends RecyclerView.Adapter<HomeServicesAdapter.ViewHolder> {
    private clickItemInterface itemInterface;
    private Context mContext;
    private ArrayList<HomeDataModel> categoryModels_all;

    public HomeServicesAdapter(Context mContext, ArrayList<HomeDataModel> models) {
        this.mContext = mContext;
        this.categoryModels_all = models;
    }

    public void itemInterfaceClicked(clickItemInterface clicked) {
        this.itemInterface = clicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_services_recycler_design, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeDataModel homeDataModel = categoryModels_all.get(position);

        holder.servicesPic.setImageResource(homeDataModel.getPicture());
        holder.servicesTv.setText(homeDataModel.getName());
    }

    @Override
    public int getItemCount() {
        return categoryModels_all == null ? 0 : categoryModels_all.size();
        //return 11;
    }

    public interface clickItemInterface {

        void clickItem(String categoryID, String title);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView servicesPic;
        TextView servicesTv;


        ViewHolder(View itemView) {
            super(itemView);

            servicesPic = itemView.findViewById(R.id.servicesPic_ids);
            servicesTv = itemView.findViewById(R.id.servicesTv_ids);
        }
    }
}
