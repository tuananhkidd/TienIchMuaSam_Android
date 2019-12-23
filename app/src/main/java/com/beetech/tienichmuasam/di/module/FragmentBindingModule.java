package com.beetech.tienichmuasam.di.module;


import com.beetech.tienichmuasam.ui.home.HomeFragment;
import com.beetech.tienichmuasam.ui.home.chat.ChatFragment;
import com.beetech.tienichmuasam.ui.home.dashboard.NewFeedsFragment;
import com.beetech.tienichmuasam.ui.home.search.SearchFragment;
import com.beetech.tienichmuasam.ui.list_product.ListProductFragment;
import com.beetech.tienichmuasam.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    //TODO bind fragment
    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract NewFeedsFragment bindNewFeedsFragment();

    @ContributesAndroidInjector
    abstract SearchFragment bindSearchFragment();

    @ContributesAndroidInjector
    abstract ListProductFragment bindListProductFragment();

    @ContributesAndroidInjector
    abstract ChatFragment bindChatFragment();
}
