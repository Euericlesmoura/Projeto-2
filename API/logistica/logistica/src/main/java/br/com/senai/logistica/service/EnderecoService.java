package br.com.senai.logistica.service;

import br.com.senai.logistica.model.Endereco;
import br.com.senai.logistica.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository repo) {
        enderecoRepository = repo;
    }

    //Metodo Listar Todos

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    //Metodo Cadastrar
    public Endereco cadastrarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    //Metodo Buscar
    public Endereco busarPorId(Integer id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    //Metodo Deletar
    public Endereco deletarUsuario(Integer id) {

        Endereco endereco = busarPorId(id);

        if (endereco == null) {
            return null;
        }

        enderecoRepository.delete(endereco);
        return endereco;
    }

    //Metodo Atualizar
    public Endereco atualizarEndereco (Integer id, Endereco endNovo) {

        Endereco endAntigo = busarPorId(id);

        if (endAntigo == null) {
            return null;
        }

        endAntigo.setLogradouro(endNovo.getLogradouro());
        endAntigo.setNumero(endNovo.getNumero());
        endAntigo.setCep(endNovo.getCep());
        endAntigo.setCidade(endNovo.getCidade());
        return enderecoRepository.save(endAntigo);
    }
}
