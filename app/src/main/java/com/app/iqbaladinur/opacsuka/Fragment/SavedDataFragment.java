package com.app.iqbaladinur.opacsuka.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.iqbaladinur.opacsuka.Adapter.SavedStoreCustomAdapter;
import com.app.iqbaladinur.opacsuka.Model.ListModel;
import com.app.iqbaladinur.opacsuka.Model.SavedData;
import com.app.iqbaladinur.opacsuka.R;
import com.app.iqbaladinur.opacsuka.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDataFragment extends android.app.Fragment {
    ArrayList Listitem;
    DatabaseHandler db;
    ListView Listvew;
    TextView nodata;
    public SavedDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = new DatabaseHandler(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_saved_data, container, false);
        List<SavedData> datane = db.getAllSavedDataStore();
        nodata = (TextView) rootView.findViewById(R.id.textNodata);
        Listitem = new ArrayList<>();
        ListModel tampung;
        if (datane.size() > 0){
            for (int i = 0; i < datane.size();i++){
                tampung = new ListModel();
                tampung.setJudul(datane.get(i).getJudul());
                tampung.setDes(datane.get(i).getDes());
                tampung.setLokasi(datane.get(i).getLokasi());
                Listitem.add(tampung);
            }
            Listvew = (ListView) rootView.findViewById(R.id.savedData);
            SavedStoreCustomAdapter data = new SavedStoreCustomAdapter(getActivity(), Listitem);
            Listvew.setAdapter(data);
            nodata.setVisibility(View.GONE);
        }
        return rootView;
    }

}
