package com.techmesystem.intera.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.techmesystem.intera.Adapter.CasmaticProductAdapter;
import com.techmesystem.intera.Adapter.HomeCategoryAdapter;
import com.techmesystem.intera.Adapter.HomeProductAdapter;
import com.techmesystem.intera.Adapter.HomeProductTwoAdapter;
import com.techmesystem.intera.Adapter.HomeServicesAdapter;
import com.techmesystem.intera.Model.HomeDataModel;
import com.techmesystem.intera.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private Activity mActivity;
    private RecyclerView categoryRecycler, productRecycler, productTwoRecycler, homeCervicesRecycler, casmaticRecycler;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View hView = inflater.inflate(R.layout.home_fragment_design, container, false);

        categoryRecycler = hView.findViewById(R.id.categoryRecycler_ids);
        productRecycler = hView.findViewById(R.id.productRecycler_ids);
        productTwoRecycler = hView.findViewById(R.id.productTwoRecycler_ids);
        homeCervicesRecycler = hView.findViewById(R.id.homeCervicesRecycler_ids);
        casmaticRecycler = hView.findViewById(R.id.casmaticRecycler_ids);

        return hView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<HomeDataModel> homeDataModels_all = getCategoryALL();

        categoryRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        categoryRecycler.hasFixedSize();
        categoryRecycler.setNestedScrollingEnabled(false);

        HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(mContext, homeDataModels_all);
        categoryRecycler.setAdapter(homeCategoryAdapter);

        ArrayList<String> arrayListP = new ArrayList<>();
        productRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        productRecycler.hasFixedSize();
        productRecycler.setNestedScrollingEnabled(false);

        HomeProductAdapter homeProductAdapter = new HomeProductAdapter(mContext, arrayListP);
        productRecycler.setAdapter(homeProductAdapter);

        ArrayList<String> arrayListP2 = new ArrayList<>();
        productTwoRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        productTwoRecycler.hasFixedSize();
        productTwoRecycler.setNestedScrollingEnabled(false);

        HomeProductTwoAdapter homeProductTwoAdapter = new HomeProductTwoAdapter(mContext, arrayListP2);
        productTwoRecycler.setAdapter(homeProductTwoAdapter);

        ArrayList<HomeDataModel> arrayListS =getServicesALL();
        homeCervicesRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        homeCervicesRecycler.hasFixedSize();
        homeCervicesRecycler.setNestedScrollingEnabled(false);

        HomeServicesAdapter homeServicesAdapter = new HomeServicesAdapter(mContext, arrayListS);
        homeCervicesRecycler.setAdapter(homeServicesAdapter);

        ArrayList<String> arrayListC = new ArrayList<>();
        casmaticRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        casmaticRecycler.hasFixedSize();
        casmaticRecycler.setNestedScrollingEnabled(false);

        CasmaticProductAdapter casmaticProductAdapter = new CasmaticProductAdapter(mContext, arrayListC);
        casmaticRecycler.setAdapter(casmaticProductAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private ArrayList<HomeDataModel> getCategoryALL() {
        ArrayList<HomeDataModel> homeDataModels_all = new ArrayList<>();

        HomeDataModel homeDataModel1 = new HomeDataModel();
        homeDataModel1.setPicture(R.drawable.pic1);
        homeDataModel1.setName("Medicamentos");
        homeDataModels_all.add(homeDataModel1);

        HomeDataModel homeDataModel2 = new HomeDataModel();
        homeDataModel2.setPicture(R.drawable.pic2);
        homeDataModel2.setName("Autocuidado");
        homeDataModels_all.add(homeDataModel2);

        HomeDataModel homeDataModel3 = new HomeDataModel();
        homeDataModel3.setPicture(R.drawable.pic3);
        homeDataModel3.setName("Higiene e Cuidado Pessoal");
        homeDataModels_all.add(homeDataModel3);

        HomeDataModel homeDataModel4 = new HomeDataModel();
        homeDataModel4.setPicture(R.drawable.pic4);
        homeDataModel4.setName("Beleza");
        homeDataModels_all.add(homeDataModel4);

        HomeDataModel homeDataModel11 = new HomeDataModel();
        homeDataModel11.setPicture(R.drawable.pic1);
        homeDataModel11.setName("Medicamentos");
        homeDataModels_all.add(homeDataModel11);

        HomeDataModel homeDataModel22 = new HomeDataModel();
        homeDataModel22.setPicture(R.drawable.pic2);
        homeDataModel22.setName("Autocuidado");
        homeDataModels_all.add(homeDataModel22);

        HomeDataModel homeDataModel33 = new HomeDataModel();
        homeDataModel33.setPicture(R.drawable.pic3);
        homeDataModel33.setName("Higiene e Cuidado Pessoal");
        homeDataModels_all.add(homeDataModel33);

        HomeDataModel homeDataModel44 = new HomeDataModel();
        homeDataModel44.setPicture(R.drawable.pic4);
        homeDataModel44.setName("Beleza");
        homeDataModels_all.add(homeDataModel44);

        return homeDataModels_all;
    }

    private ArrayList<HomeDataModel> getServicesALL() {
        ArrayList<HomeDataModel> homeDataModels_all = new ArrayList<>();

        HomeDataModel homeDataModel1 = new HomeDataModel();
        homeDataModel1.setPicture(R.drawable.alarm);
        homeDataModel1.setName("Acompanhar\ntratamento");
        homeDataModels_all.add(homeDataModel1);

        HomeDataModel homeDataModel2 = new HomeDataModel();
        homeDataModel2.setPicture(R.drawable.group_icon);
        homeDataModel2.setName("Carteira\nDigital");
        homeDataModels_all.add(homeDataModel2);

        HomeDataModel homeDataModel3 = new HomeDataModel();
        homeDataModel3.setPicture(R.drawable.win);
        homeDataModel3.setName("Ganhadores\nSorteios");
        homeDataModels_all.add(homeDataModel3);

        HomeDataModel homeDataModel4 = new HomeDataModel();
        homeDataModel4.setPicture(R.drawable.assignment);
        homeDataModel4.setName("Meus\npedidos");
        homeDataModels_all.add(homeDataModel4);

        HomeDataModel homeDataModel11 = new HomeDataModel();
        homeDataModel11.setPicture(R.drawable.alarm);
        homeDataModel11.setName("Acompanhar\ntratamento");
        homeDataModels_all.add(homeDataModel11);

        HomeDataModel homeDataModel22 = new HomeDataModel();
        homeDataModel22.setPicture(R.drawable.group_icon);
        homeDataModel22.setName("Carteira\nDigital");
        homeDataModels_all.add(homeDataModel22);

        HomeDataModel homeDataModel33 = new HomeDataModel();
        homeDataModel33.setPicture(R.drawable.win);
        homeDataModel33.setName("Ganhadores\nSorteios");
        homeDataModels_all.add(homeDataModel33);

        HomeDataModel homeDataModel44 = new HomeDataModel();
        homeDataModel44.setPicture(R.drawable.assignment);
        homeDataModel44.setName("Meus\npedidos");
        homeDataModels_all.add(homeDataModel44);

        return homeDataModels_all;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View rView) {
        switch (rView.getId()) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}