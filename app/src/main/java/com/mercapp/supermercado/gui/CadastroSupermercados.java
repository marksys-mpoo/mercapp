package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;


public class CadastroSupermercados extends AppCompatActivity {

    private Context _context = CadastroSupermercados.this;
    private SupermercadoNegocio supermercadoNegocio;
    private Supermercado supermercadoCadastrado;
    private EditText etSupNome, etSupTelefone, etSupBuscaNome, etSupBuscaTelefone, etLogintude, etLatitude;
    private TextView etSupTextoNome, etSupTextoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_supermercado_auto);

        etSupNome = (EditText) findViewById(R.id.etSupNome);
        etSupTelefone = (EditText) findViewById(R.id.etSupTelefone);
        etSupBuscaNome = (EditText) findViewById(R.id.supBuscaNome);
        etSupTextoNome = (TextView) findViewById(R.id.supTextoNome);
        etSupTextoTelefone = (TextView) findViewById(R.id.supTextoTelefone);

    }

    public void cadastroDireto(View view) {

        Supermercado supermercado1 = CadastrarSupermercado(1,"Supermercado 1","1111");
        efetuarCadastroSupermercado(supermercado1);
        Supermercado supermercado2 = CadastrarSupermercado(1,"Supermercado 2","2222");
        efetuarCadastroSupermercado(supermercado2);
    }
    private Supermercado CadastrarSupermercado(Integer id, String nome, String telefone) {

        Supermercado supermercado = new Supermercado();
        supermercado.setId(id);
        supermercado.setNome(nome);
        supermercado.setTelefone(telefone);
        return supermercado;

    }

    public void efetuarCadastroSupermercado(Supermercado supermercado) {

        String nome = supermercado.getNome();
        String telefone = supermercado.getTelefone();

        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone);
            Toast.makeText(this, "Supermercados Cadastrados com sucesso\r\n - " + nome, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já exitente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarSupermercadoManual(View view) {

        String nome = etSupNome.getText().toString().trim();
        String telefone = etSupTelefone.getText().toString().trim();

        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone);
            Toast.makeText(this, "Supermercados Cadastrados com sucesso\r\n Faça o login - " + nome, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já existente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscaSupermercadoBD(View view) {

        String buscaNome = etSupBuscaNome.getText().toString().trim();

        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(buscaNome);
        if (supermercadoCadastrado != null) {
            etSupTextoNome.setText(supermercadoCadastrado.getNome());
            etSupTextoTelefone.setText(supermercadoCadastrado.getTelefone());
        } else {
            Toast.makeText(this, "Supermercado não cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeTelaCadatroSupermercadosToTelaAdministrador(View view) {
        Intent voltarMenu = new Intent(CadastroSupermercados.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }

}
