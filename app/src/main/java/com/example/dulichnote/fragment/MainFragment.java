package com.example.dulichnote.fragment;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.dulichnote.MapManager;
import com.example.dulichnote.R;
import com.example.dulichnote.databinding.FragmentMainBinding;
import com.example.dulichnote.model.PlaceItem;
import com.example.dulichnote.viewmodel.MainVM;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


public class MainFragment extends BaseFragment<FragmentMainBinding, MainVM> implements MapManager.OnMarkerCallback {


    public static final String TAG = MainFragment.class.getName();

    public static final String HOME = "HOME";
    public static final String MAP = "MAP";
    public static final String SETTING = "SETTING";

    private String currentFrg = HOME;


    @Override
    protected void initViews() {
        binding.trMap.setVisibility(View.GONE);


        try {

            viewModel.loadStory(context.getAssets().open("danhlam.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        setClickMenuItem();
        showChildFrg(ListPlaceFragment.TAG, viewModel.getListPlace());
        callBack.checkMapPermission();

    }

    private void showMyLocation() {
        MapManager.getInstance().forceShowMyLocation();
    }

    private void showAllPlace(List<PlaceItem> listPlace) {
        MapManager.getInstance().showAllPlace(listPlace);
    }

    private void setClickMenuItem() {

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        if (!currentFrg.equals(HOME)) {
                            showChildFrg(ListPlaceFragment.TAG, viewModel.getListPlace());
                            binding.ivPlace.setVisibility(View.GONE);
                            currentFrg = HOME;
                            return true;
                        }
                        return false;

                    case R.id.map:
                        if (!currentFrg.equals(MAP)) {

                            showMapFrg();
                            currentFrg = MAP;
                            return true;

                        }
                        return false;

                    case R.id.setting:
                        Toast.makeText(context, "ccc" +
                                "", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
    }

    private void showMapFrg() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fr_main, mapFragment, mapFragment.getClass().getName());
        fragmentTransaction.commit();
        mapFragment.getMapAsync(googleMap -> {
            MapManager.getInstance().initMap(context, googleMap);
            MapManager.getInstance().setCallback(this);
        });
        binding.trMap.setVisibility(View.VISIBLE);
        binding.myPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyLocation();
            }
        });
        binding.ivPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllPlace(viewModel.getListPlace());
            }
        });
    }


    private void showChildFrg(String tag, Object data) {
        try {
            Class<?> classInstance = Class.forName(tag);

            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) classInstance.newInstance();

            fragment.setmData(data);

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fr_main, fragment, tag);
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

    @Override
    public void markerCallBack(PlaceItem item) {
        Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show();
    }
}