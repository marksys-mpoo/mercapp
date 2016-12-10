package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
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

public class CadastroProdutos extends AppCompatActivity {

    private static final String stringVazia = "";
    private static final String idDepartamento = "1";
    private Context _context = CadastroProdutos.this;
    private SupermercadoNegocio supermercadoNegocio;
    private Produto produtoCadastrado;
    private EditText setdescricao, setpreco,setnome, setSupermrecado ;
    private double preco;

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

    public void cadastroProdutosDireto(View view) {

        String nome = setnome.getText().toString().trim();
        String descricao = setdescricao.getText().toString().trim();
        String nomeSupermercado = setSupermrecado.getText().toString().trim();
        if (setpreco.getText().toString().equals("")) {
            setpreco.requestFocus();
            setpreco.setError(getString(R.string.preco_vazio_tela_cadastro_produtos));
        }
        else {
            if(validarCampos()) {
                preco = Double.parseDouble(setpreco.getText().toString().trim());
                supermercadoNegocio = new SupermercadoNegocio(_context);
                Supermercado supermercado = supermercadoNegocio.buscaSupermercado(nomeSupermercado);
                int idSupermercado = supermercado.getId();
                Produto produto = CadastrarProduto(nome, descricao, preco,idSupermercado,idDepartamento );
                efetuarCadastroProduto(produto);
            }else{
                Toast.makeText(this, "O cadastro não foi realizado.", Toast.LENGTH_SHORT).show();
            }

//        // 1 - Padaria
//        Produto produto1 = CadastrarProduto(1,"PAO FRANCES",8.99,"1", "1");
//        efetuarCadastroProduto(produto1, "nao");
//        Produto produto2 = CadastrarProduto(2,"PANETONE",12.99,"1", "1");
//        efetuarCadastroProduto(produto2, "nao");
//        Produto produto3 = CadastrarProduto(3,"PAO FRANCES",9.59,"2", "1");
//        efetuarCadastroProduto(produto3, "nao");
//        Produto produto4 = CadastrarProduto(4,"PAO FORMA",7.54,"2", "1");
//        efetuarCadastroProduto(produto4, "sim");
        /* // 2 - Frios
        Produto produto5 = CadastrarProduto(5,"PRESUNTO","7,99 KG","1","2");
        efetuarCadastroProduto(produto5, "nao");
        Produto produto6 = CadastrarProduto(6,"REQUEIJAO","5,33 CP","1","2");
        efetuarCadastroProduto(produto6, "nao");
        Produto produto7 = CadastrarProduto(7,"IOGURTE","11,59 1L","2","2");
        efetuarCadastroProduto(produto7, "nao");
        Produto produto8 = CadastrarProduto(8,"PRESUNTO","7,99 KG","2", "2");
        efetuarCadastroProduto(produto8, "nao");
        // 3 - Açougue
        Produto produto9 = CadastrarProduto(9,"MAMINHA","19,49 KG","1","3");
        efetuarCadastroProduto(produto9, "nao");
        Produto produto10 = CadastrarProduto(10,"CARNE BOVINA","20,33 KG","1","3");
        efetuarCadastroProduto(produto10, "nao");
        Produto produto11 = CadastrarProduto(11,"CARNE SUINA","22,00 KG","2","3");
        efetuarCadastroProduto(produto11, "nao");
        Produto produto12 = CadastrarProduto(12,"ALCATRA","17,00 KG","2","3");
        efetuarCadastroProduto(produto12, "nao");
        // 4 - Frutas
        Produto produto13 = CadastrarProduto(13,"MACA VERDE","7,49 KG","1","4");
        efetuarCadastroProduto(produto13, "nao");
        Produto produto14 = CadastrarProduto(14,"TOMATE","6,33 KG","1","4");
        efetuarCadastroProduto(produto14, "nao");
        Produto produto15 = CadastrarProduto(15,"LARANJA","3,00 KG","2","4");
        efetuarCadastroProduto(produto15, "nao");
        Produto produto16 = CadastrarProduto(16,"CEBOLA","4,50 KG","2","4");
        efetuarCadastroProduto(produto16, "nao");
        // 5 - Bebida
        Produto produto17 = CadastrarProduto(17,"CERVEJA","3,00 LT","1","5");
        efetuarCadastroProduto(produto17, "nao");
        Produto produto18 = CadastrarProduto(18,"SODA","6,50 2L","1","5");
        efetuarCadastroProduto(produto18, "nao");
        Produto produto19 = CadastrarProduto(19,"COCA-COLA","7,00 2L","2","5");
        efetuarCadastroProduto(produto19, "nao");
        Produto produto20 = CadastrarProduto(20,"GUARANA","6,00 2L","2","5");
        efetuarCadastroProduto(produto20, "nao");
        // 6 - Mercearia
        Produto produto21 = CadastrarProduto(21,"BISCOITO","2,49 PC","1","6");
        efetuarCadastroProduto(produto21, "nao");
        Produto produto22 = CadastrarProduto(22,"MAIONESE","3,55 PC","1","6");
        efetuarCadastroProduto(produto22, "nao");
        Produto produto23 = CadastrarProduto(23,"MOLHO TOMATE","4,00 PC","2","6");
        efetuarCadastroProduto(produto23, "nao");
        Produto produto24 = CadastrarProduto(24,"MARGARINA","3,02 PC","2","6");
        efetuarCadastroProduto(produto24, "nao");
        // 7 - Higiene
        Produto produto25 = CadastrarProduto(25,"CREME DENTAL","4,00 UN","1","7");
        efetuarCadastroProduto(produto25, "nao");
        Produto produto26 = CadastrarProduto(26,"ESCOVA DENTAL","5,33 UN","1","7");
        efetuarCadastroProduto(produto26, "nao");
        Produto produto27 = CadastrarProduto(27,"SHAMPOO","6,00 UN","2","7");
        efetuarCadastroProduto(produto27, "nao");
        Produto produto28 = CadastrarProduto(28,"SABONETE","1,50 UN","2","7");
        efetuarCadastroProduto(produto28, "nao");
        // 8 - Limpeza
        Produto produto29 = CadastrarProduto(29,"AGUA SANITARIA","2,49 1L","1","8");
        efetuarCadastroProduto(produto29, "nao");
        Produto produto30 = CadastrarProduto(30,"DESINFETANTE","2,33 KG","1","8");
        efetuarCadastroProduto(produto30, "nao");
        Produto produto31 = CadastrarProduto(31,"DETERGENTE","2,00 2L","2","8");
        efetuarCadastroProduto(produto31, "nao");
        Produto produto32 = CadastrarProduto(32,"AMACIANTE","5,90 2L","2","8");
        efetuarCadastroProduto(produto32, "nao");
        // 9 - Bazar
        Produto produto33 = CadastrarProduto(33,"TUPPERWARE","3,49 UN","1","9");
        efetuarCadastroProduto(produto33, "nao");
        Produto produto34 = CadastrarProduto(34,"CANECA","8,53 UN","1","9");
        efetuarCadastroProduto(produto34, "nao");
        Produto produto35 = CadastrarProduto(35,"PRATO PLASTICO","7,00 PC","2","9");
        efetuarCadastroProduto(produto35, "nao");
        Produto produto36 = CadastrarProduto(36,"TALHERE PLASTICO","5,00 PC","2","9");
        efetuarCadastroProduto(produto36, "sim"); */
        }
    }

    private void efetuarCadastroProduto(Produto produto) {
        Integer id = produto.getId();
        String descricao = produto.getDescricao();
        Double preco = produto.getPreco();
        String nome =  produto.getNome();
        int idSupermercado = produto.getIdSupermercado();
        String idDepartamento = produto.getIdDepartamento();
        ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
        produtoCadastrado = produtoNegocio.buscar(id);
        if (produtoCadastrado == null) {
            produtoNegocio.cadastrar(nome, descricao, preco, idSupermercado,idDepartamento);
            Toast.makeText(this, "Produtos cadastrados com sucesso.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Produtos já exitentes!", Toast.LENGTH_SHORT).show();
        }

    }

    private Produto CadastrarProduto(  String nome, String descricao, double preco, int nomesupermercado, String idDepartamento) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setIdDepartamento(idDepartamento);
        produto.setIdSupermercado(nomesupermercado);
        return produto;
    }

//    private Produto CadastrarProduto(Integer id, String descricao, Double preco, String idSupermercado, String idDepartamento) {
//
//        Produto produto = new Produto();
//        produto.setId(id);
//        produto.setDescricao(descricao);
//        produto.setPreco(preco);
//        produto.setIdSupermercado(idSupermercado);
//        produto.setIdDepartamento(idDepartamento);
//        return produto;
//    }

//    public void efetuarCadastroProduto(Produto produto, String isUltimoProduto) {
//        Integer id = produto.getId();
//        String descricao = produto.getDescricao();
//        Double preco = produto.getPreco();
//        String idSupermercado = produto.getIdSupermercado();
//        String idDepartamento = produto.getIdDepartamento();
//        ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
//        produtoCadastrado = produtoNegocio.buscar(id);
//        if (produtoCadastrado == null) {
//            produtoNegocio.cadastrar(descricao, preco, idSupermercado, idDepartamento);
//            if (isUltimoProduto == "sim"){
//                Toast.makeText(this, "Produtos cadastrados com sucesso.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            if (isUltimoProduto == "sim") {
//                Toast.makeText(this, "Produtos já exitentes!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

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
        String nome = setnome.getText().toString();
        String descricao = setdescricao.getText().toString();
        return verificaVazios(nome, descricao);
    }

    private boolean verificaVazios(String nome, String descricao) {
        boolean result;
        if (TextUtils.isEmpty(nome)) {
            setnome.requestFocus();
            setnome.setError(getString(R.string.nome_vazio_tela_cadastro_produtos));
            result = false;
        } else if (TextUtils.isEmpty(descricao)) {
            setdescricao.requestFocus();
            setdescricao.setError(getString(R.string.descricao_vazio_tela_cadastro_produtos));
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
