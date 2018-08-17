package com.example.noname.mybudget.mainMenuFragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.noname.mybudget.Data.DataBaseHelper;
import com.example.noname.mybudget.R;

import java.util.ArrayList;
import java.util.List;


public class historyFragment extends Fragment {
    DataBaseHelper myDb;
    String[] Menu = {"empty","empty","empty"};
    List<String> hMenu = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_history, container, false);
        if(myDb!=null) setData();
        myDb = new DataBaseHelper(getActivity());
        ListView listView = view.findViewById(R.id.historyMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                hMenu);
        listView.setAdapter(adapter);

        return view;
    }

    private void setData() {
        Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            hMenu.clear();
            for(int i=0;i<res.getCount();i++) {
                res.moveToNext();
                buffer.append("№ "+res.getString(0)+": ");
                buffer.append(res.getString(1));
                buffer.append(res.getString(2)+" ");
                buffer.append("for "+res.getString(3)+" ");
                buffer.append("on Date: "+res.getString(4));

                hMenu.add(i,String.valueOf(buffer));
                buffer.delete(0,buffer.length());
            }
        }
    }

}


