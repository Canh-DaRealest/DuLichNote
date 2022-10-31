package com.example.dulichnote.fragment;



import android.view.LayoutInflater;

import com.example.dulichnote.databinding.FragmentMainBinding;
import com.example.dulichnote.viewmodel.CommonViewModel;


public class MainFragment extends BaseFragment<FragmentMainBinding, CommonViewModel> {




    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected FragmentMainBinding initViewBinding(LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }
}