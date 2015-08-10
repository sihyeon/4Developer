package com.project4D.fdpay.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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
import com.project4D.fdpay.AccountActivity;
import com.project4D.fdpay.CardActivity;
import com.project4D.fdpay.R;
import com.project4D.fdpay.util.IntentUtil;

/**
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 2 (2015-08-08)
 */
public class Drawer {
    public static final int POINT_CARD_POSITION = 3;
    public static final int CREDIT_CARD_POSITION = 2;
    public static int lastDrawerSelectedItem = -1;
    private com.mikepenz.materialdrawer.Drawer drawer;
    private Activity activity;

    public static Drawer newInstance(Activity activity) {
        return new Drawer(activity);
    }

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
                .withSelectedItem(lastDrawerSelectedItem)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("카드").withIcon(FontAwesome.Icon.faw_credit_card).withCheckable(false),
                        new DividerDrawerItem(),
                        //TODO make Setting List Later..
                        new SecondaryDrawerItem().withName("신용 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(1),
                        new SecondaryDrawerItem().withName("포인트 카드").withIcon(FontAwesome.Icon.faw_list_ol).withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("가계부").withIcon(FontAwesome.Icon.faw_area_chart).withIdentifier(3).withTag(AccountActivity.class.getName()),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("1").withIcon(FontAwesome.Icon.faw_list_ol),
                        new SecondaryDrawerItem().withName("2").withIcon(FontAwesome.Icon.faw_list_ol),
                        new SecondaryDrawerItem().withName("3").withIcon(FontAwesome.Icon.faw_list_ol),
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
                            case 1: b.putString("fragment", "CreditCardFragment");
                                IntentUtil.pullActivity(activity, CardActivity.class, b); lastDrawerSelectedItem = 2; break;
                            case 2: b.putString("fragment", "PointCardFragment");
                                IntentUtil.pullActivity(activity, CardActivity.class); lastDrawerSelectedItem = 3; break;
                            case 3: IntentUtil.pullActivity(activity, AccountActivity.class); lastDrawerSelectedItem = 4; break;
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
