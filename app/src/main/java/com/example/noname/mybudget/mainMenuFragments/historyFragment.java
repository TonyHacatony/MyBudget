package com.example.noname.mybudget.mainMenuFragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.mybudget.Data.DataBaseHelper;
import com.example.noname.mybudget.R;


public class historyFragment extends Fragment {
    TextView textType;
    DataBaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_history, container, false);

        myDb = new DataBaseHelper(getActivity());
        textType = view.findViewById(R.id.idResult);

        DataStart();
        return view;
    }


    private void DataStart() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if(res!=null && res.getCount()>0){
            while(res.moveToNext()){
                stringBuffer.append("№ "+res.getString(0)+": ");
                stringBuffer.append(res.getString(1));
                stringBuffer.append(res.getString(2)+" ");
                stringBuffer.append("for "+res.getString(3)+"\n");
                stringBuffer.append("on Date: "+res.getString(4)+"\n"+"\n");
            }
            textType.setText(stringBuffer.toString());
        }
    }


}


