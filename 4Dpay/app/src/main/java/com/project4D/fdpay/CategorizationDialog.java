package com.project4D.fdpay;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.project4D.fdpay.adapter.DialogBigListAdapter;
import com.project4D.fdpay.adapter.DialogSmallListAdapter;
import com.project4D.fdpay.internal.HouseHolderStatus;

/**
 * Created by Jaeung on 2015-09-03.
 */
public class CategorizationDialog extends Dialog {
    private ListView bigList;
    private ListView smallList;
    private DialogBigListAdapter dialogBigListAdapter;
    private DialogSmallListAdapter dialogSmallListAdapter;

    private HouseHolderStatus houseHolderStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_categorization_list);

        dialogBigListAdapter = new DialogBigListAdapter();

        bigList = (ListView) findViewById(R.id.bigList);
        bigList.setAdapter(dialogBigListAdapter);
        bigList.setOnItemClickListener(bigListClickListener);

        dialogBigListAdapter.add("식비", "dialog_food_expenses");
        dialogBigListAdapter.add("주거/통신", "dialog_house_communication");
        dialogBigListAdapter.add("생활용품", "dialog_daily_supplies");
        dialogBigListAdapter.add("의복/미용", "dialog_clothes_beauty_treatment");
        dialogBigListAdapter.add("건강/문화", "dialog_health_culture");
        dialogBigListAdapter.add("교통/차량", "dialog_traffic_car");
        dialogBigListAdapter.add("경조사/기부금", "dialog_family_event_contribution");
        dialogBigListAdapter.add("저축", "dialog_saving");
        dialogBigListAdapter.add("이체/출금", "dialog_transfer_withdraw");
        dialogBigListAdapter.add("기타지출", "dialog_the_others");

        smallList = (ListView) findViewById(R.id.smallList);
    }

    public OnItemClickListener bigListClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            String item = ((DialogBigListAdapter.BigListItem) dialogBigListAdapter.getItem(position)).categorizationBigText;
            dialogSmallListAdapter = new DialogSmallListAdapter();
            smallList.setAdapter(dialogSmallListAdapter);
            selectSmallList(item);
        }
    };

    public OnItemClickListener smallListClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            String item = ((DialogSmallListAdapter.SmallListItem)dialogSmallListAdapter.getItem(position)).categorizationSmallText;
            houseHolderStatus.categorizationText = item;
            houseHolderStatus.categorizationTextStatus = true;
            dismiss();
        }
    };

    public void selectSmallList(String item) {

        dialogSmallListAdapter.removeALL();
        dialogSmallListAdapter = null;
        dialogSmallListAdapter = new DialogSmallListAdapter();
        smallList.setAdapter(dialogSmallListAdapter);
        smallList.setOnItemClickListener(smallListClickListener);

        switch (item) {
            case "식비":
                dialogSmallListAdapter.add("식재료");
                dialogSmallListAdapter.add("간식");
                dialogSmallListAdapter.add("외식/배달");
                dialogSmallListAdapter.add("커피/음료");
                dialogSmallListAdapter.add("기타식비");
                break;
            case "주거/통신":
                dialogSmallListAdapter.add("주거관리비");
                dialogSmallListAdapter.add("월세");
                dialogSmallListAdapter.add("공과금");
                dialogSmallListAdapter.add("통신비");
                dialogSmallListAdapter.add("인터넷");
                dialogSmallListAdapter.add("기타주거/통신비");
                break;
            case "생활용품":
                dialogSmallListAdapter.add("잡화소모");
                dialogSmallListAdapter.add("가구/가전");
                dialogSmallListAdapter.add("주방/욕실");
                dialogSmallListAdapter.add("기타생활용품");
                break;
            case "의복/미용":
                dialogSmallListAdapter.add("의류");
                dialogSmallListAdapter.add("미용");
                dialogSmallListAdapter.add("패션잡화");
                dialogSmallListAdapter.add("세탁수선비");
                dialogSmallListAdapter.add("기타의복/미용");
                break;
            case "건강/문화":
                dialogSmallListAdapter.add("의료비");
                dialogSmallListAdapter.add("운동/레저");
                dialogSmallListAdapter.add("문화생활");
                dialogSmallListAdapter.add("도서");
                dialogSmallListAdapter.add("여행");
                dialogSmallListAdapter.add("기타건강/문화");
                break;
            case "교통/차량":
                dialogSmallListAdapter.add("대중교통");
                dialogSmallListAdapter.add("주유비");
                dialogSmallListAdapter.add("세차/수리");
                dialogSmallListAdapter.add("주차/통행");
                dialogSmallListAdapter.add("기타교통/차량");
                break;
            case "경조사/기부금":
                dialogSmallListAdapter.add("경조사비");
                dialogSmallListAdapter.add("회비");
                dialogSmallListAdapter.add("선물");
                dialogSmallListAdapter.add("기부금");
                dialogSmallListAdapter.add("기타경조사/기부금");
                break;
            case "저축":
                dialogSmallListAdapter.add("적금");
                dialogSmallListAdapter.add("예금");
                dialogSmallListAdapter.add("펀드");
                dialogSmallListAdapter.add("보험");
                dialogSmallListAdapter.add("기타저축");
                break;
            case "이체/출금":
                dialogSmallListAdapter.add("이체");
                dialogSmallListAdapter.add("출금");
                break;
            case "기타지출":
                dialogSmallListAdapter.add("기타지출");
                break;


        }
    }


    public CategorizationDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }
}
