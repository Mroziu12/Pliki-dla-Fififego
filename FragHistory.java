package com.example.dzik;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragHistory extends Fragment {

    String TAG="Mroziu";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment historia_zgloszen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragHistory newInstance(String param1, String param2) {
        FragHistory fragment = new FragHistory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.frag_history, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rv_historia);
        Log.d(TAG, "onCreateView: to jest rv:"+recyclerView);

        Context context = container.getContext();
        //TODO Pobierz to wszytko z serwera
        //String opisy[],data[],status[];
        // int ileMlodych[],ileStarych[];
        Vector<String> opisy = new Vector<String>() ;
        Vector<String> data= new Vector<String>();
        Vector<String> status= new Vector<String>();
        Vector<String> miejsce= new Vector<String>();
        Vector<Integer> ileMlodych= new Vector<Integer>();
        Vector<Integer> ileStarych= new Vector<Integer>();

        //Przykładowe dane 1
        opisy.addElement("Pierwszy Opis");
        data.addElement("27.11.2020");
        status.addElement("Oczekujący");
        miejsce.addElement("Zabrze");
        ileMlodych.addElement(2);
        ileStarych.addElement(1);
        //Przykłądowe dane 2
        opisy.addElement("Drugi Opis");
        data.addElement("17.11.2020");
        status.addElement("Zaakceptowany");
        miejsce.addElement("Więcbork");
        ileMlodych.addElement(2);
        ileStarych.addElement(3);
        //TODO--------------------------------------------------------------
        HistoryAdapter historyAdapter = new HistoryAdapter(context,opisy,data,status,miejsce,ileMlodych,ileStarych);
        Log.d(TAG, "onCreateView: po stworzeniu"+historyAdapter);
        recyclerView.setAdapter(historyAdapter);
        Log.d(TAG, "onCreateView: po adapterze"+recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Log.d(TAG, "onCreateView: po layout manager"+recyclerView);
        return v;
    }


}