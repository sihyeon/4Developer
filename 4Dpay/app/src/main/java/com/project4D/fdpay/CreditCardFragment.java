package com.project4D.fdpay;

import android.app.Activity;
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
    private ListView li;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = CreditCardDBManager.newCreditCardDBManager(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_card, container, false);
        adapter = new CardListAdapter(getActivity());
        li = (ListView) view.findViewById(R.id.creditcard_listView);
        li.setAdapter(adapter);
        setAdapterItem();
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnClickItemListView(parent, position);
            }
        });
        setActivityTitle("신용카드");


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
        int listSize = cardList.size();
        int dbSize = db.getCount();
        if(listSize < dbSize){
            Log.i("TAG", "checkAdapterItemChanged cardList size " + listSize);
            Log.i("TAG", "checkAdapterItemChanged db getCount " + dbSize);
            for(int i = listSize+1; i <= dbSize; i++){
                cardList.add(db.getName(i));
                adapter.addItem(db.getName(i));
                Log.i("TAG", "checkAdapterItemChanged inner function : "+db.getName(i));
            }
        }
    }

    private void setActivityTitle(String title){
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void setAdapterItem(){
        cardList = db.getAllName();
        if(cardList.isEmpty()) return;
//        adapter.setCardList(cardList);
        for(String e : cardList){
            adapter.addItem(e);
        }
//        adapter.notifyDataSetInvalidated();
    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent,int position){
        //TODO hp..
        Intent i = new Intent(getActivity(), ShowCardInfoActivity.class);
        i.putExtras(new Bundle(position));
        startActivity(i);
    }

}
