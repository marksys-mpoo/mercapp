package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.ProdutoNegocio;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class CadastroProdutos extends AppCompatActivity {

    private static final String stringVazia = "";
    private static final String idDepartamento = "1";
    private Context _context = CadastroProdutos.this;
    private SupermercadoNegocio supermercadoNegocio;
    private Produto produtoCadastrado;
    private EditText setdescricao, setpreco,setnome, setSupermrecado ;
    private double preco;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
        setnome = (EditText) findViewById(R.id.edtNomeProduto);
        setdescricao = (EditText) findViewById(R.id.edtDescricaoProduto);
        setpreco = (EditText) findViewById(R.id.edtPrecoProduto);
        setSupermrecado = (EditText)findViewById(R.id.edtSupermercadoProduto);
        setnome.requestFocus();
    }

    public void pegarConteudoProdutos(View view) {

        String nome = setnome.getText().toString().trim();
        String descricao = setdescricao.getText().toString().trim();
        String nomeSupermercado = setSupermrecado.getText().toString().trim();
        if (validarCampos() && validarPrecoDouble()) {
            preco = Double.parseDouble(setpreco.getText().toString().trim());
            supermercadoNegocio = new SupermercadoNegocio(_context);
            Supermercado supermercado = supermercadoNegocio.buscaSupermercado(nomeSupermercado);
            if (supermercado != null){
                Produto produto = CriarProduto(nome, descricao, preco, supermercado, idDepartamento);
                efetuarCadastroProduto(produto);
            }else {
//                Toast.makeText(this, "Este supermercado não existe.", Toast.LENGTH_SHORT).show();
                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //define o titulo
//                builder.setTitle("");
                //define a mensagem
                builder.setMessage("Este supermercado não está Registrado, deseja cadastrar?");
                //define um botão como positivo
                builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
//                      chamarCadastroSupermercado();
                        Toast.makeText(getApplication(), "Para continuar é preciso ter o supermercado cadastrado.", Toast.LENGTH_SHORT).show();
                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplication(), "Para continuar é preciso ter o supermercado cadastrado.", Toast.LENGTH_SHORT).show();
                    }
                });
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        } else {
            Toast.makeText(this, "O cadastro não foi realizado.", Toast.LENGTH_SHORT).show();
        }

    }

    private void efetuarCadastroProduto(Produto produto) {
        ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
        produtoCadastrado = produtoNegocio.buscar(produto.getId());
        if (produtoCadastrado == null) {
            produtoNegocio.cadastrar(produto);
            Toast.makeText(this, "Produtos cadastrados com sucesso.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Produtos já exitentes!", Toast.LENGTH_SHORT).show();
        }

    }

//    private void chamarCadastroSupermercado(){
//        Intent intent  = new Intent(this, TelaMenuActivity.class);
//        CadastroProdutos.this.startActivity(intent);
//        finish();
//    }

    private boolean validarPrecoDouble(){
        boolean result;
        if (setpreco.getText().toString().equals("")) {
            setpreco.requestFocus();
            setpreco.setError(getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        }else{
            result = true;
        }
        return  result;
    }

    private Produto CriarProduto(String nome, String descricao, double preco, Supermercado nomesupermercado, String idDepartamento) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setIdDepartamento(idDepartamento);
        produto.setSupermercado(nomesupermercado);
        return produto;
    }

    public void changeTelaCadatroProdutosToTelaAdministrador(View view) {
        Intent voltarMenu = new Intent(CadastroProdutos.this, ListaProdutos.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroProdutos.this, ListaProdutos.class);
        startActivity(voltarMenu);
        finish();
    }

    private boolean validarCampos(){
        String nome = setnome.getText().toString().trim();
        String descricao = setdescricao.getText().toString().trim();
        String supermercado = setSupermrecado.getText().toString().trim();
        return verificaVazios(nome, descricao,supermercado);
    }

    private boolean verificaVazios(String nome, String descricao, String supermercado) {
        boolean result;
        if (TextUtils.isEmpty(nome)) {
            setnome.requestFocus();
            setnome.setError(getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        } else if (TextUtils.isEmpty(descricao)) {
            setdescricao.requestFocus();
            setdescricao.setError(getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        } else if (TextUtils.isEmpty(supermercado)) {
            setSupermrecado.requestFocus();
            setSupermrecado.setError(getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        }else {
            result = true;
        }
        return result;
    }

    private void limparCampos(){
        setnome.setText(stringVazia);
        setdescricao.setText(stringVazia);
        setpreco.setText(stringVazia);
        setnome.requestFocus();
    }

}
