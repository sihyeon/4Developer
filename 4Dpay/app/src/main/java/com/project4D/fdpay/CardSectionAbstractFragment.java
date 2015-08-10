package com.project4D.fdpay;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project4D.fdpay.model.CardModel;
import com.project4D.fdpay.view.DrawerFragment;

import java.util.ArrayList;
import java.util.List;

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

public abstract class CardSectionAbstractFragment extends DrawerFragment {
    protected ListView listview;
    protected CardListAdapter adapter;
    protected String myname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_card_section_abstract, container, false);

        listview = (ListView) view.findViewById(R.id.card_listView);
        adapter = new CardListAdapter(getActivity());
        listview.setAdapter(adapter);

        setActivityName();
        //TODO how to get info through HttpPoster? lol
        setAdapterItem();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent i = new Intent(CardActivity.this, ShowCardInfoActivity.class).putExtra(Constant.cardName, /*TODO later; send card name*/);
                setOnClickItemListView(parent, position);
            }
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        ((CardActivity) getActivity()).setActionBarTitle(myname);
    }

    //fragment에서 actionbar의 제목을 지정
    public abstract void setActivityName();

    //adapter의 리스트 아이템 관리
    public abstract void setAdapterItem();

    //listview의 아이템 이벤트 등록
    public abstract void setOnClickItemListView(AdapterView<?> parent,int position);


    protected class CardListAdapter extends BaseAdapter {
        protected List<CardModel> cardList = new ArrayList<CardModel>();
        protected Context context;

        public CardListAdapter(Context context) {
            super();
            this.context = context;
        }

        public void addItem0(String name) {
            CardModel card = new CardModel();
            card.setImg(getResources().getDrawable(R.drawable.card0));
            card.setText(name);
            cardList.add(card);
        }

        public void addItem1(String name) {
            CardModel card = new CardModel();
            card.setImg(getResources().getDrawable(R.drawable.card1));
            card.setText(name);
            cardList.add(card);
        }

        public void addItem2(String name) {
            CardModel card = new CardModel();
            card.setImg(getResources().getDrawable(R.drawable.card2));
            card.setText(name);
            cardList.add(card);
        }

        public void addItem3(String name) {
            CardModel card = new CardModel();
            card.setImg(getResources().getDrawable(R.drawable.card3));
            card.setText(name);
            cardList.add(card);
        }

        @Override
        public int getCount() {
            return cardList.size();
        }

        @Override
        public Object getItem(int position) {
            return cardList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.card_item, null);
                holder.card = (ImageView) convertView.findViewById(R.id.card_item_img);
                holder.cardText = (TextView) convertView.findViewById(R.id.card_item_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CardModel c = cardList.get(position);
            holder.card.setImageDrawable(c.getImg());
            holder.cardText.setText(c.getText());

            return convertView;
        }

        public void addLast() {
            CardModel last = new CardModel();
            last.setImg(getResources().getDrawable(R.drawable.card_addcard));
            cardList.add(last);
        }

        protected class ViewHolder {
            public ImageView card;
            public TextView cardText;
        }
    }
}
