package com.project4D.fdpay.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4D.fdpay.R;
import com.project4D.fdpay.model.CardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-08-14.
 */
public class CardListAdapter extends BaseAdapter {
    protected List<CardModel> cardList = new ArrayList<CardModel>();
    protected Context context;

    public CardListAdapter(Context context) {
        super();
        this.context = context;
    }

    public void addItem(String name) {
        CardModel card = new CardModel();
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
        //holder.card.setImageDrawable(c.getImg());
        holder.cardText.setText(c.getText());

        return convertView;
    }

    protected class ViewHolder {
        public ImageView card;
        public TextView cardText;
    }
}
