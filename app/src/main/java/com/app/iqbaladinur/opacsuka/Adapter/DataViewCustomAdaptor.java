package com.app.iqbaladinur.opacsuka.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.iqbaladinur.opacsuka.Model.ListModel;
import com.app.iqbaladinur.opacsuka.Model.SavedData;
import com.app.iqbaladinur.opacsuka.R;
import com.app.iqbaladinur.opacsuka.db.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by sanja on 31/05/2017.
 */

public class DataViewCustomAdaptor extends BaseAdapter {
    public Activity act;
    public ArrayList item;
    public DataViewCustomAdaptor(Activity act, ArrayList item){
        this.act = act;
        this.item = item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recycle_items, null);
            holder = new ViewHolder(convertView);
            holder.txtJudul = (TextView) convertView.findViewById(R.id.judul);
            holder.txtDeskripsi = (TextView) convertView.findViewById(R.id.deskripsi);
            holder.txtLokasi = (TextView) convertView.findViewById(R.id.lokasi);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ListModel data = (ListModel) getItem(position);

        holder.txtJudul.setText(data.getJudul());
        holder.txtDeskripsi.setText(data.getDes());
        holder.txtLokasi.setText(data.getLokasi());
        holder.data = data;
        return convertView;
    }

    static class ViewHolder{
        public ListModel data;
        public TextView txtJudul;
        public TextView txtDeskripsi;
        public TextView txtLokasi;
        private SavedData thedata;
        DatabaseHandler db;

        public ViewHolder(View v){
            thedata = new SavedData();
            db = new DatabaseHandler(v.getContext());
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    thedata.setJudul(data.getJudul());
                    thedata.setDes(data.getDes());
                    thedata.setLokasi(data.getLokasi());
                    if (!db.isDataExist(data.getJudul())) {
                        db.saveDataStore(thedata);
                        Toast.makeText(v.getContext(), "Data Saved",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(), "Data Already Saved",
                                Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
        }
    }



}
