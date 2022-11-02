package com.example.dulichnote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

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
    public void checkMapPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please acept permission", Toast.LENGTH_SHORT).show();
            return;
        }
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