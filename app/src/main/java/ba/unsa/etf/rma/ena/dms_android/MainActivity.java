package ba.unsa.etf.rma.ena.dms_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import ba.unsa.etf.rma.ena.dms_android.activities.LoginActivity;
import ba.unsa.etf.rma.ena.dms_android.classes.LoggedIn;
import ba.unsa.etf.rma.ena.dms_android.views.DokumentManager;
import ba.unsa.etf.rma.ena.dms_android.views.KorisnikManager;
import ba.unsa.etf.rma.ena.dms_android.views.UlogaManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper viewFlipper;
    DokumentManager dokumentManager;
    KorisnikManager korisnikManager;
    UlogaManager ulogaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent getLoggedIn = getIntent();
        LoggedIn loggedIn = getLoggedIn.getExtras().getParcelable("loggedIn");
        Log.i("AAA", loggedIn.getIme());
        //TODO sredi meniiii!!!!!!!!!!!!!!!!!!!

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setDisplayedChild(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_dokumenti) {
            //TODO all_dokumenti
            viewFlipper.setDisplayedChild(1);
            dokumentManager = new DokumentManager(this);
        } else if (id == R.id.nav_korisnici) {
            //TODO all_korisnici
            viewFlipper.setDisplayedChild(2);
            korisnikManager = new KorisnikManager(this);
        } else if (id == R.id.nav_uloge) {
            //TODO all_uloge
            viewFlipper.setDisplayedChild(3);
            ulogaManager = new UlogaManager(this);
        }
        else{
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
