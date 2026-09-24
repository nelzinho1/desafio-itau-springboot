package desafio.itau.springboot.services;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionServices {

    //biblioteca para criar fila das transacoes na memoria ConcurrentLinkedQueue
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    // metodo para add transacao // aqui passamos o transaction por parametro (Transaction transaction)
    public String addTransaction(Transaction transaction) {
        if (transaction.getValor() <= 0) {
            return "Transação Negativa!";
        }
        transactions.add(transaction);

        return "Transação criada com sucesso!";
    }

    //metodo pra limpar as transacoes
    public void clearTransactions() {
        transactions.clear();
    }

    // metodo pra retornar as estatisticas
    public DoubleSummaryStatistics getStatistics() {
        //usar o offsetdatetime pra pegar data atual, nao precisa instanciar ela dar o new pois ela nao e classe é um metodo statico
        OffsetDateTime now = OffsetDateTime.now();
      //  System.out.println("AGORA: " + now);

        //o retorno das statistcs tem que fazer um lambida com stream nas transactions
        return transactions.stream()
                .filter(t -> {
                    System.out.println("TRANSAÇÃO: " + t.getDataHora());
                    return t.getDataHora().isAfter(now.minusSeconds(60));
                })
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

    }

}
