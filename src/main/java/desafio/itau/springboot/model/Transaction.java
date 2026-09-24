package desafio.itau.springboot.model;

import java.time.OffsetDateTime;

public class Transaction {
    // atributos
    private double valor;
    private OffsetDateTime dataHora;

    //construtores
    //se eu criei um construtor passando os atributos significa que nao precisa criar os metodos sets dos atributos
    public Transaction(final double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    //gets
    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
