package com.project4D.fdpay;

import android.content.Intent;
import android.widget.AdapterView;

import com.project4D.fdpay.internal.Constant;

public class PointCardFragment extends CardSectionAbstractFragment {

    @Override
    public void setActivityName() {
        super.myname = Constant.iamPoint;
    }

    @Override
    public void setAdapterItem() {
        //first parameter : card's name(get from HttpPoster)
        //second parameter : card's coloer(get from constants)
        super.adapter.addItem0("1");
        super.adapter.addItem1("2");
        super.adapter.addItem2("3");
        super.adapter.addLast();
    }

    @Override
    public void setOnClickItemListView(AdapterView<?> parent,int position) {
        if (parent.getLastVisiblePosition() == position) {
            //Intent i = new Intent(CardActivity.this, ShowCardInfoActivity.class).putExtra(Constant.cardName, /*TODO later; send card name*/);
            startActivity(new Intent(getActivity(), AddCreditCardInfoActivity.class));
            return;
        }
        startActivity(new Intent(getActivity(), ShowCardInfoActivity.class));
    }

}
