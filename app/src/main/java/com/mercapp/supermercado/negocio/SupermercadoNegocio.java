package com.mercapp.supermercado.negocio;

import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.persistencia.SupermercadoPersistencia;

import java.util.List;


public class SupermercadoNegocio {

    private Context _context;
    private Session sessao = Session.getInstanciaSessao();

    public Supermercado buscaSupermercado(String nome) {
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        Supermercado supermercadoSelecionado = supermercadoPersistencia.buscarSupermercado(nome);
        return supermercadoSelecionado;
    }

    public SupermercadoNegocio(Context context)
    {
        _context = context;
    }

    public void cadastrar(String nome, String telefone, LatLng coordenadas){
        Supermercado supermercadoCadastro = new Supermercado();
        supermercadoCadastro.setNome(nome);
        supermercadoCadastro.setTelefone(telefone);
        supermercadoCadastro.setCoordenadas(coordenadas);
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.cadastrar(supermercadoCadastro);
    }


    public void editar(Supermercado supermercado){
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.editar(supermercado);
    }

    public List<Supermercado> listaSupermercados(){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        List<Supermercado> supermercados = consulta.listaDados();
        return supermercados;
    }

    public List<Supermercado> listarSupermercadosPorParteDoNome(String inputText){
        SupermercadoPersistencia listagem = new SupermercadoPersistencia(_context);
        List<Supermercado> supermercados = listagem.listarSupermercadosPorParteDoNome(inputText);
        return supermercados;
    }

    public List<String> listaNomeSupermercado(){
        SupermercadoPersistencia listagem = new SupermercadoPersistencia(_context);
        List<String> supermercados = listagem.listaSupermercado();
        return supermercados;
    }

    public Cursor listaProdutosDoSupermercado(String idSuper){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaDadosProdutosDoSupermercado(idSuper);
        return cursor;
    }

    public Cursor listaProdutosPorDepartamentoNegocio(String idSupermercado, int numDepartamento){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaProdutosDoSupermercadoPorDepartamentoPersistencia(idSupermercado, numDepartamento);
        return cursor;
    }

    public void iniciarSessaoSupermercado(Supermercado supermercado){
        sessao.setSupermercadoSelecionado(supermercado);
    }

    public void iniciarSessaoFuncaoCrud(String funcao){
        sessao.setFuncaoCrudSupermercado(funcao);
    }

    public void iniciarSessaotextButaoFuncaoCrud(String text){
        sessao.setTextButaoFuncaoSupermercado(text);
    }

    public void iniciarSessaoProduto(String departamento){
        sessao.setDepartamentoSelecionado(departamento);
    }

    public void deletar(Supermercado supermercado){
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.deletar(supermercado);
    }
}