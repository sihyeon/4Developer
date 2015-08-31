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
import com.project4D.fdpay.util.CreditCardDBManager;

import java.util.List;

public class CreditCardFragment extends Fragment {
    private ListView listview;
    private CardListAdapter adapter;
    private List<String> cardList;
    private String myname;
    private CreditCardDBManager db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = CreditCardDBManager.newCreditCardDBManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_card, container, false);

        setActivityTitle("신용카드");
        adapter = new CardListAdapter(getActivity());
        listview = (ListView) view.findViewById(R.id.creditcard_listView);
        listview.setAdapter(adapter);
        setAdapterItem();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnClickItemListView(parent, position);
            }
        });

        view.findViewById(R.id.credit_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCreditCardInfoActivity.class));
            }
        });
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle("신용카드");
        checkAdapterItemChanged();
    }

    private void checkAdapterItemChanged() {
        if(cardList.size() < db.getCount()){
            Log.i("TAG", "checkAdapterItemChanged cardList size "+cardList.size());
            for(int i = cardList.size() ; i < db.getCount(); i++){
                cardList.add(db.getName(i));
                adapter.addItem(db.getName(i));
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void setActivityTitle(String title){
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void setAdapterItem(){
        cardList = db.getAllName();
        if(cardList.isEmpty()) return;
        for(String e : cardList){
            adapter.addItem(e);
        }
    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent,int position){
        //TODO hp..
        Intent i = new Intent(getActivity(), ShowCardInfoActivity.class);
        i.putExtras(new Bundle(position));
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
