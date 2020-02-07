package com.techmesystem.intera.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techmesystem.intera.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {
    private clickItemInterface itemInterface;
    private Context mContext;
    private ArrayList<String> categoryModels_all;

    public HomeProductAdapter(Context mContext, ArrayList<String> models) {
        this.mContext = mContext;
        this.categoryModels_all = models;
    }

    public void itemInterfaceClicked(clickItemInterface clicked) {
        this.itemInterface = clicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_product_recycler_design, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return categoryModels_all == null ? 0 : categoryModels_all.size();
        return 11;
    }

    public interface clickItemInterface {

        void clickItem(String categoryID, String title);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
