package com.project4D.fdpay;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.project4D.fdpay.internal.HouseHolderStatus;

import java.util.ArrayList;

//DemoBase에 있는 데이터를 기초로 원형 그래프를 만듦
public class CategorizationFragment extends DemoBase implements OnSeekBarChangeListener,
        OnChartValueSelectedListener, View.OnClickListener{

    private PieChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private TextView categorizationMonth;
    private Typeface tf;

    private int categorizationMonthNumber = HouseHolderStatus.TODAY_Month;
    private int categorizationYearsNumber = HouseHolderStatus.TODAY_YEAR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); */
        //사용할 xml


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View categorizationView = inflater.inflate(R.layout.fragment_categorization_view, container, false);
        setActivityTitle("분류별 보기");
/*
        tvX = (TextView) categorizationView.findViewById(R.id.tvXMax);
        tvY = (TextView) categorizationView.findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) categorizationView.findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) categorizationView.findViewById(R.id.seekBar2);

        mSeekBarY.setProgress(10);

        mSeekBarX.setOnSeekBarChangeListener(this);
        mSeekBarY.setOnSeekBarChangeListener(this);
*/
        mChart = (PieChart) categorizationView.findViewById(R.id.pieChart);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");


        mChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        mChart.setCenterTextTypeface(Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf"));

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setTransparentCircleColor(Color.WHITE);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        mChart.setCenterText("카테고리별\n원그래프");

        setData(3, 100);

        mChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        //몇월인지 텍스트 등록
        categorizationMonth = (TextView) categorizationView.findViewById(R.id.categorizeMonth);
        categorizationMonth.setText(categorizationYearsNumber + "년 " + categorizationMonthNumber + "월");
        //버튼 생성 및 버튼 리스너 등록
        ImageView leftMonthButton = (ImageView) categorizationView.findViewById(R.id.leftMonthButton);
        leftMonthButton.setOnClickListener(this);
        ImageView rightMonthButton = (ImageView) categorizationView.findViewById(R.id.rightMonthButton);
        rightMonthButton.setOnClickListener(this);

        return categorizationView;
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityTitle("분류별 보기");
    }

    //버튼 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftMonthButton:
                if(categorizationMonthNumber != 1) {
                    categorizationMonthNumber--;
                } else if (categorizationMonthNumber == 1){
                    categorizationYearsNumber--;
                    categorizationMonthNumber = 12;
                }
                categorizationMonth.setText(categorizationYearsNumber + "년 " + categorizationMonthNumber + "월");
                setData(3, 100);
                break;
            case R.id.rightMonthButton:
                if(categorizationMonthNumber != 12) {
                    categorizationMonthNumber++;
                } else if (categorizationMonthNumber == 12){
                    categorizationYearsNumber++;
                    categorizationMonthNumber = 1;
                }
                categorizationMonth.setText(categorizationYearsNumber + "년 " + categorizationMonthNumber + "월");
                setData(3, 100);
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText("" + (mSeekBarX.getProgress() + 1));
        tvY.setText("" + (mSeekBarY.getProgress()));

        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        //몇퍼인지 정하는 구문
        for (int i = 0; i < count + 1; i++) {
            yVals1.add(
                    new Entry((float) (Math.random() * mult) + mult / 5, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet value: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }


    // private void removeLastEntry() {
    //
    // PieData data = mChart.getDataOriginal();
    //
    // if (data != null) {
    //
    // PieDataSet set = data.getDataSet();
    //
    // if (set != null) {
    //
    // Entry e = set.getEntryForXIndex(set.getEntryCount() - 1);
    //
    // data.removeEntry(e, 0);
    // // or remove by value
    // // mData.removeEntry(xIndex, dataSetIndex);
    //
    // mChart.notifyDataSetChanged();
    // mChart.invalidate();
    // }
    // }
    // }
    //
    // private void addEntry() {
    //
    // PieData data = mChart.getDataOriginal();
    //
    // if (data != null) {
    //
    // PieDataSet set = data.getDataSet();
    // // set.addEntry(...);
    //
    // data.addEntry(new Entry((float) (Math.random() * 25) + 20f,
    // set.getEntryCount()), 0);
    //
    // // let the chart know it's data has changed
    // mChart.notifyDataSetChanged();
    //
    // // redraw the chart
    // mChart.invalidate();
    // }
    // }
}

