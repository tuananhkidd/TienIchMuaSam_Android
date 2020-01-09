package com.beetech.tienichmuasam.di.module;

import com.beetech.tienichmuasam.di.RxPreference;
import com.beetech.tienichmuasam.di.RxPreferenceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    @Provides
    @Singleton
    RxPreference provideRxPreference(RxPreferenceImpl rxPreference) {
        return rxPreference;
    }
}
