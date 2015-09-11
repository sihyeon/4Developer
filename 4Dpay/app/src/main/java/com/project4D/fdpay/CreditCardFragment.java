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
import com.project4D.fdpay.manager.CreditCardTableManager;

public class CreditCardFragment extends Fragment {
    private CardListAdapter adapter;
    private CreditCardTableManager db;
    private ListView li;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new CreditCardTableManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_card, container, false);
        adapter = new CardListAdapter();
        li = (ListView) view.findViewById(R.id.creditcard_listView);
        li.setAdapter(adapter);
        initAdapterItem();
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
    public void onStart() {
        super.onStart();
        setActivityTitle("신용카드");
        if (AddCreditCardInfoActivity.newlyCardInfo != null) {
            adapter.addItem(AddCreditCardInfoActivity.newlyCardInfo.getString("NAME"));
            adapter.addId(AddCreditCardInfoActivity.newlyCardInfo.getInt("ID"));
            adapter.notifyDataSetChanged();
            AddCreditCardInfoActivity.newlyCardInfo = null;
        }
        if(EditCardNameActivity.cardNameChanged !=null) {
            adapter.changeItemNameById(
                    EditCardNameActivity.cardNameChanged.getInt("ID"),
                    EditCardNameActivity.cardNameChanged.getString("NAME")
            );
            adapter.notifyDataSetChanged();
            EditCardNameActivity.cardNameChanged = null;
        }
        if(ShowCardInfoActivity.deleteCardInfo !=null) {
            adapter.deleteItemById(ShowCardInfoActivity.deleteCardInfo.getInt("ID"));
            adapter.notifyDataSetChanged();
            ShowCardInfoActivity.deleteCardInfo = null;
        }
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void initAdapterItem() {
        for(String e : db.getAllCardName()){
            adapter.addItem(e);
        }
        for(Integer i : db.getAllId()){
            adapter.addId(i);
        }
        adapter.notifyDataSetChanged();

    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent, int position) {
        //TODO hp..
        Intent i = new Intent(getActivity(), ShowCardInfoActivity.class);
        Bundle b = new Bundle();
        b.putString("CLASS_NAME", this.getClass().getSimpleName());
        Log.i("TAG", "setOnClickItemListView " + this.getClass().getSimpleName());
        b.putInt("ID", adapter.getId(position));
        i.putExtras(b);
        startActivity(i);
    }
}
