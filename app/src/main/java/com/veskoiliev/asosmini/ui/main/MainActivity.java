package com.veskoiliev.asosmini.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.veskoiliev.asosmini.R;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.BaseActivity;
import com.veskoiliev.asosmini.ui.singleproduct.SingleProductActivity;
import com.veskoiliev.asosmini.widget.GridAutoFitLayoutManager;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView, ProductSelectListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.products_list)
    RecyclerView mRecyclerView;

    private Button mGenderMen;
    private Button mGenderWomen;

    private ProductsAdapter mAdapter;
    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        mPresenter = new MainActivityPresenterImpl(this);

        // If we're creating the Activity from scratch we need to notify the presenter.
        // Otherwise the onRestoreInstanceState() will handle the initialization.
        if (savedInstanceState == null) {
            mPresenter.onCreate();
        }
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();


        // NavigationView - we need to add the Header manually to capture the Women/Men buttons.
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, mNavigationView, false);

        mGenderMen = (Button) headerView.findViewById(R.id.menu_btn_men);
        mGenderMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onMenuMenClicked();
            }
        });

        mGenderWomen = (Button) headerView.findViewById(R.id.menu_btn_women);
        mGenderWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onMenuWomenClicked();
            }
        });
        mNavigationView.addHeaderView(headerView);


        // Init the RecyclerView.
        int paddingPx = getResources().getDimensionPixelSize(R.dimen.products_list_item_padding);
        int columnWidthPx = getResources().getDimensionPixelSize(R.dimen.products_list_item_width);
        mRecyclerView.addItemDecoration(new ProductsItemDecoration(paddingPx));
        mRecyclerView.setLayoutManager(new GridAutoFitLayoutManager(this, columnWidthPx));
        mAdapter = new ProductsAdapter(this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorites) {
            mPresenter.onFavoritesClicked();
            return true;
        } else if (id == R.id.action_bag) {
            mPresenter.onBagClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mPresenter.onCategorySelected(id);

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

    @Override
    public void toggleGenderButtons(boolean displayingMen) {
        mGenderMen.setEnabled(!displayingMen);
        mGenderWomen.setEnabled(displayingMen);
    }

    @Override
    public void openSingleProductPage(long productId) {
        startActivity(
                SingleProductActivity.getStartIntent(this, productId));
    }

    @Override
    public void onError(String errorMessage) {
        Snackbar.make(mDrawer, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDisplayFavorites(HashMap<Long, Product> favorites) {
        displayFavorites(favorites);
    }

    @Override
    public void onDisplayBag(HashMap<Long, Integer> bag) {
        displayBag(bag);
    }

    @Override
    public void onAddedToFavorites() {
        Snackbar.make(mToolbar, getString(R.string.added_to_favorites), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onProductSelected(long productId) {
        mPresenter.onProductSelected(productId);
    }

    @Override
    public void addProductToFavorites(long productId) {
        mPresenter.onProductToggleFavorites(productId);
    }
}
