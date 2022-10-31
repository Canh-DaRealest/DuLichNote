package com.example.dulichnote.fragment;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.dulichnote.R;
import com.example.dulichnote.databinding.FragmentMainBinding;
import com.example.dulichnote.model.PlaceItem;
import com.example.dulichnote.viewmodel.CommonViewModel;
import com.example.dulichnote.viewmodel.MainVM;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MainFragment extends BaseFragment<FragmentMainBinding, MainVM> {


    public static final String TAG = MainFragment.class.getName();

    public static final String HOME = "HOME";
    public static final String MENU = "MENU";
    public static final String SETTING = "SETTING";
    private String currentFrg =HOME ;


    @Override
    protected void initViews() {






        try {

            viewModel.loadStory(context.getAssets().open("danhlam.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        setClickMenuItem();
        showChildFrg(ListPlaceFragment.TAG, viewModel.getListPlace());

    }

    private void setClickMenuItem() {

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        if (!currentFrg.equals(HOME)) {
                            showChildFrg(ListPlaceFragment.TAG, viewModel.getListPlace());
                            currentFrg =HOME;
                            return true;
                        }


                    case R.id.menu:
                        return true;
                    case R.id.setting:
                        Toast.makeText(context, "ccc" +
                                "", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
    }

    private void showChildFrg(String tag, Object data) {
        try {
            Class<?> classInstance = Class.forName(tag);

            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) classInstance.newInstance();

            fragment.setmData(data);

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fr_main, fragment);
            fragmentTransaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<MainVM> getClassVM() {
        return MainVM.class;
    }

    @Override
    protected FragmentMainBinding initViewBinding(LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }
}