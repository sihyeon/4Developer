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

    private int listYearsNumber = HouseHolderStatus.TODAY_YEAR;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View yearsView = inflater.inflate(R.layout.fragment_years_view, container, false);
        //액션바 이름 변경
        setActivityTitle("연별 보기");
        //리스트뷰 연결
        mListView = (ListView) yearsView.findViewById(R.id.listView);

        listAdd();

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

    private void listAdd(){
        //어댑터 생성
        yearsListAdapter = new YearsListAdapter();

        //어댑터 연결
        mListView.setAdapter(yearsListAdapter);
        for (int i = 12; i >= 1; i--) {
            yearsListAdapter.add(listYearsNumber, i, 4000, 5000);
        }
    }

    private void listRefresh(){
        yearsListAdapter.removeALL();
        yearsListAdapter = null;
        listAdd();
    }

    @Override
    public void onStart() {
        super.onStart();
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
                    listRefresh();
                }
                break;
            case R.id.rightyearsButton:
                listYearsNumber++;
                listYears.setText(listYearsNumber + "년");
                yearsListAdapter.setYear(listYearsNumber);
                listRefresh();
                break;
        }
    }
}
