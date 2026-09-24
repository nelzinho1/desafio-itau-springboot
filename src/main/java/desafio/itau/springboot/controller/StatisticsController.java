package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsResponse;
import desafio.itau.springboot.services.TransactionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final TransactionServices transactionServices;

    public StatisticsController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics() {

        DoubleSummaryStatistics statistics =
                transactionServices.getStatistics();

        return ResponseEntity.ok(new StatisticsResponse(statistics));
    }
}
