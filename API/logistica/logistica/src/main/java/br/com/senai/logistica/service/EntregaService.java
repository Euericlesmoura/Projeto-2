package br.com.senai.logistica.service;

import br.com.senai.logistica.model.Entrega;
import br.com.senai.logistica.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository repo) {

        entregaRepository = repo;
    }

    //Metodo Listar Todos
    public List<Entrega> listarEntrega() {
        return entregaRepository.findAll();
    }

    //Metodo Cadastrar
    public Entrega cadastrarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    //Metodo Buscar
    public Entrega buscarPorId(Integer id) {
        return entregaRepository.findById(id).orElse(null);
    }

    //Metodo Deletar
    public Entrega deletarEntrega(Integer id) {

        Entrega entrega = buscarPorId(id);

        if (entrega == null) {
            return null;
        }

        entregaRepository.delete(entrega);
        return entrega;
    }

    //Metodo Atualizar
    public Entrega atualizarEntrega (Integer id, Entrega entregaNovo) {

        Entrega entregaAntigo = buscarPorId(id);

        if (entregaAntigo == null) {
            return null;
        }

        entregaAntigo.setStatus(entregaNovo.getStatus());
        entregaAntigo.setDataPedido(entregaNovo.getDataPedido());
        entregaAntigo.setDescricaoProduto(entregaNovo.getDescricaoProduto());
        return entregaRepository.save(entregaAntigo);
    }
}
