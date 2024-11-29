Feature: A controller modifies a customer

  Rule: The controller can modify all fields of a customer

    Example: Modifying an existing customer
      Given a customer "Acme Corp" in the system
      When the controller updates the customer's name to "Acme Industries"
      Then the customer's name should be changed to "Acme Industries"

  @not-implemented
  Rule: If a customer does not exist, an error occurs

    Example: Attempting to modify a non-existent customer
      Given there is no customer with ID 999 in the system
      When the controller attempts to modify the customer with ID 999
      Then a customer-management error should occur with the message "Customer not found"
