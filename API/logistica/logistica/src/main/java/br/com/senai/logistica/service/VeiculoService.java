package br.com.senai.logistica.service;

import br.com.senai.logistica.model.Veiculo;
import br.com.senai.logistica.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository repo) {
        veiculoRepository = repo;
    }

    //Metodo Listar Todos
    public List<Veiculo> listarVeiculo() {
        return veiculoRepository.findAll();
    }

    //Metodo Cadastrar
    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    //Metodo Buscar
    public Veiculo buscarPorId(Integer id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    //Metodo Deletar
    public Veiculo deletarVeiculo(Integer id) {

        Veiculo veiculo = buscarPorId(id);

        if (veiculo != null) {
            return null;
        }

        veiculoRepository.delete(veiculo);
        return veiculo;
    }

    //Metodo Atualizar
    public Veiculo atualizarVeiculo (Integer id, Veiculo veiculoNovo) {

        Veiculo veiculoAntigo = buscarPorId(id);

        if (veiculoAntigo == null) {
            return null;
        }

        veiculoAntigo.setModelo(veiculoNovo.getModelo());
        veiculoAntigo.setTipo(veiculoNovo.getTipo());
        veiculoAntigo.setPlaca(veiculoNovo.getPlaca());
        return veiculoRepository.save(veiculoAntigo);
    }
}
