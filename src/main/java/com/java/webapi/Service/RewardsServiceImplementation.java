package com.java.webapi.Service;

import com.java.webapi.Dao.Rewards;
import com.java.webapi.Models.Customer;
import com.java.webapi.Models.Transaction;
import com.java.webapi.Repository.CustomerRepository;
import com.java.webapi.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImplementation implements RewardsService {
    public static final int daysInMonths = 30;
    public static final int firstRewardLimit = 50;
    public static final int secondRewardLimit = 100;

    @Autowired
    TransactionRepository transactionRepository;

    public RewardsServiceImplementation() {
    }

    public RewardsServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    CustomerRepository customerRepository;
    public Rewards getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*daysInMonths);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewardPoints(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private  Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction)).mapToLong(r -> r.longValue()).sum();
    }

    private  Integer calculateRewards(Transaction t) {
        if (t.getAmount() >firstRewardLimit && t.getAmount() <= secondRewardLimit) {
            return Math.round(t.getAmount() -firstRewardLimit);
        } else if (t.getAmount() > secondRewardLimit) {
            return Math.round(t.getAmount() - secondRewardLimit) * 2
                    + firstRewardLimit;
        } else
            return 0;

    }

    public  Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
    public List<Rewards> getAllRewards(){
          List<Customer> allCustomers= (List<Customer>) customerRepository.findAll();
          return allCustomers.stream().map(x->new RewardsServiceImplementation(transactionRepository).getRewardsByCustomerId(x.getCustomerId())).collect(Collectors.toList());
    }
}

