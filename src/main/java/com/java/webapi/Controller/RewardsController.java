package com.java.webapi.Controller;


import com.java.webapi.Models.Customer;
import com.java.webapi.Repository.CustomerRepository;
import com.java.webapi.Service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.webapi.Dao.Rewards;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class RewardsController {
    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/rewards/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }
    @GetMapping(value="/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rewards>> getAllRewardsByCustomer(){
        List<Rewards> customerRewards = rewardsService.getAllRewards();

        List<Rewards> allRewards= rewardsService.getAllRewards();
        return new ResponseEntity<>(allRewards,HttpStatus.OK);

    }

}
