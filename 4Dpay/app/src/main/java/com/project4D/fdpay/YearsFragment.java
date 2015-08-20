package com.project4D.fdpay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project4D.fdpay.adapter.YearsListAdapter;

/**
 * Created by Jaeung on 2015-07-28.
 */
public class YearsFragment extends Fragment implements OnClickListener {
    private ListView mListView = null;
    private YearsListAdapter yearsListAdapter = null;
    private TextView listYears;

    private int listYearsNumber = Date.TODAY_YEAR;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View yearsView = inflater.inflate(R.layout.fragment_years_view, container, false);
        //액션바 이름 변경
        setActivityTitle("연별 보기");
        //어댑터 생성
        yearsListAdapter = new YearsListAdapter();
        //리스트뷰 연결
        mListView = (ListView) yearsView.findViewById(R.id.listView);
        //어댑터 연결
        mListView.setAdapter(yearsListAdapter);

        yearsListAdapter.add(listYearsNumber, 12, 4000, 5000);
        yearsListAdapter.add(listYearsNumber, 11, 4000, 5000);
        yearsListAdapter.add(listYearsNumber, 10, 4000, 5000);
        yearsListAdapter.add(listYearsNumber, 9, 4000, 5000);
        yearsListAdapter.add(listYearsNumber, 8, 4000, 5000);
        yearsListAdapter.add(listYearsNumber, 7, 4000, 5000);

        //텍스트뷰 등록
        listYears = (TextView) yearsView.findViewById(R.id.yearsText);
        listYears.setText(listYearsNumber + "년");

        //이미지 버튼 등록
        ImageView leftMonthButton = (ImageView) yearsView.findViewById(R.id.leftyearsButton);
        leftMonthButton.setOnClickListener(this);
        ImageView rightMonthButton = (ImageView) yearsView.findViewById(R.id.rightyearsButton);
        rightMonthButton.setOnClickListener(this);

        return yearsView;
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle("연별 보기");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftyearsButton:
                if(listYearsNumber >= 1) {
                    listYearsNumber--;
                    listYears.setText(listYearsNumber + "년");
                    yearsListAdapter.setYear(listYearsNumber);

                }
                break;
            case R.id.rightyearsButton:
                listYearsNumber++;
                listYears.setText(listYearsNumber + "년");
                yearsListAdapter.setYear(listYearsNumber);
                break;
        }
    }
}
