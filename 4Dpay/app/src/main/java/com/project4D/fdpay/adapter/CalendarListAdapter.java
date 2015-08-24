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
 * Created by Jaeung on 2015-07-28.
 */
public class CalendarListAdapter extends BaseAdapter{

    private ArrayList<CalendarItem> calendarItemList;

    // 생성자
    public CalendarListAdapter() {
        calendarItemList = new ArrayList<CalendarItem>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return calendarItemList.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return calendarItemList.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.adpater_calendar_list, parent, false);

        TextView breakdown = (TextView) item.findViewById(R.id.breakdown);
        TextView categorize = (TextView) item.findViewById(R.id.categorize);
        TextView incomingOrSpending = (TextView) item.findViewById(R.id.incomingOrSpending);

        breakdown.setText(calendarItemList.get(position).breakdown);
        categorize.setText(calendarItemList.get(position).categorize);
        incomingOrSpending.setText(calendarItemList.get(position).incoming + "");

        return item;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String breakdown, String categorize, int incoming, int spending, String value) {
        calendarItemList.add(new CalendarItem(breakdown, categorize, incoming, spending));
    }

    public void removeALL(){
        for(int i = 0; calendarItemList.size() > i; i++){
            remove(i);
        }
    }
    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        calendarItemList.remove(_position);
    }

    private class CalendarItem{
        public String breakdown;
        public String categorize;
        public int incoming = 0;
        public int spending = 0;
        public CalendarItem(String breakdown, String categorize, int incoming, int spending){
            this.breakdown = breakdown;
            this.categorize = categorize;
            this.incoming = incoming;
            this.spending = spending;
        }
    }
}