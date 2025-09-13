package br.com.senai.logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Anotaçoes do Lombok
@Getter
@Setter
//Anotaçoes OBRIGATÓRIAS para o JPA funcionar
@NoArgsConstructor
@AllArgsConstructor
//Anotaçoes do JPA
//Entity - informa que essa classe é uma tabela
@Entity
//Table - permite que voce configure a tabela
@Table(name = "tipo_usuario")
public class TipoUsuario {

    //Id - define que é chave primária
    @Id
    //GeneretadValue - define que a chava é criada pelo BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Column - configura a coluna
    //name - nome da coluna
    //nullable - se o campo pode ser nulo ou nao (para transformar a informaçao em campo obrigatório)
    @Column(name = "tipo_usuario_id", nullable = false)
    private Integer tipoUsuarioId;

    //columnDefinition - transforma o campo em variável TEXT
    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;
}
