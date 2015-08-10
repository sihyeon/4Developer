package com.project4D.fdpay.view;

import android.app.Fragment;

/**
 * Created by Somin Lee on 2015-08-09.
 */
public class DrawerFragment extends Fragment{
    protected Drawer drawer;
    protected int drawerItemPosition = -1;

    public DrawerFragment offerDrawer(Drawer drawer, int drawerItemPosition) {
        this.drawer = drawer;
        this.drawerItemPosition = drawerItemPosition;
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (drawer != null)
            drawer.setSelectedItem(drawerItemPosition);
    }
}
