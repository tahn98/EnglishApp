package com.tahn.quizapplicationv3.ConstructData;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Listen;

import java.util.ArrayList;
import java.util.Arrays;

public class ConstructListen {
    private static ArrayList<Listen> listenArrayList = new ArrayList<>(
            Arrays.asList(new Listen("A Day at School", R.raw.elem2, R.raw.school),
                    new Listen("Airport Arrival", R.raw.airportarrival, R.raw.airtxt),
                    new Listen("Christmas is Coming!", R.raw.santa, R.raw.santatxt),
                    new Listen("Answering Machine", R.raw.phone, R.raw.phonetxt),
                    new Listen("Camping under the Stars", R.raw.camp1, R.raw.camptxt),
                    new Listen("First Date", R.raw.firstdate, R.raw.firsttxt),
                    new Listen("A Fun Day", R.raw.fun, R.raw.funtxt),
                    new Listen("Happy Birthday!", R.raw.birthday, R.raw.birthdaytxt),
                    new Listen("New Year's Day", R.raw.newyear, R.raw.newtxt),
                    new Listen("Nice to meet you", R.raw.childintro, R.raw.childtxt))
    );

    public static ArrayList<Listen> returnArrayListenData(){
        return listenArrayList;
    }
}
