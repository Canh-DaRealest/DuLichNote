package com.example.dulichnote.fragment;


import android.os.Handler;
import android.view.LayoutInflater;

import com.example.dulichnote.databinding.FragmentMainBinding;
import com.example.dulichnote.databinding.FragmentSplashBinding;
import com.example.dulichnote.viewmodel.CommonViewModel;


public class SplashFragment extends BaseFragment<FragmentSplashBinding, CommonViewModel> {


    public static final String TAG = SplashFragment.class.getName();

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.showFrg(MainFragment.TAG, null, false);
            }
        },2000);

    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected FragmentSplashBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSplashBinding.inflate(inflater);
    }
}