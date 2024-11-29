Feature: A controller creates a new customer

  Rule: Customer must have a name, contact email, phone number and billing address

    Example: Creating a customer with all required information
      Given a new customer with name "Acme Corp", email "contact@acme.com", phone "+1-555-1234", and address "123 Main St, Anytown, USA"
      When the controller attempts to create the customer
      Then the customer should be successfully created

    Example: Creating a customer with missing information
      Given a new customer with name "Acme Corp" and email "contact@acme.com"
      When the controller attempts to create the customer
      Then a customer-management error should occur with the message "Missing required fields"

  @not-implemented
  Rule: Customer must have a valid contact email

    Example: Creating a customer with an invalid email
      Given a new customer with name "Acme Corp", email "notanemail", phone "+1-555-1234", and address "123 Main St, Anytown, USA"
      When the controller attempts to create the customer
      Then a customer-management error should occur with the message "Invalid customer email address"

  @not-implemented
  Rule: Customer must have a valid phone number

    Example: Creating a customer with an invalid phone number
      Given a new customer with name "Acme Corp", email "contact@acme.com", phone "12345", and address "123 Main St, Anytown, USA"
      When the controller attempts to create the customer
      Then a customer-management error should occur with the message "Invalid phone number"
