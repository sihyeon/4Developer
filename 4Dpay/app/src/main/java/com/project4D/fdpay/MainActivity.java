package com.project4D.fdpay;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.project4D.fdpay.model.CreditCardInfo;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.HttpPoster;
import com.project4D.fdpay.util.HttpPosterCallBack;


/**
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 10.1.1 (2015-09-19)
 * @see this Activity is the main Activity - card and house holder attatch in this activity,
 */

public class MainActivity extends AppCompatActivity {
    public Drawer drawer;
    public static Context mainActivityContext;
    public HouseHolderStatus houseHolderStatus = new HouseHolderStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityContext = this;

        drawer = new Drawer(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
////////////////////////////////////////TEST CODE/////////////////////////////////////
        CreditCardInfo ci = new CreditCardInfo();
        ci.setCardName("TESTER");
        ci.setCardNum("123456789");
        ci.setCardValid(1234);
        ci.setCvc(000);
        ci.setPassword(0101);
        HttpPoster.executePOST(ci, new HttpPosterCallBack() {
            @Override
            public void onSuccess(String response) {
                new AlertDialogHelper(MainActivity.this)
                        .setMessage("성공")
                        .setPositiveButton("확인", null)
                        .build();
            }

            @Override
            public void onError(Throwable error) {
                new AlertDialogHelper(MainActivity.this)
                        .setMessage("실패")
                        .setPositiveButton("확인", null)
                        .build();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
    }

    public void transactFragment(Fragment fragment, String tag) {
        getFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment, tag).addToBackStack(tag).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Handle drawer
            case android.R.id.home:
                if (drawer.isDrawerOpen())
                    drawer.closeDrawer();
                else
                    drawer.openDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        // Handle Drawer
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        }
        // Prevent one-back activity finishing
        else {
            // one-back
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                Toast.makeText(
                        MainActivity.this,
                        "한번 더 뒤로가기 버튼을 누르시면 앱이 종료됩니다.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            // two-back
            else {
                super.onBackPressed();
            }
        }
    }

    public void setActionBarTitle(String myname) {
        super.getSupportActionBar().setTitle(myname);
    }

    public void setDrawerLastSelectedItem(int selectedItem){
        drawer.lastDrawerSelectedItem = selectedItem;
    }

    private class Drawer {
        private com.mikepenz.materialdrawer.Drawer drawer;
        private Activity activity;
        public int lastDrawerSelectedItem = -1;

        private Drawer(Activity activity) {
            this.activity = activity;
            createDrawer();
        }

        private void createDrawer() {
            final ProfileDrawerItem p1 = new ProfileDrawerItem()
                    //TODO generate userInfo method later..
                    .withName("Tester")
                    .withEmail("Tester@gmail.com")
                    .withIcon(activity.getResources().getDrawable(R.drawable.banner_test));

            AccountHeader headerResult = new AccountHeaderBuilder()
                    .withActivity(activity)
                    .withCompactStyle(false)
                    .withTextColorRes(R.color.md_black_1000)
                    .addProfiles(p1,
                            new ProfileSettingDrawerItem().withName("Sign out").withDescription("Sign out")
                                    .withIcon(new IconicsDrawable(activity, GoogleMaterial.Icon.gmd_verified_user)
                                            .actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(1),
                            new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings))
                    .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                        @Override
                        public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                            return false;
                        }
                    })
                    .build();

            drawer = new DrawerBuilder()
                    .withActivity(activity)
                    .withAccountHeader(headerResult)
                    .withTranslucentStatusBar(false)
                    .withActionBarDrawerToggle(false)
                    .withSelectedItem(-1)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("카드").withIcon(FontAwesome.Icon.faw_credit_card).withEnabled(false).withCheckable(false).withTextColor(R.color.hintColor),
                            new DividerDrawerItem(),
                            //TODO make Setting List Later..
                            new SecondaryDrawerItem().withName("신용 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(1),
                            new SecondaryDrawerItem().withName("포인트 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(2),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName("가계부").withIcon(FontAwesome.Icon.faw_area_chart).withEnabled(false).withCheckable(false).withTextColor(R.color.hintColor),
                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("쓰기").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(3),
                            new SecondaryDrawerItem().withName("월별보기").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(4),
                            new SecondaryDrawerItem().withName("연별보기").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(5),
                            new SecondaryDrawerItem().withName("분류별보기").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(6),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName("환경설정").withIcon(FontAwesome.Icon.faw_edit)
                    )
                    .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                            // Ignore event when this drawer's activity equals target activity
                            if (drawerItem.getIdentifier() == lastDrawerSelectedItem)
                                return false;

                            Bundle b = new Bundle();
                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    transactFragment(new CreditCardFragment(), "Credit");
                                    lastDrawerSelectedItem = 1;
                                    break;
                                case 2:
                                    transactFragment(new PointCardFragment(), "Point");
                                    lastDrawerSelectedItem = 2;
                                    break;
                                case 3:
                                    transactFragment(new WritingFragment(), "Writing");
                                    lastDrawerSelectedItem = 3;
                                    break;
                                case 4:
                                    transactFragment(new CalendarFragment(), "Calendar");
                                    lastDrawerSelectedItem = 4;
                                    break;
                                case 5:
                                    transactFragment(new YearsFragment(), "Year");
                                    lastDrawerSelectedItem = 5;
                                    break;
                                case 6:
                                    transactFragment(new CategorizationFragment(), "Category");
                                    lastDrawerSelectedItem = 6;
                                    break;
                            }
                            return false;
                        }
                    })
                    .build();
        }


        public void openDrawer() {
            drawer.openDrawer();
        }

        public boolean isDrawerOpen() {
            return drawer.isDrawerOpen();
        }

        public void closeDrawer() {
            drawer.closeDrawer();
        }

        public void setSelectedItem(int position) {
            drawer.setSelection(position);
        }
    }
}
