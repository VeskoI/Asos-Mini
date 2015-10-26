package com.veskoiliev.asosmini;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.widget.GridAutoFitLayoutManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private static final String TAG = "vesko";

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.products_list)
    RecyclerView mRecyclerView;

    private ProductsAdapter mAdapter;
    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        mPresenter = new MainActivityPresenterImpl(this);
        if (savedInstanceState == null) {
            mPresenter.onCreate(true);
        } else {
            mPresenter.onRecreate(true);
        }
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);


        int paddingPx = getResources().getDimensionPixelSize(R.dimen.products_list_item_padding);
        int columnWidthPx = getResources().getDimensionPixelSize(R.dimen.products_list_item_width);
        mRecyclerView.addItemDecoration(new ProductsItemDecoration(paddingPx));
        mRecyclerView.setLayoutManager(new GridAutoFitLayoutManager(this, columnWidthPx));
        mAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d(TAG, "onNavigationItemSelected: clicked: " + id);
        mPresenter.onCategorySelected(id);

        // TODO: 10/26/2015 fix WOMEN / MEN buttons
//        if (id == R.id.menu_btn_women) {
//            mPresenter.onMenuWomenClicked();
//            // Handle the camera action
//        } else if (id == R.id.menu_btn_men) {
//            mPresenter.onMenuMenClicked();
//        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        Menu menu = mNavigationView.getMenu();

        if (!categories.isEmpty()) {
            menu.clear();
            for (Category c : categories) {
                mNavigationView.getMenu().add(0, (int)c.getDatabaseId(), 0, c.getName());
            }
        }
    }

    @Override
    public void onProductsLoaded(List<Product> products) {
        mAdapter.setData(products);
    }

    @Override
    public void onMenuItemSelected(long id) {
        Menu menu = mNavigationView.getMenu();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setChecked(menuItem.getItemId() == (int)id);
        }
    }

    @Override
    public void openDrawer() {
        mDrawer.openDrawer(GravityCompat.START);
    }
}
