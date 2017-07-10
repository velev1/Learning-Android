package com.example.velev.phonebook.views.details;


import dagger.Module;
import dagger.Provides;

@Module
public class DetailsContactsModule {

    @Provides
    DetailsContactContract.View provideView() {
        return new DetailsContact();
    }

    @Provides
    DetailsContactContract.Presenter providePresenter(DetailsContactContract.View view) {
        return new DetailsPresenter(view);
    }

}
