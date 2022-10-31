package com.example.dulichnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dulichnote.R;
import com.example.dulichnote.databinding.ActivityMainBinding;
import com.example.dulichnote.fragment.SplashFragment;
import com.example.dulichnote.viewmodel.CommonViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, CommonViewModel> {


    @Override
    protected void initViews() {
showFrg(SplashFragment.TAG, null, false);
    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}