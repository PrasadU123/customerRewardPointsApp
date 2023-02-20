# customerRewardPointsApp
#The rest API calculates the customer reward points based on customer Id

#A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

The package name is structured as com.retailer.rewards
Exception is thrown if customer does not exists.
H2 in-memory database to store the information.
Run the application and automatically data will get load from the .json file.To check at db-level open the H2-console through browser(with username and password which is provided in the application.properties file.

To check the endpoints.

 http://localhost:8080/customers/rewards/{customerId} - Reward points per customerId
 
 eg:
 http://localhost:8080/customers/rewards/1     -> we have data with customerId as 1 in h2 db.
 http://localhost:8080/customers/rewards/2     -> we have data with customerId as 2 in h2 db.
 
 
 
 http://localhost:8080/customers/rewards - Reward points of all customer.
 
 
