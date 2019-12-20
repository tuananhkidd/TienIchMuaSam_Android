package com.beetech.tienichmuasam.ui.home.search;


import androidx.fragment.app.Fragment;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.FragmentSearchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private SearchViewModel searchViewModel;

    public SearchFragment() {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
