package com.beetech.tienichmuasam.di;

import android.app.Application;


import com.beetech.tienichmuasam.BaseApplication;
import com.beetech.tienichmuasam.di.module.ActivityBindingModule;
import com.beetech.tienichmuasam.di.module.AppModule;
import com.beetech.tienichmuasam.di.module.FragmentBindingModule;
import com.beetech.tienichmuasam.di.module.NetworkModule;
import com.beetech.tienichmuasam.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
