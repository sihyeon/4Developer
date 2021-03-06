package com.project4D.fdpay;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project4D.fdpay.internal.HouseHolderStatus;

/**
 * Created by Jaeung on 2015-08-04.
 */

//

public class WritingFragment extends Fragment implements View.OnClickListener {
    private HouseHolderStatus houseHolderStatus;
    private int writingYear = HouseHolderStatus.TODAY_YEAR;
    private int writingMonth = HouseHolderStatus.TODAY_Month;
    private int writingDay = HouseHolderStatus.TODAY_DAY;
    private DatePickerDialog datePickerDialog;
    private CategorizationDialog categorizationDialog;

    private View writingView;
    private EditText moneyEdit;
    private EditText breakdownEdit;
    private EditText categorizationView;
    private EditText memoEdit;
    private TextView writingDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        writingView = inflater.inflate(R.layout.fragment_writing_view, container, false);
        setActivityTitle("쓰기");

        //텍스트뷰 등록
        writingDate = (TextView) writingView.findViewById(R.id.writingDayText);
        writingDate.setText(writingYear + "." + writingMonth + "." + writingDay);

        ImageView writingDateSelector = (ImageView) writingView.findViewById(R.id.writingDateSelector);
        writingDateSelector.setOnClickListener(this);
        //버튼 생성 및 리스너 등록
        Button buttonIncome = (Button) writingView.findViewById(R.id.button_Income);
        buttonIncome.setOnClickListener(this);

        Button buttonSpend = (Button) writingView.findViewById(R.id.button_Spend);
        buttonSpend.setOnClickListener(this);

        Button buttonSaveAndContinue = (Button) writingView.findViewById(R.id.button_SaveAndContinue);
        buttonSaveAndContinue.setOnClickListener(this);

        Button buttonSaveAndEnd = (Button) writingView.findViewById(R.id.button_SaveAndEnd);
        buttonSaveAndEnd.setOnClickListener(this);

        moneyEdit = (EditText) writingView.findViewById(R.id.moneyEdit);
        breakdownEdit = (EditText) writingView.findViewById(R.id.breakdownEdit);
        memoEdit = (EditText) writingView.findViewById(R.id.memoEdit);

        categorizationView = (EditText) writingView.findViewById(R.id.categorizationResult);
        categorizationView.setText("카테고리선택");
        categorizationView.setInputType(0);
        categorizationView.setOnClickListener(this);

        return writingView;
    }

    public DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            writingYear = year;
            writingMonth = monthOfYear + 1;
            writingDay = dayOfMonth;

            updateWringDate();
        }
    };

    //카테고리 다이얼로그 종료시 불리는 리스너
    public CategorizationDialog.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (houseHolderStatus.categorizationTextStatus) {
                categorizationView.setText(houseHolderStatus.categorizationText);
                houseHolderStatus.categorizationTextStatus = false;
            }
        }
    };

    //data를 변경하는 함수
    private void updateWringDate() {
        writingDate.setText(writingYear + "." + writingMonth + "." + writingDay);
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityTitle("쓰기");

        if (houseHolderStatus.writingInfoUseStatus && houseHolderStatus.dateUseStatus) {
            breakdownEdit.setText(houseHolderStatus.breakdown);
            categorizationView.setText(houseHolderStatus.categorization);
            moneyEdit.setText(houseHolderStatus.amount + "");
            houseHolderStatus.writingInfoUseStatus = false;

            writingYear = houseHolderStatus.year;
            writingMonth = houseHolderStatus.month;
            writingDay = houseHolderStatus.day;
            houseHolderStatus.dateUseStatus = false;

            writingDate.setText(writingYear + "." + writingMonth + "." + writingDay);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
            datePickerDialog = null;
        }
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
                categorizationView.getText().toString();
                memoEdit.getText().toString();
                break;
            //날짜 옆의 달력을 눌렀을 경우
            case R.id.writingDateSelector:
                datePickerDialog = new DatePickerDialog(WritingFragment.this.getActivity(), R.style.DialogTheme ,mDateSetListener, writingYear, writingMonth - 1, writingDay);
                datePickerDialog.show();
                break;
            //카테고리를 눌렀을 경우
            case R.id.categorizationResult:
                categorizationDialog = new CategorizationDialog(WritingFragment.this.getActivity());
                categorizationDialog.setOnDismissListener(onDismissListener);
                categorizationDialog.show();
                break;
        }
    }



}

