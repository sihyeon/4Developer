package com.project4D.fdpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.UListener;
import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.ViewUtil;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 **/
public class ShowCardInfoActivity extends ActionBarActivity {

    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardTableManager cm = new CreditCardTableManager(this);
    private EventManager eventManager = EventManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_info);
        final Bundle bundle = getIntent().getExtras();

        MultiFormatWriter gen = new MultiFormatWriter();
        try {
            final int WIDTH = 1400;
            final int HEIGHT = 560;
            BitMatrix bytemap = gen.encode(cm.getCardNumById(bundle.getInt("ID")), BarcodeFormat.CODE_128, WIDTH, HEIGHT);
            Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
            for (int i = 0 ; i < WIDTH ; ++i)
                for (int j = 0 ; j < HEIGHT ; ++j) {
                    bitmap.setPixel(i, j, bytemap.get(i,j) ? Color.BLACK : Color.WHITE);
                }
            ImageView view = (ImageView) findViewById(R.id.showcardinfo_barcode);
            view.setImageBitmap(bitmap);
            view.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        vu.textView(R.id.showcardinfo_cardname).setText(cm.getCardNameById(bundle.getInt("ID")));
        vu.button(R.id.showcardinfo_editcardbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowCardInfoActivity.this, EditCardNameActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
        vu.button(R.id.showcardinfo_deletecardbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialogHelper(ShowCardInfoActivity.this)
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cm.deleteCardInfoById(bundle.getInt("ID"));

                                Bundle b = new Bundle();
                                b.putInt("ID", bundle.getInt("ID"));
                                for (UListener ul : eventManager.getListener("DELETE_CREDIT")) {
                                    ul.onSuccess(b);
                                }
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .build();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

