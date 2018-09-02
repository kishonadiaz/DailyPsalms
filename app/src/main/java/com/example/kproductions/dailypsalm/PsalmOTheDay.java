package com.example.kproductions.dailypsalm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PsalmOTheDay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PsalmOTheDay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PsalmOTheDay extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String[] psalmsTitle = {"Psalms 1","Psalms 2","Psalms 3","Psalms 4","Psalms 5","Psalms 6",
            "Psalms 7","Psalms 8","Psalms 9","Psalms 10","Psalms 11","Psalms 12",
            "Psalms 13","Psalms 14","Psalms 15","Psalms 16","Psalms 17","Psalms 18",
            "Psalms 19","Psalms 20","Psalms 21","Psalms 22","Psalms 23","Psalms 24",
            "Psalms 25","Psalms 26","Psalms 27","Psalms 28","Psalms 29","Psalms 30",
            "Psalms 31","Psalms 32","Psalms 33","Psalms 34","Psalms 35","Psalms 36",
            "Psalms 37","Psalms 38","Psalms 39","Psalms 40","Psalms 41","Psalms 42",
            "Psalms 43","Psalms 44","Psalms 45","Psalms 46","Psalms 47","Psalms 48",
            "Psalms 49","Psalms 50","Psalms 51","Psalms 52","Psalms 53","Psalms 54",
            "Psalms 55","Psalms 56","Psalms 57","Psalms 58","Psalms 59","Psalms 60",
            "Psalms 61","Psalms 62","Psalms 63","Psalms 64","Psalms 65","Psalms 66",
            "Psalms 67","Psalms 68","Psalms 69","Psalms 70","Psalms 71","Psalms 72",
            "Psalms 73","Psalms 74","Psalms 75","Psalms 76","Psalms 77","Psalms 78",
            "Psalms 79","Psalms 80","Psalms 81","Psalms 82","Psalms 83","Psalms 84",
            "Psalms 85","Psalms 86","Psalms 87","Psalms 88","Psalms 89","Psalms 90",
            "Psalms 91","Psalms 92","Psalms 93","Psalms 94","Psalms 95","Psalms 96",
            "Psalms 97","Psalms 98","Psalms 99","Psalms 100","Psalms 101","Psalms 102",
            "Psalms 103","Psalms 104","Psalms 105","Psalms 106","Psalms 107","Psalms 108",
            "Psalms 109","Psalms 110","Psalms 111","Psalms 112","Psalms 113","Psalms 114",
            "Psalms 115","Psalms 116","Psalms 117","Psalms 118","Psalms 119","Psalms 120",
            "Psalms 121","Psalms 122","Psalms 123","Psalms 124","Psalms 125","Psalms 126",
            "Psalms 127","Psalms 128","Psalms 129","Psalms 130","Psalms 131","Psalms 132",
            "Psalms 133","Psalms 134","Psalms 135","Psalms 136","Psalms 137","Psalms 138",
            "Psalms 139","Psalms 140","Psalms 141","Psalms 142","Psalms 143","Psalms 144",
            "Psalms 145","Psalms 146","Psalms 147","Psalms 148","Psalms 149","Psalms 150"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ListView listView;
    int greencount = 100;
    int bluecounter = 120;
    int redcounter = 200;
    static boolean initOnstart = false;
    ArrayList<ListData> listData;



    private ListViewAdapter listViewAdapter = null;

    PsalmOTheDay psalmOTheDay;
    MyTextToSpeech myTextToSpeech;



    public PsalmOTheDay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PsalmOTheDay.
     */
    // TODO: Rename and change types and number of parameters
    public static PsalmOTheDay newInstance(String param1, String param2) {
        PsalmOTheDay fragment = new PsalmOTheDay();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOnstart = true;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final int rand = new Random().nextInt(149);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    listView.setSelection(rand);
                    listData.get(rand).setThisColor(Color.RED);
                    myTextToSpeech.speakWords("Please Reed " + listData.get(rand).getTexts());

            }
        }, 1000);

        initOnstart = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        psalmOTheDay = this;
        View view =  inflater.inflate(R.layout.fragment_psalm_othe_day, container, false);
        final ArrayList<ListData> listDataArrayList = new ArrayList<>();
//        final ListData[] listData = {new ListData(R.drawable.ic_launcher_background,"test")};
        listData = new ArrayList<>();

        for(String i: psalmsTitle){
            listData.add(new ListData(R.drawable.pslamoftheday,Color.rgb(redcounter,greencount,bluecounter),i));

            bluecounter++;
            greencount++;
            redcounter++;
        }

         myTextToSpeech = new MyTextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                myTextToSpeech.onInit(status);
            }
        });

        myTextToSpeech.setPitch(.3f);




        for(ListData i : listData){
            listDataArrayList.add(i);


        }


        listViewAdapter = new ListViewAdapter(view.getContext(),R.layout.listview_info,Color.RED,listDataArrayList);



        listView = view.findViewById(R.id.palsmlist);

        listView.setAdapter(listViewAdapter);

        final int rand = new Random().nextInt(149);




        startActivityForResult(myTextToSpeech.getInent(),myTextToSpeech.MY_DATA_CHECK_CODE);

        if(!initOnstart) {
            listView.setTranslationZ(30);
            listView.setSelection(rand);
            listView.scrollListBy(-300);
            listData.get(rand).setThisColor(Color.RED);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    myTextToSpeech.speakWords("Please Reed " + listData.get(rand).getTexts());
                }
            }, 1000);
        }
        listView.setDrawSelectorOnTop(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(listData.get(position).getTexts().equals(listDataArrayList.get(position).getTexts())){

                    mListener.onPsalmInteraction(psalmOTheDay,view,position,listDataArrayList.get(position).getTexts());

                }

            }
        });
        initOnstart = true;
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(myTextToSpeech != null){
            myTextToSpeech.ttsStop();
        }
//        int rand = new Random().nextInt(149);
//        listView.setSelection(rand);
//        listData.get(rand).setThisColor(Color.RED);
    }

    @Override
    public void onPause() {
        super.onPause();
        initOnstart = false;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPsalmInteraction(PsalmOTheDay psalmOTheDay,View view,int position,String message);
    }
}
