package com.example.velev.eventreminder.views.eventDetailsViewTest;

import io.reactivex.Observable;

public interface EventDetailsContract {

    interface View {

    }

    interface ViewModel {

        Observable<String> setTimer(final long timeInMilliSeconds);

    }
}
