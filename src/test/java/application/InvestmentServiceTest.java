package application;
import com.greenwealth.transaction_service.application.InvestmentService;
import com.greenwealth.transaction_service.domain.model.Investment;
import com.greenwealth.transaction_service.domain.model.Transaction;
import com.greenwealth.transaction_service.domain.repository.InvestmentRepository;
import com.greenwealth.transaction_service.infrastructure.messaging.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import  static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class InvestmentServiceTest {
    @Mock // clonne DB
    private InvestmentRepository repository;
    @Mock // clonn Kafka
    private KafkaProducer kafkaProducer;
    @InjectMocks // the service (put clonnes inside the service)
    private InvestmentService investmentService;
    @Test
    void should_CreateInvestment_When_AmountNeedsRouding(){
        // preparation
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("12.50")); //  trying test an amount for get 0.50 investisment

        // action
        investmentService.processInvestment(transaction);
        // verfy save method one time
        verify(repository, times(1)).save(any(Investment.class));
        // verify send a message to kafka one time
        verify(kafkaProducer, times(1)).sendInvestmentEvent(anyString());

        System.out.println("Seccessful Test : calculat the investment and save it ");
    }


}
