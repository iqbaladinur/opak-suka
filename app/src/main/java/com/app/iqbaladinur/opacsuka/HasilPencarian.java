package com.app.iqbaladinur.opacsuka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.iqbaladinur.opacsuka.Adapter.DataViewCustomAdaptor;
import com.app.iqbaladinur.opacsuka.Model.ListModel;

import java.util.ArrayList;

public class HasilPencarian extends AppCompatActivity {
    ArrayList Listitem;
    ListView Listvew;
    int jumlah_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pencarian);

        Listvew = (ListView) findViewById(R.id.search_result);
        Bundle recived_data = getIntent().getExtras();
        Listitem = (ArrayList) recived_data.get("Datanya");
        jumlah_data = Listitem.size();
        this.setTitle(String.format("%s (%d found)", recived_data.getString("Keyword"), jumlah_data));

        DataViewCustomAdaptor data = new DataViewCustomAdaptor(HasilPencarian.this, Listitem);
        Listvew.setAdapter(data);
    }
}
