package com.example.velev.phonebook.views.tabs.dialer;

import dagger.Module;
import dagger.Provides;

@Module
public class DialerModule {

    @Provides
    DialerContract.View provideView() {
        return new TabDialer();
    }

    @Provides
    DialerContract.Presenter providePresenter(DialerContract.View view) {
        return new DialerPresenter(view);
    }
}
