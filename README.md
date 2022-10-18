# project_2

Eureka Service - Keeps track of IP-Address to service name mappings for microservice set.

Gateway service - Forwards incoming requests to correct microservice based on root path and performs basic authentication by integrating with eureka registry.

Register Service - Used for logging users in, out, and registering.

Customer Service - Used to update and delete customers.

Product Service - Used to perform CRUD operations on Products. 

Order Service - Submits orders for existing products and attempts to withdraw payment from the authneticated user's account by sending a request to Payment Service.

Payment Service - A downstream service of product service. Can submit payments (deduct from account balance) and refund payments (add to account balance). 

Entities - A shared Jar that defines all of the tables of the project as Abstract JPA MappedSuperClasses.
