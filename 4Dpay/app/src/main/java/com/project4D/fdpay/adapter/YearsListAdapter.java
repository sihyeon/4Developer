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
public class YearsListAdapter extends BaseAdapter{

    private ArrayList<String> m_List;

    // 생성자
    public YearsListAdapter() {
        m_List = new ArrayList<String>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
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

        TextView text = null;
        CustomHolder holder = null;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if (convertView == null) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_years_list, parent, false);

            text = (TextView) convertView.findViewById(R.id.listMonth);

            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.m_TextView = text;
            convertView.setTag(holder);

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
/*
                    int setYearsNumber = ((YearsActivity)YearsActivity.yearsContext).getListYearsNumber();
                    //터치시 해당 년 월로 이동
                    ((CalendarActivity)CalendarActivity.calendarContext).setSetYear(setYearsNumber);
                    ((CalendarActivity)CalendarActivity.calendarContext).setSetMonth(Integer.parseInt(m_List.get(pos)));
                    ((CalendarActivity)CalendarActivity.calendarContext).setMinMaxDate();

                    //탭을 달력탭으로 변경함
                   MainActivity.tab_host.setCurrentTab(0);
                   */
                }
            });
        } else {
            holder = (CustomHolder) convertView.getTag();
            text = holder.m_TextView;
        }

        // Text 등록
        text.setText(m_List.get(position));

        return convertView;
    }

    private class CustomHolder {
        TextView m_TextView;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String _msg) {
        m_List.add(_msg);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
}
