package com.project4D.fdpay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project4D.fdpay.adapter.CardListAdapter;

/**
 * 1. extends AppCompatAcitivity
 * 2. getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 * getSupportActionBar().setHomeButtonEnabled(false);
 * 3. onOptionsItemSelected(MenuItem item)
 *
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 8 (2015-08-08)
 * @see this is Abstraction fragment for CARDSECTION, ( I dont want to change more !! )
 */

public class PointCardFragment extends Fragment {
    private ListView listview;

    private CardListAdapter adapter;
    private String myname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_point_card, container, false);

        setActivityTitle("포인트카드");
        adapter = new CardListAdapter();
        listview = (ListView) view.findViewById(R.id.pointcard_listView);
        listview.setAdapter(adapter);
        setAdapterItem("title1");
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnClickItemListView(parent, position);
            }
        });

        view.findViewById(R.id.point_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AddCreditCardInfoActivity.class), 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1){
            setAdapterItem(data.getStringExtra("cardName"));
            Log.i("To show data", "onActivityResult "+data.getStringExtra("cardName"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle("포인트카드");
    }

    private void setActivityTitle(String title){
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void setAdapterItem(String name){
        //for example
        adapter.addItem(name);
        ((MainActivity)getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent,int position){
        //TODO hp..
        startActivity(new Intent(getActivity(), ShowCardInfoActivity.class));
    }


}
