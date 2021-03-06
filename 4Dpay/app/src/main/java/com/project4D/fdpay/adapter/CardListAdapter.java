package com.project4D.fdpay.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project4D.fdpay.R;
import com.project4D.fdpay.model.CardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Somin Lee (sayyo1120@gmail,com)
 * @version 2015-08-14.
 * this is Card List Adatper.
 * In this class, cardlist be costomized.
 */
public class CardListAdapter extends BaseAdapter {
    protected final List<CardModel> cardList = new ArrayList<>();
    protected final List<Integer> cardId = new ArrayList<>();

    public void addItem(String name) {
        CardModel card = new CardModel();
        card.setText(name);
        cardList.add(card);
    }

    public void addId(Integer id){
        cardId.add(id);
        switch (id%3) {
            case 0:
                cardList.get(cardId.indexOf(id)).setImgId(R.drawable.card_white2_yellow);
                break;
            case 1:
                cardList.get(cardId.indexOf(id)).setImgId(R.drawable.card_white1_yellow);
                break;
            case 2:
                cardList.get(cardId.indexOf(id)).setImgId(R.drawable.card_yellow2);
                break;
        }
    }

    public Integer getId(int position){
        return cardId.get(position);
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
        ViewHolder curView;

        if (convertView == null) {
            curView = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
            curView.layout = (RelativeLayout) convertView.findViewById(R.id.card_mainlayout);
            curView.card = (ImageView) convertView.findViewById(R.id.card_item_img);
            curView.cardText = (TextView) convertView.findViewById(R.id.card_item_text);
            setRoundedBackground(curView.layout, Color.parseColor("white"));

            convertView.setTag(curView);
        } else {
            curView = (ViewHolder) convertView.getTag();
        }

        // Set data
        CardModel c = cardList.get(position);
        curView.cardText.setText(c.getText());
        curView.card.setImageResource(c.getImgId());
        return convertView;
    }

    private void setRoundedBackground(View view,int color){
        final GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(color);
        gradientDrawable.invalidateSelf();
    }

    public void changeItemNameById(int id, String name) {
        cardList.get(cardId.indexOf(id)).setText(name);
    }

    public void deleteItemById(int id) {
        int position = cardId.indexOf(id);
        cardList.remove(position);
        cardId.remove(position);
    }



    private class ViewHolder {
        public RelativeLayout layout;
        public ImageView card;
        public TextView cardText;
    }
}
