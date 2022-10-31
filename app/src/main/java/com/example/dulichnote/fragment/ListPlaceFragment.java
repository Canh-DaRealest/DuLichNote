package com.example.dulichnote.fragment;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dulichnote.adapter.PlaceAdapter;
import com.example.dulichnote.databinding.FragmentSplashBinding;
import com.example.dulichnote.databinding.ListPlaceBinding;
import com.example.dulichnote.model.PlaceItem;
import com.example.dulichnote.viewmodel.CommonViewModel;

import java.util.List;


public class ListPlaceFragment extends BaseFragment<ListPlaceBinding, CommonViewModel> {


    public static final String TAG = ListPlaceFragment.class.getName();

    @Override
    protected void initViews() {

        List<PlaceItem> listData = (List<PlaceItem>) mData;


        PlaceAdapter placeAdapter = new PlaceAdapter(listData, context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.vpPlace.setAdapter(placeAdapter);

    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected ListPlaceBinding initViewBinding(LayoutInflater inflater) {
        return ListPlaceBinding.inflate(inflater);
    }
}