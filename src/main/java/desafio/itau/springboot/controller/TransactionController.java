package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.TransactionRequest;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.services.TransactionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionServices transactionServices;

    public TransactionController(TransactionServices transactionServices, JsonMapper.Builder builder) {
        this.transactionServices = transactionServices;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {

        String message = transactionServices.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        if (request.getValor() <= 0) {
            return ResponseEntity.unprocessableContent().body(message);
        }
        if (request.getDataHora().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.unprocessableContent().body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionServices.clearTransactions();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
