package com.sinbaddrinkshop.drinkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.nex3z.notificationbadge.NotificationBadge;
import com.sinbaddrinkshop.drinkshop.Adapter.CategoryAdapter;
import com.sinbaddrinkshop.drinkshop.Model.Banner;
import com.sinbaddrinkshop.drinkshop.Model.Category;
import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.database.DataSource.CartRepository;
import com.sinbaddrinkshop.drinkshop.database.DataSource.FavoriteRepository;
import com.sinbaddrinkshop.drinkshop.database.Local.CartDatasource;
import com.sinbaddrinkshop.drinkshop.database.Local.FavoriteDatasource;
import com.sinbaddrinkshop.drinkshop.database.Local.RoomDatabase;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.sinbaddrinkshop.drinkshop.utils.SharedPrefManager;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView setName, setEmail;

    SliderLayout sliderLayout;


    RecyclerView list_menu;


    NotificationBadge badge;

    ImageView cart_icon;

    //API
    APIService mApiServices;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mApiServices = Common.getApiService();
        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        list_menu = (RecyclerView) findViewById(R.id.menu_list);
        list_menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_menu.setHasFixedSize(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // if user not login
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }

        View headerView = navigationView.getHeaderView(0);
        setName = (TextView) headerView.findViewById(R.id.setName);
        setName.setText(SharedPrefManager.getInstance(this).getUser().getName());
        setEmail = (TextView) headerView.findViewById(R.id.setEmail);
        setEmail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());


        getBannerImage();
        getCategoryMenu();


        //save newest topping
        getToppingList();

        //save database
        initDb();


    }

    public void initDb() {

        Common.roomDatabase = RoomDatabase.getInstance(this);
        Common.cartRepository = CartRepository
                .getInstance(CartDatasource.getInstance(Common.roomDatabase.cartDAO()));

        Common.favoriteRepository = FavoriteRepository
                .getInstance(FavoriteDatasource.getInstance(Common.roomDatabase.favoriteDAO()));
    }

    private void getToppingList() {
        compositeDisposable.add(mApiServices.getDrinkById(Common.TOPPING_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Drink>>() {
                    @Override
                    public void accept(List<Drink> drinks) throws Exception {
                        Common.ToppingList = drinks;
                    }
                }));
    }

    private void getCategoryMenu() {
        compositeDisposable.add(mApiServices.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        displayCategoryMenu(categories);
                    }
                }));
    }

    private void displayCategoryMenu(List<Category> categories) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        list_menu.setAdapter(categoryAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void getBannerImage() {
        compositeDisposable.add(mApiServices.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        displayImage(banners);
                    }
                }));
    }

    private void displayImage(List<Banner> banners) {

        HashMap<String, String> bannerMap = new HashMap<>();
        for (Banner item : banners)
            bannerMap.put(item.getName(), item.getLink());

        for (String name : bannerMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, SignInActivity.class));
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
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        View view = menu.findItem(R.id.cart_menu).getActionView();
        badge = (NotificationBadge) view.findViewById(R.id.badge);
        cart_icon = (ImageView) view.findViewById(R.id.cart_icon);

        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
        getMenuInflater().inflate(R.menu.home, menu);

        updateCurCount();

        return true;
    }

    private void updateCurCount() {
        if (badge == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Common.cartRepository.countCartItem() == 0) {
                    badge.setVisibility(View.INVISIBLE);
                } else {
                    badge.setVisibility(View.VISIBLE);
                    badge.setText(String.valueOf(Common.cartRepository.countCartItem()));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_menu) {

            return true;
        } else if (id == R.id.search_bar) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cart_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {
            startActivity(new Intent(getApplicationContext(), FavoriteListActivity.class));

        } else if (id == R.id.nav_signout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateCurCount();
    }
}
