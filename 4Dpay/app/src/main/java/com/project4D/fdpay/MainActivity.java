package com.project4D.fdpay;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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


/**
 * 1. extends AppCompatAcitivity
 * 2. getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 * getSupportActionBar().setHomeButtonEnabled(false);
 * 3. onOptionsItemSelected(MenuItem item)
 *
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 10.1 (2015-08-10)
 * @see this Activity is the main Activity - card in this app, and I am fucking crazy to make.
 */

public class MainActivity extends ActionBarActivity {
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = new Drawer(this);
        //transactFragment(new PointCardFragment());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void transactFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.card_fragment, fragment).addToBackStack(null).commit();
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

    private class Drawer {
        private com.mikepenz.materialdrawer.Drawer drawer;
        private Activity activity;
        private int lastDrawerSelectedItem = -1;

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
                            new PrimaryDrawerItem().withName("카드").withIcon(FontAwesome.Icon.faw_credit_card).withEnabled(false).withCheckable(false),
                            new DividerDrawerItem(),
                            //TODO make Setting List Later..
                            new SecondaryDrawerItem().withName("신용 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(1),
                            new SecondaryDrawerItem().withName("포인트 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(2),
                            new DividerDrawerItem(),
                            new PrimaryDrawerItem().withName("가계부").withIcon(FontAwesome.Icon.faw_area_chart).withIdentifier(3),
                            new DividerDrawerItem(),
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
                                    transactFragment(new CreditCardFragment());lastDrawerSelectedItem = 1; break;
                                case 2:
                                    transactFragment(new PointCardFragment()); lastDrawerSelectedItem = 2; break;
                                case 3:
                                    lastDrawerSelectedItem = 3;
                                    break;
                                case 4:
                                    transactFragment(new CalendarFragment());
                                    lastDrawerSelectedItem = 4;
                                    break;
                                case 5:
                                    transactFragment(new YearsFragment());
                                    lastDrawerSelectedItem = 5;
                                    break;
                                case 6:
                                    transactFragment(new CategorizeFragment());
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
