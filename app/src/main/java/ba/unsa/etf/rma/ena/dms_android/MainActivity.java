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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import ba.unsa.etf.rma.ena.dms_android.activities.AddKorisnikActivity;
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
    LoggedIn loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent getLoggedIn = getIntent();
        loggedIn = getLoggedIn.getExtras().getParcelable("loggedIn");
        Log.i("AAA", loggedIn.getIme());


        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setDisplayedChild(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setItems(navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void setItems(NavigationView navigationView) {

        String iIP = loggedIn.getIme()+ " " + loggedIn.getPrezime();
        View headerLayout = navigationView.getHeaderView(0);
        ((TextView) headerLayout.findViewById(R.id.textView_ime_i_prezime)).setText(iIP);
        ((TextView)headerLayout.findViewById(R.id.textView_korisnicko_ime)).setText(loggedIn.getKorisnickoIme());

        Menu navMenu = navigationView.getMenu();

        navigationView.getHeaderView(0).findViewById(R.id.textView_korisnicko_ime);

        if(loggedIn.getUloga()==3){
            navMenu.findItem(R.id.nav_korisnici).setVisible(false);
            navMenu.findItem(R.id.nav_uloge).setVisible(false);
        }
        else if(loggedIn.getUloga()==2){
            navMenu.findItem(R.id.nav_uloge).setVisible(false);
        }
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
        else if(id == R.id.nav_moj_profil) {
            Intent changeData = new Intent(MainActivity.this, AddKorisnikActivity.class);
            startActivity(changeData);
        }
        else if(id == R.id.nav_logout){
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public LoggedIn getLoggedIn(){
        return loggedIn;
    }


}
