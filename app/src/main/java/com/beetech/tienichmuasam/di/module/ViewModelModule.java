package com.beetech.tienichmuasam.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.beetech.tienichmuasam.di.ViewModelFactory;
import com.beetech.tienichmuasam.ui.cart.CartViewModel;
import com.beetech.tienichmuasam.ui.home.HomeViewModel;
import com.beetech.tienichmuasam.ui.home.chat.ChatViewModel;
import com.beetech.tienichmuasam.ui.home.dashboard.NewFeedsViewModel;
import com.beetech.tienichmuasam.ui.home.search.SearchViewModel;
import com.beetech.tienichmuasam.ui.list_product.ListProductViewModel;
import com.beetech.tienichmuasam.ui.main.MainViewModel;
import com.beetech.tienichmuasam.ui.product.DetaillProductViewModel;
import com.beetech.tienichmuasam.ui.profile.PersonViewModel;
import com.beetech.tienichmuasam.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    //bind ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewFeedsViewModel.class)
    abstract ViewModel bindNewFeedsViewModel(NewFeedsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListProductViewModel.class)
    abstract ViewModel bindListProductViewModel(ListProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel.class)
    abstract ViewModel bindChatViewModel(ChatViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetaillProductViewModel.class)
    abstract ViewModel bindDetailProductViewModel(DetaillProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindCartViewModel(CartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModel.class)
    abstract ViewModel bindPersonViewModel(PersonViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
