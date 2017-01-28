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

    private String nomeSpinnerSupermercado, nomeSpinnerImagem;
    private Context context = CadastroProdutos.this;
    private Spinner spinnerSupermercado, spinnerImagens, spinnerDepartamento;
    private SupermercadoNegocio supermercadoNegocio;
    private EditText setdescricao, setpreco,setnome;
    private int imagem, posicaoSpinnerSupermercado, posicaoSpinnerImagem, posicaoSpinnerDepartamento;
    private double preco;
    private Session session = Session.getInstanciaSessao();
    private ProdutoNegocio produtoNegocio = new ProdutoNegocio(context);
    private String[] strProdutosDrawable = {
            "img_arroz",
            "img_creme_pele",
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
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
        setnome = (EditText) findViewById(R.id.edtNomeProduto);
        setdescricao = (EditText) findViewById(R.id.edtDescricaoProduto);
        setpreco = (EditText) findViewById(R.id.edtPrecoProduto);
        spinnerSupermercado = (Spinner) findViewById(R.id.spinner);
        spinnerImagens = (Spinner) findViewById(R.id.spinnerImagens);
        spinnerDepartamento = (Spinner) findViewById(R.id.spinnerDepartamento);
        setnome.requestFocus();
        spinnerSupermercados();
        spinnerImagens();
        spinnerDepartamentos();
        if (session.getProdutoSelecionado() != null) {
            carregaDados();
        }
        System.out.println( "" );
        System.out.println( " ========================== Códigos Imagens =========================== " );
        for (String item : strProdutosDrawable) {
            int codigoImagem = getResources().getIdentifier(item , "drawable", getPackageName());
            System.out.println( item + " = " + codigoImagem );
        }
    }

    private void spinnerSupermercados() {
        supermercadoNegocio = new SupermercadoNegocio(context);
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
    }

    private void spinnerDepartamentos() {
        ArrayAdapter<String> arrayAdapterDepartamento = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, departamentos);
        ArrayAdapter<String> spinnerArrayAdapterDepartamento = arrayAdapterDepartamento;
        spinnerArrayAdapterDepartamento.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDepartamento.setAdapter(spinnerArrayAdapterDepartamento);

        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicaoSpinnerDepartamento = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerImagens() {
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
    }

    public final void pegarConteudoProdutos(View view) {

        if (validarCampos() && validarPrecoDouble()){
            String nome = setnome.getText().toString().trim();
            String descricao = setdescricao.getText().toString().trim();
            String nomeSupermercado = nomeSpinnerSupermercado;
            preco = Double.parseDouble(setpreco.getText().toString().trim());
            imagem = getResources().getIdentifier(nomeSpinnerImagem , "drawable", getPackageName());

            supermercadoNegocio = new SupermercadoNegocio(context);
            Supermercado supermercado = supermercadoNegocio.buscaSupermercado(nomeSupermercado);

            if (supermercado != null){
                if (session.getProdutoSelecionado() != null) {
                    produtoSelecionado(nome, descricao, supermercado);
                    produtoNegocio.editar(session.getProdutoSelecionado());
                } else {
                    Produto produto = criarProduto(nome, imagem, descricao, preco, supermercado, posicaoSpinnerDepartamento, posicaoSpinnerSupermercado, posicaoSpinnerImagem);
                    produtoNegocio.cadastrar(produto);
                }
                Intent changeToListaProdutos = new Intent(CadastroProdutos.this, ListaProdutos.class);
                session.setProdutoSelecionado(null);
                CadastroProdutos.this.startActivity(changeToListaProdutos);
                finish();
            }else {
                criarAlertDialog();
            }
        } else {
            Toast.makeText(this, "O cadastro não foi realizado.", Toast.LENGTH_SHORT).show();
        }
    }

    private Produto criarProduto(String nome, int recebeImagem, String descricao, double recebePreco, Supermercado nomesupermercado, int numeroDepartamento,
                    int posicaodoSpinnerSupermercado, int posicaoSpinnerImagemProduto){
        Produto produtoCadastro = new Produto();
        produtoCadastro.setDescricao(descricao);
        produtoCadastro.setPreco(recebePreco);
        produtoCadastro.setNome(nome);
        produtoCadastro.setImageProduto(recebeImagem);
        produtoCadastro.setSupermercado(nomesupermercado);
        produtoCadastro.setNumeroDepartamento(numeroDepartamento);
        produtoCadastro.setPosicaoSpinnerSupermercado(posicaodoSpinnerSupermercado);
        produtoCadastro.setPosicaoSpinnerImagem(posicaoSpinnerImagemProduto);

        return produtoCadastro;
    }

    private void criarAlertDialog() {
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
        final AlertDialog messagemTela = builder.create();
        //Exibe
        messagemTela.show();
    }

    private void produtoSelecionado(String nome, String descricao, Supermercado supermercado) {
        session.getProdutoSelecionado().setNome(nome);
        session.getProdutoSelecionado().setImageProduto(imagem);
        session.getProdutoSelecionado().setDescricao(descricao);
        session.getProdutoSelecionado().setPreco(preco);
        session.getProdutoSelecionado().setSupermercado(supermercado);
        session.getProdutoSelecionado().setNumeroDepartamento(posicaoSpinnerDepartamento);
        session.getProdutoSelecionado().setPosicaoSpinnerSupermercado(posicaoSpinnerSupermercado);
        session.getProdutoSelecionado().setPosicaoSpinnerImagem(posicaoSpinnerImagem);
    }

    private void carregaDados() {
        Double carregarPreco = session.getProdutoSelecionado().getPreco();
        String precoEditavel = carregarPreco.toString();
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
        boolean result = true;
        if (setpreco.getText().toString().equals("")) {
            setpreco.requestFocus();
            setpreco.setError(getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        }
        return  result;
    }

    @Override
    public final void onBackPressed() {
        Intent voltarLista = new Intent(CadastroProdutos.this, ListaProdutos.class);
        startActivity(voltarLista);
        finish();
    }

    private boolean validarCampos(){
        String nome = setnome.getText().toString().trim();
        String descricao = setdescricao.getText().toString().trim();
        return Validacao.verificaVaziosProduto(nome, descricao,this,setnome,setdescricao);
    }

    public final void mudaListaImagens(View view) {
        Intent voltarMenu = new Intent(CadastroProdutos.this, ListaImagens.class);
        startActivity(voltarMenu);
        finish();
    }

}
