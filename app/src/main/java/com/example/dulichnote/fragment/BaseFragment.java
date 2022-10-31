package com.example.dulichnote.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.dulichnote.callback.IMainCallBack;

public abstract class BaseFragment<T extends ViewBinding, M extends ViewModel> extends Fragment implements View.OnClickListener {

 protected T binding;
 protected M viewModel;

 protected Context context;

 protected Object mData;

 protected IMainCallBack callBack;


    public void setmData(Object mData) {
        this.mData = mData;
    }

    public void setCallBack(IMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public final  void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = initViewBinding(inflater);
       viewModel = new ViewModelProvider(this).get(getClassVM());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<M> getClassVM();

    protected abstract T initViewBinding(LayoutInflater inflater);

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
    }


}
