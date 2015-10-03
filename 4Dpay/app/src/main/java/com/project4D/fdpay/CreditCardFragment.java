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
import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.SimpleUAdapter;
import com.project4D.fdpay.event.UListener;
import com.project4D.fdpay.manager.CreditCardTableManager;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * this is Abstraction fragment for CARDSECTION,
 **/
public class CreditCardFragment extends Fragment {
    private CardListAdapter adapter;
    private CreditCardTableManager db;
    private ListView li;
    private EventManager eventManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new CreditCardTableManager(getActivity());
        eventManager = EventManager.getInstance();

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

        eventManager.registerListener("ADD_CREDIT", new SimpleUAdapter<Bundle>() {
            @Override
            public void onSuccess(Bundle b) {
                adapter.addItem(b.getString("NAME"));
                adapter.addId(b.getInt("ID"));
                adapter.notifyDataSetChanged();
            }
        });
        eventManager.registerListener("EDIT_CREDIT", new SimpleUAdapter<Bundle>() {
            @Override
            public void onSuccess(Bundle b) {
                adapter.changeItemNameById(
                        b.getInt("ID"),
                        b.getString("NAME")
                );
                adapter.notifyDataSetChanged();
            }
        });
        eventManager.registerListener("DELETE_CREDIT", new SimpleUAdapter<Bundle>() {
            @Override
            public void onSuccess(Bundle b) {
                adapter.deleteItemById(b.getInt("ID"));
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        setActivityTitle("신용카드");
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
