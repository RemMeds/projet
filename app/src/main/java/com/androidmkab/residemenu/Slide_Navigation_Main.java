package com.androidmkab.residemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


public class Slide_Navigation_Main extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private Slide_Navigation_Main mContext;
    private ResideMenuItem itemAccueil;
    private ResideMenuItem itemProfil;
    private ResideMenuItem itemPilulier;
    private ResideMenuItem itemParametres;
    private ResideMenuItem itemRepertoire;
    private ResideMenuItem itemHistorique;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_navigation_main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new AccueilFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
//       resideMenu.setUse3D(true);resideMenu.setBackground(R.drawable.blue);
        resideMenu.setUse3D(true);resideMenu.setBackground(R.drawable.menu2);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
       // resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemAccueil     = new ResideMenuItem(this, R.drawable.ic_home_dark,     "Accueil");
        itemProfil  = new ResideMenuItem(this, R.drawable.ic_dark_profile,  "Profil");
        itemPilulier  = new ResideMenuItem(this, R.drawable.drugs,  "Pilulier");
        itemRepertoire = new ResideMenuItem(this, R.drawable.phonebook, "Répertoire");
        itemHistorique  = new ResideMenuItem(this, R.drawable.icon_calendar,  "Historique");
        itemParametres = new ResideMenuItem(this, R.drawable.icon_settings, "Paramètres");

        itemAccueil.setOnClickListener(this);
        itemPilulier.setOnClickListener(this);
        itemRepertoire.setOnClickListener(this);
        itemProfil.setOnClickListener(this);
        itemHistorique.setOnClickListener(this);
        itemParametres.setOnClickListener(this);


        resideMenu.addMenuItem(itemAccueil, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfil, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPilulier, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemRepertoire, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHistorique, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemParametres, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
//        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
//        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
//            }
//        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return resideMenu.dispatchTouchEvent(ev);
//    }

    @Override
    public void onClick(View view) {

        if (view == itemAccueil){
            changeFragment(new AccueilFragment());
        }else if (view == itemProfil){
            changeFragment(new ProfilFragment());
        }else if (view == itemPilulier){
            changeFragment(new PilulierFragment());
        }else if (view == itemRepertoire){
            changeFragment(new RepertoireFragment());
        }else if (view == itemHistorique){
            changeFragment(new HistoriqueFragment());
        }else if (view == itemParametres){
            changeFragment(new ParametresFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            //Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
