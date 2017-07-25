package com.example.velev.eventreminder.views.eventDetailsViewTest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class EventDetailsViewModel implements EventDetailsContract.ViewModel{

    @Override
    public Observable<String> setTimer(final long timeInMilliSeconds) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
                long seconds = timeInMilliSeconds / 1000;
                long currentTimeInMilliSeconds = timeInMilliSeconds;
                for (int i = 0; i < seconds + 1; i++) {
                    if (i == seconds) {
                        e.onNext("finished!");
                    } else {
                        e.onNext(convertMilliSeconds(currentTimeInMilliSeconds));
                        currentTimeInMilliSeconds -= 1000;
                    }
                    Thread.sleep(1000);
                }

                e.onComplete();
            }

        });
    }

    public String convertMilliSeconds(long milliSeconds) {

        if(milliSeconds < 0) {
            throw new IllegalArgumentException("The milliSeconds must be positive long number.");
        }

        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long months = 0;
        long years = 0;

        String result = "";

        Long totalTimeInSeconds = milliSeconds / 1000;

        seconds = totalTimeInSeconds % 60;
        minutes = (totalTimeInSeconds - seconds) / 60;
        hours = (minutes - (minutes % 60)) / 60;
        days = (hours - (hours % 24)) / 24;
        months = (days - (days % 30)) / 30;
        years = (months - (months % 12)) / 12;

        if(years > 0) {
            String strYears = years == 1 ? " year " : " years ";
            result+= String.valueOf(years) + strYears;
        }

        if (months > 0) {
            // this line is because the difference in count of the days in the month (30, 31, 28, 29)
            // the app assumes one month to be 30 days
            months = ((days - years * 5) - ((days - years * 5) % 30) )/ 30;

            while (months >= 12) {
                months %= 12;
            }
            String strMonths = months == 1 ? " month " : " months ";
            result += String.valueOf(months) + strMonths;
        }

        if (days > 0) {
            while (days >= 365) {
                days -=365;
            }

            while (days >= 30) {
                days %= 30;
            }

            String strDays = days == 1 ? " day " : " days ";
            result += String.valueOf(days) + strDays;
        }

        if (hours > 0) {
            while (hours >= 24) {
                hours %= 24;
            }

            result += addLeadingZero(hours) + ":";
        } else {
            result += "00:";
        }

        if (minutes > 0) {
            while (minutes >= 60) {
                minutes %= 60;
            }

            result += addLeadingZero(minutes) + ":";
        } else {
            result += "00:";
        }

        result += addLeadingZero(seconds);

        return result;
    }

    private String addLeadingZero(long number) {
        if (number > 9) {
            return String.valueOf(number);
        }

        return "0" + String.valueOf(number);
    }

}
