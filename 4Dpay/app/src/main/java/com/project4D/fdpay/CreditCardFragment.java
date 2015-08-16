package com.project4D.fdpay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project4D.fdpay.adapter.CardListAdapter;

public class CreditCardFragment extends Fragment {
    private ListView listview;
    private CardListAdapter adapter;
    private String myname;

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
    }

    private void setActivityTitle(String title){
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void setAdapterItem(){
        //for example
        adapter.addItem("title1");
        adapter.addItem("title2");
    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent,int position){
        //TODO hp..
        startActivity(new Intent(getActivity(), ShowCardInfoActivity.class));
    }

}
