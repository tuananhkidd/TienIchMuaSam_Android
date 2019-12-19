package com.beetech.tienichmuasam.di.module;


import com.beetech.tienichmuasam.ui.home.HomeFragment;
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
}
