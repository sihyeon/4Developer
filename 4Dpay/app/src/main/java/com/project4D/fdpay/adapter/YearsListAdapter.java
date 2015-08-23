package com.project4D.fdpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project4D.fdpay.CalendarFragment;
import com.project4D.fdpay.MainActivity;
import com.project4D.fdpay.R;

import java.util.ArrayList;

/**
 * Created by Jaeung on 2015-07-28.
 */
public class YearsListAdapter extends BaseAdapter {
    private ArrayList<AdapterLayout> adapterLayout;

    private int year;

    // 생성자
    public YearsListAdapter() {
        adapterLayout = new ArrayList<AdapterLayout>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return adapterLayout.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return adapterLayout.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        TextView monthText = null;
        TextView spendingText = null;
        TextView incomingText = null;
        CustomHolder holder = null;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if (convertView == null) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_years_list, parent, false);

            //텍스트뷰 등록
            monthText = (TextView) convertView.findViewById(R.id.listMonth);
            spendingText = (TextView) convertView.findViewById(R.id.listTotalSpendResult);
            incomingText = (TextView) convertView.findViewById(R.id.listTotalIncomeResult);

            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.monthTextHolder = monthText;
            holder.spendingTextHolder = spendingText;
            holder.incomingTextHolder = incomingText;
            convertView.setTag(holder);

            monthText.setText(adapterLayout.get(pos).month + "월");
            spendingText.setText(adapterLayout.get(pos).spending + "원");
            incomingText.setText(adapterLayout.get(pos).incoming + "원");

            //클릭 이벤트 리스너
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) MainActivity.mainActivityContext).date.useState = true;
                    ((MainActivity) MainActivity.mainActivityContext).date.year = year;
                    ((MainActivity) MainActivity.mainActivityContext).date.month = adapterLayout.get(pos).month;
                    ((MainActivity) MainActivity.mainActivityContext).setDrawerLastSelectedItem(4);
                    ((MainActivity) MainActivity.mainActivityContext).transactFragment(new CalendarFragment(), "Calendar");
                }
            });
        } else {
            holder = (CustomHolder) convertView.getTag();
            monthText = holder.monthTextHolder;
            spendingText = holder.spendingTextHolder;
            incomingText = holder.incomingTextHolder;
        }

        return convertView;
    }


    private class CustomHolder {
        TextView monthTextHolder;
        TextView spendingTextHolder;
        TextView incomingTextHolder;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(int year, int month, int spending, int incoming) {
        this.year = year;
        adapterLayout.add(new AdapterLayout(month, spending, incoming));
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        adapterLayout.remove(_position);
    }

    public void removeALL(){
        for (int i = 0; adapterLayout.size() > i; i++){
            remove(i);
        }
    }

    public void setYear(int year){
        this.year = year;
    }

    private class AdapterLayout{
        public int month;
        public int spending;
        public int incoming;
        public int balance;
        public AdapterLayout(int month, int spending, int incoming){
            this.month = month;
            this.spending = spending;
            this.incoming = incoming;
        }
    }

}
