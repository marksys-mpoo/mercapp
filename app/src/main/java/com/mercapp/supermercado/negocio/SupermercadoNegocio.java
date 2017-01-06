package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.persistencia.SupermercadoPersistencia;

import java.util.List;


public class SupermercadoNegocio {

    private Context context;
    private Session sessao = Session.getInstanciaSessao();

    public SupermercadoNegocio(Context contexto)
    {
        this.context = contexto;
    }
    
    public final  Supermercado buscaSupermercado(String nome) {
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(context);
        return supermercadoPersistencia.buscar(nome);
    }


    public  final void cadastrar(String nome, String telefone, LatLng coordenadas){
        Supermercado supermercadoCadastro = new Supermercado();
        supermercadoCadastro.setNome(nome);
        supermercadoCadastro.setTelefone(telefone);
        supermercadoCadastro.setCoordenadas(coordenadas);
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(context);
        supermercadoPersistencia.cadastrar(supermercadoCadastro);
    }


    public  final void editar(Supermercado supermercado){
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(context);
        supermercadoPersistencia.editar(supermercado);
    }

    public  final List<Supermercado> listar(){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(context);
        return consulta.listar();
    }

    public  final List<Supermercado> listar(String inputText){
        SupermercadoPersistencia listagem = new SupermercadoPersistencia(context);
        return listagem.listar(inputText);
    }

    public  final List<String> listaNomeSupermercado(){
        SupermercadoPersistencia listagem = new SupermercadoPersistencia(context);
        return listagem.listaSupermercado();
    }

    public  final void iniciarSessaoSupermercado(Supermercado supermercado){
        sessao.setSupermercadoSelecionado(supermercado);
    }

    public final  void iniciarSessaoFuncaoCrud(String funcao){
        sessao.setFuncaoCrudSupermercado(funcao);
    }

    public  final void iniciarSessaotextButaoFuncaoCrud(String text){
        sessao.setTextButaoFuncaoSupermercado(text);
    }

    public  final void iniciarSessaoProduto(String departamento){
        sessao.setDepartamentoSelecionado(departamento);
    }

    public final  void deletar(Supermercado supermercado){
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(context);
        supermercadoPersistencia.deletar(supermercado);
    }
}