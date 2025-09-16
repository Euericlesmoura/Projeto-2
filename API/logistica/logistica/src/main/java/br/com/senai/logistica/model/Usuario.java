package br.com.senai.logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "nome_completo", nullable = false, columnDefinition = "TEXT")
    private String nomeCompleto;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "senha",  nullable = false)
    private String senha;

    //Mapeando relacionamento entre tabelas (uma classe dentro da outra)
    //Muitos USUARIOS para um TIPO USUARIO
    //FetchType.EAGER - carrega os dados juntos
    //optional - obrigatorio ou nao
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    //@JoinColumn - avisar para o JAVA qual coluna do TIPO USUARIO que vou relacionar
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;
}
