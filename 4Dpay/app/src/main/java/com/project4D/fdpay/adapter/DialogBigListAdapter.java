package com.project4D.fdpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project4D.fdpay.R;

import java.util.ArrayList;

/**
 * Created by Jaeung on 2015-09-03.
 */
public class DialogBigListAdapter extends BaseAdapter {
    private ArrayList<BigListItem> bigListItems;

    public DialogBigListAdapter(){
        bigListItems = new ArrayList<BigListItem>();
    }
    @Override
    public int getCount() {
        return bigListItems.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return bigListItems.get(position);
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
        View item = inflater.inflate(R.layout.dialog_adapter_big_list, parent, false);

        TextView bigListText = (TextView) item.findViewById(R.id.bigListText);
        bigListText.setText(bigListItems.get(position).categorizationBigText);
  /*      item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return item;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String CategorizationBigText) {
        bigListItems.add(new BigListItem(CategorizationBigText));
    }

    public void removeALL() {
        for (int i = 0; bigListItems.size() > i; i++) {
            remove(i);
        }
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        bigListItems.remove(_position);
    }

    public class BigListItem{
        public String categorizationBigText;
        public BigListItem(String CategorizationBigText){
            this.categorizationBigText = CategorizationBigText;
        }
    }
}
