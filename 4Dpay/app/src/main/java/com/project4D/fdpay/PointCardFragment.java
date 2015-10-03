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
import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.SimpleUAdapter;
import com.project4D.fdpay.manager.PointCardTableManager;

/**
 *
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 8 (2015-08-08)
 * this is Abstraction fragment for CARDSECTION, ( I dont want to change more !! )
 */

public class PointCardFragment extends Fragment {
    private ListView listview;

    private CardListAdapter adapter;
    private PointCardTableManager db;
    private EventManager eventManager = EventManager.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new PointCardTableManager(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_point_card, container, false);

        setActivityTitle("포인트카드");
        adapter = new CardListAdapter();
        listview = (ListView) view.findViewById(R.id.pointcard_listView);
        listview.setAdapter(adapter);
        initAdapterItem();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnClickItemListView(parent, position);
            }
        });

        view.findViewById(R.id.point_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddPointCardInfoActivity.class));
            }
        });

        eventManager.registerListener("ADD_POINT", new SimpleUAdapter<Bundle>() {
            @Override
            public void onSuccess(Bundle b) {
                adapter.addItem(b.getString("NAME"));
                adapter.addId(b.getInt("ID"));
                adapter.notifyDataSetChanged();
            }
        });
        eventManager.registerListener("DELETE_POINT", new SimpleUAdapter<Bundle>() {
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
        setActivityTitle("포인트카드");
    }

    private void setActivityTitle(String title){
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    private void initAdapterItem(){
        for (String e : db.getAllCardName()) {
            adapter.addItem(e);
        }
        for (int i : db.getAllId()) {
            adapter.addId(i);
        }
        adapter.notifyDataSetChanged();
    }

    //listview의 아이템 이벤트 등록
    private void setOnClickItemListView(AdapterView<?> parent,int position){
        //TODO hp..
        Intent i = new Intent(getActivity(), ShowPointCardInfoActivity.class);
        Bundle b = new Bundle();
        b.putString("CLASS_NAME", this.getClass().getSimpleName());
        b.putInt("ID", adapter.getId(position));
        i.putExtras(b);
        startActivity(i);
    }


}
