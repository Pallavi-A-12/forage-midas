package com.jpmc.midascore.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;

@Component
public class TransactionListener {
	
    private RestTemplate restTemplate;
	
	private final UserRepository userRepository;
	private final TransactionRecordRepository transactionRecordRepository;
	
	private TransactionListener(
			UserRepository userRepository,
			TransactionRecordRepository transactionRecordRepository,
			RestTemplate restTemplate) {
		this.userRepository=userRepository;
		this.transactionRecordRepository=transactionRecordRepository;
		this.restTemplate = restTemplate;
	}
			
	
	@KafkaListener(
			topics ="${general.kafka-topic}",
			groupId = "midas-group"
			
	)
	public void listen(Transaction transaction) {
		
		UserRecord sender =
	            userRepository.findById(transaction.getSenderId());

	    UserRecord recipient =
	            userRepository.findById(transaction.getRecipientId());
		
		//Validate sender and recipient
		if (sender == null || recipient == null) {
			return;
		}
		
		//Validate balance
		if (sender.getBalance() < transaction.getAmount()) {
			return;
		}
		
		// Call Incentive API
				Incentive incentive = restTemplate.postForObject(
						"http://localhost:8080/incentive",
						transaction,
						Incentive.class
				);
		
		//Update balances
		sender.setBalance(sender.getBalance() - transaction.getAmount());
		
		recipient.setBalance(
				recipient.getBalance()
				+ transaction.getAmount()
				+ incentive.getAmount());
		
		userRepository.save(sender);
		userRepository.save(recipient);
		
		//save transaction record
		TransactionRecord transactionRecord = 
				new TransactionRecord(
							transaction.getAmount(),
							sender,
							recipient
						);
		transactionRecordRepository.save(transactionRecord);
		
		
		System.out.println(transaction);
	}
}
