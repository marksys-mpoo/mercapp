package com.mercapp.usuario.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.Session;
import com.mercapp.infra.Sobre;
import com.mercapp.supermercado.gui.ListarItensCarrinhoActivity;
import com.mercapp.usuario.gui.fragments.Mapa;

import static com.mercapp.R.id;

public class TelaMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    private Session session = Session.getInstanciaSessao();

    @Override
    protected final  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setEmailNavigation();
        abrirMapaFragments();

    }

    private void abrirMapaFragments() {
//        GoogleApiClient client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(id.container, new Mapa(), "Mapa");
        transaction.commitAllowingStateLoss();
    }

    private void setEmailNavigation() {
        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        String email = session.getUsuarioLogado().getEmail().toString();
        String nome = session.getPessoaLogada().getNome().toString();

        TextView nomePessoa = (TextView)header.findViewById(id.nomeLogado);
        nomePessoa.setText(nome);
        TextView emailUsuario = (TextView)header.findViewById(id.emailLogado);
        emailUsuario.setText(email);
    }

    @Override
    public final  void onBackPressed() {
        confirmarSaida();
    }

    @Override
    public  final boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_menu, menu);
        return true;
    }

    /** Menu do navigation drawer
     * **/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public final  boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_perfil:
                perfil();
                break;
            case R.id.nav_historico:
                aviso();
                break;
            case R.id.nav_sobre:
                sobre();
                break;
            case R.id.nav_carrinho:
                Intent voltarMenu = new Intent(TelaMenu.this, ListarItensCarrinhoActivity.class);
                startActivity(voltarMenu);
                finish();

//                aviso();
                break;
            case R.id.nav_descontos:
                aviso();
                break;
            case R.id.nav_configuracao:
                aviso();
                break;
            case R.id.nav_mapa:
                getFragmentManager().popBackStack();
                break;
            case R.id.logoutApp:
                confirmarSaida();
                break;
            case R.id.nav_adm:
                administrador();
                break;
            case R.id.nav_ajuda:
                ajuda();
                break;
        }
        drawer();
        return true;
    }

    private void perfil() {
        Intent perfil = new Intent(TelaMenu.this, CadastroPessoa.class);
        TelaMenu.this.startActivity(perfil);
        finish();
    }

    private void sobre() {
        Intent sobre = new Intent(TelaMenu.this, Sobre.class);
        TelaMenu.this.startActivity(sobre);
        finish();
    }

    private void administrador() {
        Intent changeToAdm = new Intent(TelaMenu.this, Administrador.class);
        TelaMenu.this.startActivity(changeToAdm);
        finish();
    }

    private void ajuda() {
        Intent changeToAjuda = new Intent(TelaMenu.this, Ajuda.class);
        TelaMenu.this.startActivity(changeToAjuda);
        finish();
    }

    private void drawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void aviso() {
        Toast.makeText(this, "Desculpe o transtorno, estamos implementando", Toast.LENGTH_SHORT).show();
        return;
    }


    private void confirmarSaida(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair");
        builder.setMessage("Deseja Realmente Sair?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent logoutapp = new Intent(TelaMenu.this, Login.class);
                TelaMenu.this.startActivity(logoutapp);
                finish();
            }
        });
        builder.setNegativeButton("Não", null);
        AlertDialog alerta = builder.create();
        alerta.show();
    }
}
