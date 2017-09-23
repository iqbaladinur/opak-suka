package com.app.iqbaladinur.opacsuka.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.iqbaladinur.opacsuka.Model.ListModel;
import com.app.iqbaladinur.opacsuka.R;

import java.util.ArrayList;

/**
 * Created by sanja on 03/06/2017.
 */

public class SavedStoreCustomAdapter extends BaseAdapter {
    public Activity act;
    public ArrayList item;
    public SavedStoreCustomAdapter(Activity act, ArrayList item){
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
            holder = new ViewHolder();
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
        return convertView;
    }

    static class ViewHolder{
        public TextView txtJudul;
        public TextView txtDeskripsi;
        public TextView txtLokasi;
    }
}
