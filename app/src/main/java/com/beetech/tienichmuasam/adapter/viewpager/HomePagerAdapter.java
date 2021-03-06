package com.beetech.tienichmuasam.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.ui.home.chat.ChatFragment;
import com.beetech.tienichmuasam.ui.home.dashboard.NewFeedsFragment;
import com.beetech.tienichmuasam.ui.home.search.SearchFragment;
import com.beetech.tienichmuasam.ui.profile.PersonFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments.add(new NewFeedsFragment());
        fragments.add(new SearchFragment());
        fragments.add(new SearchFragment());
        fragments.add(new ChatFragment());
        fragments.add(new PersonFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
