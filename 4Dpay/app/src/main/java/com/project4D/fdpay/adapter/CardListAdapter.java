package com.project4D.fdpay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
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
        final CardModel discount = (CardModel) getItem(position);
        CardModel prev_discount = null;
        if (position > 0){
            prev_discount = (CardModel) getItem(position - 1);
        }

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.card_item, null);

            CardModel c = cardList.get(position);

            holder.layout = (RelativeLayout) convertView.findViewById(R.id.card_mainlayout);
            holder.card = (ImageView) convertView.findViewById(R.id.card_item_img);
            if(position%2==0)
                holder.card.setImageResource(R.drawable.card_white_pink_gradation);
            holder.cardText = (TextView) convertView.findViewById(R.id.card_item_text);
            holder.cardText.setText(c.getText());
            Log.i("Tag", "getView title:"+c.getText()+" position:"+position);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setRoundedBackground(holder.layout, Color.parseColor("white"));

        return convertView;
    }

    private void setRoundedBackground(View view,int color){
        final GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(color);
        gradientDrawable.invalidateSelf();
    }

    private class ViewHolder {
        public RelativeLayout layout;
        public ImageView card;
        public TextView cardText;
    }
}
