Feature: A controller views a list of all customers

  Rule: The controller can fetch a list of all customers

    Example: Fetching all customers when customers exist
      Given there are 3 customers in the system
      When the controller requests the list of all customers
      Then the controller should receive a list containing 3 customers

  Rule: The controller can filter the list by providing part of the customer name

    Example: Filtering customers by partial name
      Given there are customers named "Acme Corp", "Acme Industries", and "Zenith Ltd" in the system
      When the controller requests the list of customers with the filter "Acme"
      Then the controller should receive a list containing 2 customers

  Rule: The controller receives an empty list if there are no customers

    Example: Fetching customers when no customers exist
      Given there are no customers in the system
      When the controller requests the list of all customers
      Then the controller should receive an empty list of customers
