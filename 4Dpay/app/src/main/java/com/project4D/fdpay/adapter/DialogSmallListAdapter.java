package com.project4D.fdpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project4D.fdpay.internal.HouseHolderStatus;
import com.project4D.fdpay.R;

import java.util.ArrayList;

/**
 * Created by Jaeung on 2015-09-05.
 */

//쓰기탭의 카테고리 리스트의 작은 부분의 어댑터
public class DialogSmallListAdapter extends BaseAdapter {
    private ArrayList<SmallListItem> smallListItems;
    HouseHolderStatus houseHolderStatus;

    public DialogSmallListAdapter(){
        smallListItems = new ArrayList<SmallListItem>();
    }
    @Override
    public int getCount() {
        return smallListItems.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return smallListItems.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.dialog_adapter_small_list, parent, false);

        TextView smallListText = (TextView) item.findViewById(R.id.smallListText);

        smallListText.setText(smallListItems.get(position).categorizationSmallText);

        return item;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String categorizationSmallText) {
        smallListItems.add(new SmallListItem(categorizationSmallText));
    }

    public void removeALL() {
        for (int i = 0; smallListItems.size() > i; i++) {
            remove(i);
        }
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        smallListItems.remove(_position);
    }

    public class SmallListItem{
        public String categorizationSmallText;
        public SmallListItem(String categorizationSmallText){
            this.categorizationSmallText = categorizationSmallText;
        }
    }
}
