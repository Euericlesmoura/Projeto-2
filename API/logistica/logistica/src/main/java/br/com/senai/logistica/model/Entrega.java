package br.com.senai.logistica.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrega_id", nullable = false)
    private Integer entregaId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(name = "descricao_produto", nullable = false, columnDefinition = "TEXT")
    private String descricaoProduto;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "data_pedido", nullable = false)
    private OffsetDateTime dataPedido;
}
