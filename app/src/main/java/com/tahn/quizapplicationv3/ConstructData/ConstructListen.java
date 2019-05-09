package com.tahn.quizapplicationv3.ConstructData;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Listen;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstructListen {
    private static ArrayList<Listen> listenArrayList = new ArrayList<>(
            Arrays.asList(new Listen("A Day at School", R.raw.elem2),
                    new Listen("Airport Arrival", R.raw.airportarrival),
                    new Listen("Christmas is Coming!", R.raw.santa),
                    new Listen("Answering Machine", R.raw.phone),
                    new Listen("Camping under the Stars", R.raw.camp1),
                    new Listen("First Date",R.raw.firstdate),
                    new Listen("A Fun Day", R.raw.fun),
                    new Listen("Happy Birthday!", R.raw.birthday),
                    new Listen("New Year's Day", R.raw.newyear),
                    new Listen("Nice to meet you", R.raw.childintro2))
    );

    public static ArrayList<Listen> returnArrayListenData(){
        return listenArrayList;
    }
}
