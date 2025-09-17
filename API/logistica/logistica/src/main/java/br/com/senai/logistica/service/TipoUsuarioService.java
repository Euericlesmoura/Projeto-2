package br.com.senai.logistica.service;

import br.com.senai.logistica.model.TipoUsuario;
import br.com.senai.logistica.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    //Injeção de Dependência - Falar que Service depende de alguém
    //final - significa que é constante, nao muda o que esta dentro
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioService(TipoUsuarioRepository repo) {
        tipoUsuarioRepository = repo;
    }

    //Metodo Listar Todos
    public List<TipoUsuario> listarTodos() {
        return tipoUsuarioRepository.findAll();
    }

    //Metodo Cadastrar (INSERT INTO..  no SQL)
    public TipoUsuario cadastrarTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    //Metodo Buscar
    public TipoUsuario buscarPorId(Integer id) {
        return tipoUsuarioRepository.findById(id).orElse(null);
    }

    //Metodo Deletar
    public TipoUsuario deletarTipoUsuario(Integer id) {

        //1. Verificar se o cliente existe
        TipoUsuario tipoUsuario = buscarPorId(id);

        //2. Se não existir, retornar nulo
        if (tipoUsuario == null) {
            return null;
        }

        //3. Se existir, excluo
        tipoUsuarioRepository.delete(tipoUsuario);
        return tipoUsuario;
    }

    //Metodo Atualizar
    public TipoUsuario atualizarTipoUsuario(Integer id, TipoUsuario novoTipo) {

        //1. Procurar quem eu quero atualizar
        TipoUsuario tipoAntigo = buscarPorId(id);

        //2. Se eu não encontrar, retorno nulo
        if (tipoAntigo == null) {
            return null;
        }

        //3. Se eu achar, eu atualizo
        tipoAntigo.setDescricao(novoTipo.getDescricao());
        return tipoUsuarioRepository.save(tipoAntigo);
    }
}
