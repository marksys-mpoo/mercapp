package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.ProdutoNegocio;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.negocio.Validacao;

import java.util.List;

public class CadastroProdutos extends AppCompatActivity {

    private static final String stringVazia = "";
    private String nomeSpinnerSupermercado, nomeSpinnerImagem, nomeSpinnerDepartamento;
    private Context _context = CadastroProdutos.this;
    private Spinner spinnerSupermercado, spinnerImagens, spinnerDepartamento;
    private SupermercadoNegocio supermercadoNegocio;
    private Produto produtoCadastrado;
    private EditText setdescricao, setpreco,setnome, setimagem;
    private String imagemStr;
    private int imagem, posicaoSpinnerSupermercado, posicaoSpinnerImagem, posicaoSpinnerDepartamento;
    private double preco;
    private AlertDialog alerta;
    private Session session = Session.getInstanciaSessao();
    private ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
    private String[] strProdutosDrawable = {
            "img_arroz",
            "img_creme_leite",
            "img_leite",
            "img_maca",
            "img_pao",
            "img_pizza",
            "img_refrigerante",
            "img_sabonete",
            "img_shampoo"
    };
    private String[] departamentos = {
            "Padaria",
            "Frios",
            "Açougue",
            "Frutas",
            "Bebidas",
            "Mercearia",
            "Higiene",
            "Limpeza",
            "Bazar"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
        setnome = (EditText) findViewById(R.id.edtNomeProduto);
        setdescricao = (EditText) findViewById(R.id.edtDescricaoProduto);
        setpreco = (EditText) findViewById(R.id.edtPrecoProduto);
        spinnerSupermercado = (Spinner) findViewById(R.id.spinner);
        spinnerImagens = (Spinner) findViewById(R.id.spinnerImagens);
        spinnerDepartamento = (Spinner) findViewById(R.id.spinnerDepartamento);
        setnome.requestFocus();

        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
        List<String> supermercados = supermercadoNegocio.listaNomeSupermercado();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, supermercados);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSupermercado.setAdapter(spinnerArrayAdapter);

        spinnerSupermercado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nomeSpinnerSupermercado = parent.getItemAtPosition(position).toString();
                posicaoSpinnerSupermercado = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner das Imagens
        ArrayAdapter<String> arrayAdapterImagem = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, strProdutosDrawable);
        ArrayAdapter<String> spinnerArrayAdapterImagem = arrayAdapterImagem;
        spinnerArrayAdapterImagem.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerImagens.setAdapter(spinnerArrayAdapterImagem);

        spinnerImagens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nomeSpinnerImagem = parent.getItemAtPosition(position).toString();
                posicaoSpinnerImagem = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (session.getProdutoSelecionado() != null) {
            carregaDados();
        }

        // Spinner dos Departamentos
        ArrayAdapter<String> arrayAdapterDepartamento = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, departamentos);
        ArrayAdapter<String> spinnerArrayAdapterDepartamento = arrayAdapterDepartamento;
        spinnerArrayAdapterDepartamento.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDepartamento.setAdapter(spinnerArrayAdapterDepartamento);

        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nomeSpinnerDepartamento = parent.getItemAtPosition(position).toString();
                posicaoSpinnerDepartamento = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (session.getProdutoSelecionado() != null) {
            carregaDados();
        }
    }

    public void pegarConteudoProdutos(View view) {

        if (validarCampos() && validarPrecoDouble()){
            String nome = setnome.getText().toString().trim();
            String descricao = setdescricao.getText().toString().trim();
            String nomeSupermercado = nomeSpinnerSupermercado;
            preco = Double.parseDouble(setpreco.getText().toString().trim());
            imagem = getResources().getIdentifier(nomeSpinnerImagem , "drawable", getPackageName());

            supermercadoNegocio = new SupermercadoNegocio(_context);
            Supermercado supermercado = supermercadoNegocio.buscaSupermercado(nomeSupermercado);

            if (supermercado != null){
                if (session.getProdutoSelecionado() != null) {
                    session.getProdutoSelecionado().setNome(nome);
                    session.getProdutoSelecionado().setDescricao(descricao);
                    session.getProdutoSelecionado().setSupermercado(supermercado);
                    session.getProdutoSelecionado().setNumeroDepartamento(posicaoSpinnerDepartamento);
                    session.getProdutoSelecionado().setPosicaoSpinnerSupermercado(posicaoSpinnerSupermercado);
                    session.getProdutoSelecionado().setPosicaoSpinnerImagem(posicaoSpinnerImagem);
                    produtoNegocio.editar(session.getProdutoSelecionado());
                } else {
                    produtoNegocio.cadastrar(nome, imagem, descricao, preco, supermercado, posicaoSpinnerDepartamento, posicaoSpinnerSupermercado, posicaoSpinnerImagem);
                }
                Intent changeToListaProdutos = new Intent(CadastroProdutos.this, ListaProdutos.class);
                session.setProdutoSelecionado(null);
                CadastroProdutos.this.startActivity(changeToListaProdutos);
                finish();
            }else {
                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //define a mensagem
                builder.setMessage("Este supermercado não está Registrado, deseja cadastrar?");
                //define um botão como positivo
                builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                      chamarCadastroSupermercado();
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

    private void carregaDados() {
        Double preco = session.getProdutoSelecionado().getPreco();
        String precoEditavel = preco.toString();
        setnome.setText(session.getProdutoSelecionado().getNome());
        setdescricao.setText(session.getProdutoSelecionado().getDescricao());
        setpreco.setText(precoEditavel);
        spinnerSupermercado.setSelection(session.getProdutoSelecionado().getPosicaoSpinnerSupermercado());
        spinnerImagens.setSelection(session.getProdutoSelecionado().getPosicaoSpinnerImagem());
        spinnerDepartamento.setSelection(session.getProdutoSelecionado().getNumeroDepartamento());
    }

    private void chamarCadastroSupermercado(){
        Intent intent  = new Intent(this, ListaSupermercados.class);
        CadastroProdutos.this.startActivity(intent);
        finish();
    }

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

    @Override
    public void onBackPressed() {
        Intent voltarLista = new Intent(CadastroProdutos.this, ListaProdutos.class);
        startActivity(voltarLista);
        finish();
    }

    private boolean validarCampos(){
        String nome = setnome.getText().toString().trim();
        String descricao = setdescricao.getText().toString().trim();
        return Validacao.verificaVaziosProduto(nome, descricao,this,setnome,setdescricao);
    }

    public void changeToImagens(View view) {
        Intent voltarMenu = new Intent(CadastroProdutos.this, ListaImagens.class);
        startActivity(voltarMenu);
        finish();
    }

    private void limparCampos(){
        setnome.setText(stringVazia);
        setdescricao.setText(stringVazia);
        setpreco.setText(stringVazia);
        setnome.requestFocus();
    }

}
