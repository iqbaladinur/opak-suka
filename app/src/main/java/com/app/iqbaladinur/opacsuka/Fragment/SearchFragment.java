package com.app.iqbaladinur.opacsuka.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.iqbaladinur.opacsuka.HasilPencarian;
import com.app.iqbaladinur.opacsuka.HttpRestHandler.ApiUtils;
import com.app.iqbaladinur.opacsuka.Interface.Service;
import com.app.iqbaladinur.opacsuka.Model.DataPencarian;
import com.app.iqbaladinur.opacsuka.Model.Datum;
import com.app.iqbaladinur.opacsuka.Model.ListModel;
import com.app.iqbaladinur.opacsuka.R;
import com.app.iqbaladinur.opacsuka.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends android.app.Fragment {

    private Service mService;
    public SearchView mySearch;
    String key_word;
    private String base_url = "http://opac-api.azurewebsites.net/";
    Intent i;
    ArrayList listItem;
    ProgressDialog dialog;
    Call<DataPencarian> result;
    AlertDialog.Builder message;
    ListView cached;
    DatabaseHandler db;
    List<String> chached_keyword;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = new DatabaseHandler(getActivity());
        chached_keyword = db.getAll();
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mySearch = (SearchView) rootView.findViewById(R.id.keyword);
        mService = ApiUtils.getService(base_url);
        cached = (ListView) rootView.findViewById(R.id.cached_keyword);
        cached.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, chached_keyword));

        /*
         *loading bar
         */
        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Searching..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        message=new AlertDialog.Builder(getActivity());
        message.setCancelable(false);
        message.setTitle("Sayang Sekali");
        message.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        message.setMessage("Tidak Ada Data ditemukan!");
        message.create();

        i = new Intent(getActivity(), HasilPencarian.class);

        //on query submit
        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.show();
                key_word = mySearch.getQuery().toString();
                result = mService.getData(key_word);
                result.enqueue(new Callback<DataPencarian>() {
                    @Override
                    public void onResponse(Call<DataPencarian> call, Response<DataPencarian> response) {
                        if (response.body().getStatus().equals("ok")){
                            mySearch.clearFocus();
                            dialog.dismiss();
                            List<Datum> datane = response.body().getData();
                            listItem = new ArrayList<>();
                            ListModel tampung;
                            for (int i = 0; i < datane.size();i++){
                                tampung = new ListModel();
                                tampung.setJudul(datane.get(i).getJudul());
                                tampung.setDes(datane.get(i).getDes());
                                tampung.setLokasi(datane.get(i).getLokasi());

                                listItem.add(tampung);
                            }
                            i.putExtra("Datanya", listItem);
                            i.putExtra("Keyword", key_word);
                            Log.d("insert", "inserting data");
                            if (chached_keyword.size() < 10){
                                if (!db.isKeywordExist(key_word))
                                    db.save(key_word);
                            }else{
                                db.deleteLast();
                                if (!db.isKeywordExist(key_word))
                                    db.save(key_word);
                            }
                            startActivity(i);

                        }else{
                            message.show();
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<DataPencarian> call, Throwable t) {
                        mySearch.clearFocus();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Mohon Periksa Koneksi Anda.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        cached.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                key_word = (String) ((TextView)view).getText();
                result = mService.getData(key_word);
                result.enqueue(new Callback<DataPencarian>() {
                    @Override
                    public void onResponse(Call<DataPencarian> call, Response<DataPencarian> response) {
                        if (response.body().getStatus().equals("ok")){
                            dialog.dismiss();
                            List<Datum> datane = response.body().getData();
                            listItem = new ArrayList<>();
                            ListModel tampung;
                            for (int i = 0; i < datane.size();i++){
                                tampung = new ListModel();
                                tampung.setJudul(datane.get(i).getJudul());
                                tampung.setDes(datane.get(i).getDes());
                                tampung.setLokasi(datane.get(i).getLokasi());

                                listItem.add(tampung);
                            }
                            i.putExtra("Datanya", listItem);
                            i.putExtra("Keyword", key_word);
                            Log.d("insert", "inserting data");
                            startActivity(i);
                        }else{
                            message.show();
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<DataPencarian> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Mohon Periksa Koneksi Anda.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return  rootView;
    }

}
