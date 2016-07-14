package com.android.crystal.ideabook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

public class IdeaListFragement extends Fragment{

    private static ListView ideaList;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.idea_list_fragement, container, false);

        dbHandler = new DBHandler(getContext(), null, null, 1);
        ideaList = (ListView) view.findViewById(R.id.listView);
        ideaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),IdeaViewActivity.class);
                i.putExtra("itemId",id);
                startActivity(i);
            }
        });
        appendListView();
        return view;
    }

    public void appendListView(){
        ArrayList<Ideas> ideasList = dbHandler.getIdeasList();
        ArrayList<String> ideasTitle = new ArrayList<String>();
        for(Ideas i :ideasList ){
            ideasTitle.add(i.get_ideaTitle().toString());
        }
        ListAdapter ideasAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, ideasTitle);
        ideaList.setAdapter(ideasAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        appendListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        appendListView();
    }
}





