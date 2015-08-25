package com.project4D.fdpay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jaeung on 2015-08-04.
 */
public class WritingFragment extends Fragment implements View.OnClickListener {
    private View writingView;
    EditText moneyEdit;
    EditText breakdownEdit;
    EditText categorizeEdit;
    EditText memoEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        writingView = inflater.inflate(R.layout.fragment_writing_view, container, false);
        setActivityTitle("쓰기");

        //텍스트뷰 등록
        TextView MonthAndDay = (TextView) writingView.findViewById(R.id.writingDayText);
  /*      MonthAndDay.setText(((CalendarActivity) CalendarActivity.calendarContext).getSetMonth() + "."
                + ((CalendarActivity) CalendarActivity.calendarContext).getSetDay());
*/
        //버튼 생성 및 리스너 등록
        Button buttonIncome = (Button) writingView.findViewById(R.id.button_Income);
        buttonIncome.setOnClickListener(this);

        Button buttonSpend = (Button) writingView.findViewById(R.id.button_Spend);
        buttonSpend.setOnClickListener(this);

        Button buttonSaveAndContinue = (Button) writingView.findViewById(R.id.button_SaveAndContinue);
        buttonSaveAndContinue.setOnClickListener(this);

        Button buttonSaveAndEnd = (Button) writingView.findViewById(R.id.button_SaveAndEnd);
        buttonSaveAndEnd.setOnClickListener(this);

        moneyEdit = (EditText)writingView.findViewById(R.id.moneyEdit);
        breakdownEdit = (EditText)writingView.findViewById(R.id.breakdownEdit);
        categorizeEdit = (EditText)writingView.findViewById(R.id.categorizeEdit);
        memoEdit = (EditText)writingView.findViewById(R.id.memoEdit);

        return writingView;
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle("쓰기");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Income:
                break;
            case R.id.button_Spend:

                break;
            case R.id.button_SaveAndContinue:
                break;
            case R.id.button_SaveAndEnd:
                moneyEdit.getText().toString();
                breakdownEdit.getText().toString();
                categorizeEdit.getText().toString();
                memoEdit.getText().toString();
                break;
        }
    }
}
