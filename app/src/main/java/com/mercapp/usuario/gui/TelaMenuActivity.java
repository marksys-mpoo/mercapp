package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.CadastroSupermercadoAuto;
import com.mercapp.usuario.gui.fragments.MapaFragments;

import static com.mercapp.R.id;

public class TelaMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;

    private FragmentManager fragmentManager;
    private Session session = Session.getInstanciaSessao();
    private Context _context = TelaMenuActivity.this;

    TextView emailUsuario;
    TextView nomePessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setEmailNavigation();
        abrirMapaFragments();

    }

    private void abrirMapaFragments() {
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(id.container, new MapaFragments(), "Mapa");
        transaction.commitAllowingStateLoss();
    }

    private void setEmailNavigation() {
        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        String email = session.getUsuarioLogado().getEmail().toString();
        String nome = session.getPessoaLogada().getNome().toString();

        nomePessoa = (TextView)header.findViewById(id.nomeLogado);
        nomePessoa.setText(nome);
        emailUsuario = (TextView)header.findViewById(id.emailLogado);
        emailUsuario.setText(email);
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
        getMenuInflater().inflate(R.menu.tela_menu, menu);
        return true;
    }

    private void showFragment (Fragment fragment, String name) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment, name);

        transaction.commit();

    }

    /** Menu do navigation drawer
     * **/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.nav_historico:
                Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_ajuda:
                Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_carrinho:
                Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_descontos:
                Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_configuracao:
                Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mapa:
               // showFragment(new MapaFragments(), "MapGPS" );
                getFragmentManager().popBackStack();
                break;
            case R.id.logoutApp:
                session.Logout();
                Intent changeToTelaLogin = new Intent(TelaMenuActivity.this, LoginActivity.class);
                TelaMenuActivity.this.startActivity(changeToTelaLogin);
                finish();
                break;
            case R.id.nav_cadastro_supermercado:
                Intent changeToTelaCadastroSupermercado = new Intent(TelaMenuActivity.this, CadastroSupermercadoAuto.class);
                TelaMenuActivity.this.startActivity(changeToTelaCadastroSupermercado);
                finish();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
