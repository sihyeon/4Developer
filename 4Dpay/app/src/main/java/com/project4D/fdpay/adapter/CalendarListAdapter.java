package com.project4D.fdpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project4D.fdpay.HouseHolderStatus;
import com.project4D.fdpay.MainActivity;
import com.project4D.fdpay.R;
import com.project4D.fdpay.WritingFragment;

import java.util.ArrayList;

/**
 * Created by Jaeung on 2015-07-28.
 */
public class CalendarListAdapter extends BaseAdapter{
    private HouseHolderStatus houseHolderStatus;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.adpater_calendar_list, parent, false);

        TextView breakdown = (TextView) item.findViewById(R.id.breakdown);
        TextView categorization = (TextView) item.findViewById(R.id.categorize);
        TextView amount = (TextView) item.findViewById(R.id.amount);

        breakdown.setText(calendarItemList.get(position).breakdown);
        categorization.setText(calendarItemList.get(position).categorization);
        amount.setText(calendarItemList.get(position).incoming + "");

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseHolderStatus.writingInfoUseStatus = true;
                houseHolderStatus.breakdown = calendarItemList.get(position).categorization;
                houseHolderStatus.categorization = calendarItemList.get(position).categorization;
                houseHolderStatus.amount = calendarItemList.get(position).incoming;

                //날짜 설정
                houseHolderStatus.year = calendarItemList.get(position).year;
                houseHolderStatus.month = calendarItemList.get(position).month;
                houseHolderStatus.day = calendarItemList.get(position).day;
                houseHolderStatus.dateUseStatus = true;
                ((MainActivity) MainActivity.mainActivityContext).setDrawerLastSelectedItem(3);
                ((MainActivity) MainActivity.mainActivityContext).transactFragment(new WritingFragment(), "Writing");

            }
        });

        return item;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String breakdown, String categorize, int incoming, int spending, String value, int year, int month, int day) {
        calendarItemList.add(new CalendarItem(breakdown, categorize, incoming, spending, year, month, day));
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
        public String categorization;
        public int incoming = 0;
        public int spending = 0;
        public int year = 0;
        public int month = 0;
        public int day = 0;
        public CalendarItem(String breakdown, String categorize, int incoming, int spending, int year, int month, int day){
            this.breakdown = breakdown;
            this.categorization = categorize;
            this.incoming = incoming;
            this.spending = spending;
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}