package com.example.dulichnote.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.dulichnote.R;
import com.example.dulichnote.callback.IMainCallBack;
import com.example.dulichnote.fragment.BaseFragment;

public abstract class BaseActivity<T extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements IMainCallBack, View.OnClickListener {

    protected T binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(getClassVM());
        setContentView(binding.getRoot());
        initViews();

    }

    protected abstract void initViews();

    protected abstract Class<M> getClassVM();

    protected abstract T initViewBinding();


    @Override
    public void showFrg(String tag, Object data, boolean isBacked) {

        try {
            Class<?> instance = Class.forName(tag);
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) instance.newInstance();

            fragment.setCallBack(this);
            fragment.setmData(data);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fr_container, fragment, tag);

            if (isBacked) {
                fragmentTransaction.addToBackStack(tag);
            }

            fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
    }
}
