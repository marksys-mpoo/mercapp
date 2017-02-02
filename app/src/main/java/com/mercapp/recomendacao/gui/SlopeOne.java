package com.mercapp.recomendacao.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.recomendacao.dominio.RecomendacaoProduto;
import com.mercapp.recomendacao.negocio.RecomendacaoProdutoNegocio;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.gui.DescricaoProduto;
import com.mercapp.supermercado.gui.ListaProdutos;
import com.mercapp.supermercado.gui.ListaProdutosDoSupermercado;
import com.mercapp.supermercado.gui.Ofertas;
import com.mercapp.supermercado.gui.ProdutoListAdapter;
import com.mercapp.supermercado.gui.TelaSupermercado;
import com.mercapp.supermercado.negocio.ProdutoNegocio;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SlopeOne extends AppCompatActivity {

    private ListView lista;
    private Context context = SlopeOne.this;
    private Session session = Session.getInstanciaSessao();
    private ProdutoNegocio buscaProdutos = new ProdutoNegocio(context);
    private UsuarioNegocio buscaUsuarios = new UsuarioNegocio(context);
    private RecomendacaoProdutoNegocio buscaProdutosClassificados = new RecomendacaoProdutoNegocio(context);

    private Map<Integer,Map<Integer,Double>> matrizDiferenca;
    private Map<Integer,Map<Integer,Integer>> matrizFrequencia;

    private static List<Produto> todosItens = new ArrayList<>();
    private static List<Usuario> todosUsuarios = new ArrayList<>();
    private static List<RecomendacaoProduto> produtosClassificados = new ArrayList<>();
    private static List<Produto> produtosRecomendadosOrdenados; //= new ArrayList<>();

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slope_one);

        /** Início - Simulando a leitura dos dados do sistema para o cálculo das recomendações */

        //Leitura dos Produtos do Supermercado do BD
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        //Integer idUsuario = session.getUsuarioLogado().getId();
        todosItens = buscaProdutos.listaProdutosDoSupermercado(""+idSupermercado);
        //Leitura de todos os usuários do BD
        todosUsuarios = buscaUsuarios.listarTodosUsuarios();

        //Criação da lista de notas dadas pelos usuários aos produtos
        Map<Integer,Map<Integer,Double>> data = new HashMap<Integer,Map<Integer,Double>>();

        for (Usuario usuario : todosUsuarios) {

            HashMap<Integer, Double> notasUsuario = new HashMap<Integer, Double>();
            //Lista de Objetos do Domínio RecomendacaoProduto
            produtosClassificados = buscaProdutosClassificados.listaProdutosClassificados(usuario.getId(), idSupermercado);
            for (RecomendacaoProduto recomendacaoProduto : produtosClassificados) {
                notasUsuario.put(recomendacaoProduto.getIdProduto(), recomendacaoProduto.getNota());
            }
            data.put(usuario.getId(), notasUsuario);
        }

        /** Fim - Simulando a leitura dos dados do sistema para o cálculo das recomendações */

        calculaRecomendacoes(data, session.getUsuarioLogado()); // Supondo que jose é o Usuário Logado (neste caso)

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Produto produto = (Produto) listView.getItemAtPosition(position);
                session.setProdutoSelecionado(produto);
                Intent editarProdudo = new Intent(SlopeOne.this, DescricaoProduto.class);
                startActivity(editarProdudo);
                finish();
            }
        });
    }

    private List<Produto> calculaRecomendacoes(Map<Integer,Map<Integer,Double>> data, Usuario usuarioLogado) {
        criarMatrizDiferenca(data);
        System.out.println(" ");
        System.out.println(" --------------------  INÍCIO - EXECUÇÃO DO PROTÓTIPO --------------------");
        printData(data);
        System.out.println(" ");

        System.out.println("Lendo... " + usuarioLogado);
        print(data.get(usuarioLogado.getId()));
        System.out.println(" ");

        System.out.println("Calculando PREDICT... " + usuarioLogado);
        Set<Integer> listIdOrdenados = printRecomendacao(predict(data.get(usuarioLogado.getId())), usuarioLogado);

        System.out.println("ListIdOrdenados... " + listIdOrdenados);
        List<Produto> produtosRecomendadosOrdenados = new ArrayList<>();
        for (Integer i : listIdOrdenados ) {
            Produto produtoClassificado = buscaProdutos.buscar(i);
            produtosRecomendadosOrdenados.add(produtoClassificado);
        }
        System.out.println("ListProdutosOrdenados: " + produtosRecomendadosOrdenados);
        ProdutoListAdapter adaptador = new ProdutoListAdapter(this, produtosRecomendadosOrdenados);
        lista = (ListView)findViewById(R.id.lista_produtos_recomendados);
        lista.setAdapter(adaptador);

        System.out.println(" --------------------  FIM - EXECUÇÃO DO PROTÓTIPO --------------------");
        System.out.println(" ");
//        RecomendacaoProduto recomendacaoProdutos =  new RecomendacaoProduto();
//        recomendacaoProdutos.setProdutos(produtosRecomendadosOrdenados);
        return produtosRecomendadosOrdenados;
    }

    public Map<Integer,Double> predict(Map<Integer,Double> notasUsuario) {
        HashMap<Integer,Double> predictions = new HashMap<Integer, Double>();
        HashMap<Integer,Integer> frequencies = new HashMap<Integer, Integer>();
        for (Integer j : matrizDiferenca.keySet()) {
            frequencies.put(j,0);
            predictions.put(j,0.0);
        }
        for (Integer j : notasUsuario.keySet()) {
            for (Integer k : matrizDiferenca.keySet()) {
                try {
                    Double novoValor = ( matrizDiferenca.get(k).get(j).doubleValue() + notasUsuario.get(j).doubleValue() ) * matrizFrequencia.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k)+novoValor);
                    frequencies.put(k, frequencies.get(k)+ matrizFrequencia.get(k).get(j).intValue());
                } catch(NullPointerException e) {}
            }
        }
        HashMap<Integer,Double> cleanpredictions = new HashMap<Integer, Double>();
        for (Integer j : predictions.keySet()) {
            if (frequencies.get(j)>0) {
                cleanpredictions.put(j, predictions.get(j).doubleValue()/frequencies.get(j).intValue());
            }
        }
        for (Integer j : notasUsuario.keySet()) {
            cleanpredictions.put(j,notasUsuario.get(j));
        }
        return cleanpredictions;
    }

    public void printData(Map<Integer,Map<Integer,Double>> data) {
        System.out.println(" ");
        System.out.println("************ Notas Dadas pelos Usuários *********");
        for(Integer idUsuario : data.keySet()) {
            System.out.println(idUsuario);
            print(data.get(idUsuario));
        }
        System.out.println(" ");
        System.out.println("************ Matriz Diferença [Itens x (Média das diferenças das notas e Frequências dos itens juntos) ] ************");
        System.out.print("             |");
        for (int g = 0; g< todosItens.size(); g++) {
            System.out.format("%21s", todosItens.get(g) + "       |");
        }
        System.out.println(" ");
        System.out.print("             |");
        for (int h = 0; h< todosItens.size(); h++) {
            System.out.print("  Notas    Frequenc |");
        }
        System.out.println(" ");
        for (int i = 0; i< todosItens.size(); i++) {
            System.out.print("\n" + todosItens.get(i) + ":|");
            printMatrizes(matrizDiferenca.get(todosItens.get(i).getId()), matrizFrequencia.get(todosItens.get(i).getId()));
        }
    }

    private void printMatrizes(Map<Integer,Double> notas, Map<Integer,Integer> frequencies) {
        for (int j = 0; j< todosItens.size(); j++) {
            System.out.format("%10.3f", notas.get(todosItens.get(j).getId()) );
            System.out.print(" ");
            System.out.format("%10s", frequencies.get(todosItens.get(j).getId()) + " |");
        }
        System.out.println();
    }

    public static void print(Map<Integer,Double> notasUsuario) {
        for (Integer j : notasUsuario.keySet()) {
            System.out.println(" "+ j+ " --> "+notasUsuario.get(j));
        }
    }

    public static Set<Integer> printRecomendacao(Map<Integer,Double> notasUsuario, Usuario usuario) {
        return ordenarCompare(notasUsuario, usuario);
    }

    public static Set<Integer> ordenarCompare(Map<Integer, Double> map, Usuario usuario) {
        System.out.println(" ");
        System.out.println("************ ORDENA PELO COMPARADOR ( " + usuario.toString() + " ) *********");

        Comparador comparador = new Comparador(map);
        Map<Integer, Double> sorted_map = new TreeMap<Integer, Double>(comparador);

        System.out.println("unsorted map: " + map);
        sorted_map.putAll(map);
        System.out.println("results: " + sorted_map);
        System.out.println("Ids dos Produtos Recomendados ordenados: " + sorted_map.keySet());
        System.out.println(" ");
        return sorted_map.keySet();
    }

    public void criarMatrizDiferenca(Map<Integer,Map<Integer,Double>> data) {
        matrizDiferenca = new HashMap<Integer, Map<Integer, Double>>();
        matrizFrequencia = new HashMap<Integer, Map<Integer, Integer>>();
        // first iterate through users
        for (Map<Integer, Double> user : data.values()) {
            // then iterate through user data
            for (Map.Entry<Integer, Double> entry : user.entrySet()) {
                if (!matrizDiferenca.containsKey(entry.getKey())) {
                    matrizDiferenca.put(entry.getKey(), new HashMap<Integer, Double>());
                    matrizFrequencia.put(entry.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> entry2 : user.entrySet()) {
                    int oldcount = 0;
                    if (matrizFrequencia.get(entry.getKey()).containsKey(entry2.getKey()))
                        oldcount = matrizFrequencia.get(entry.getKey()).get(entry2.getKey()).intValue();
                    Double olddiff = 0.0;
                    if (matrizDiferenca.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = matrizDiferenca.get(entry.getKey()).get(entry2.getKey()).doubleValue();
                    Double observeddiff = entry.getValue() - entry2.getValue();
                    matrizFrequencia.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
                    matrizDiferenca.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
                }
            }
        }
        for (Integer j : matrizDiferenca.keySet()) {
            for (Integer i : matrizDiferenca.get(j).keySet()) {
                Double oldvalue = matrizDiferenca.get(j).get(i).doubleValue();
                int count = matrizFrequencia.get(j).get(i).intValue();
                matrizDiferenca.get(j).put(i, oldvalue / count);
            }
        }
    }

    public final  void departamentos(View view){
        Intent irDepartamentos = new Intent(SlopeOne.this, TelaSupermercado.class);
        startActivity(irDepartamentos);
        finish();
    }

    @Override
    public final  void onBackPressed() {
        Intent irTelaSupermercado = new Intent(SlopeOne.this, TelaSupermercado.class);
        startActivity(irTelaSupermercado);
        finish();
    }

    public final void ofertas(View view){
        Intent irOfertas = new Intent(SlopeOne.this, Ofertas.class);
        startActivity(irOfertas);
        finish();
    }

}