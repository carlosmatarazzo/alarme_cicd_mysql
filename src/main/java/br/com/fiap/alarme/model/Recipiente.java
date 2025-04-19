package br.com.fiap.alarme.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_al_recipientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECIPIENTE_ID")
    private Long recipienteId;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private RecipienteTipo tipo;

    @Column(name = "CAPACIDADE_TOTAL")
    private Integer capacidadeTotal;

    @Column(name = "CAPACIDADE_ATUAL")
    private Integer capacidadeAtual = 0;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

}