package com.java.webapi.Service;


import com.java.webapi.Dao.Rewards;
import com.java.webapi.Models.Transaction;

import java.util.List;

public interface RewardsService {
       
        public List<Rewards> getAllRewards();

        Rewards getRewardsByCustomerId(Long customerId);
}

