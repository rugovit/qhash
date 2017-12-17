package com.rugovit.qhash.workflow.charts;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rugovit.qhash.R;
import com.rugovit.qhash.base_classes.view.BaseFragment;
import com.rugovit.qhash.databinding.ChartFragmentLayoutBinding;

/**
 * Created by rugovit on 12/13/2017.
 */

public class ChartFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       ChartFragmentLayoutBinding binding =ChartFragmentLayoutBinding.inflate(
                inflater, container, false);
        binding.setChart(new ChartViewModel(ChartRepository.getInstance()));
        View layout=binding.getRoot();
        layout.findViewById()
        return  layout;
    }
}
