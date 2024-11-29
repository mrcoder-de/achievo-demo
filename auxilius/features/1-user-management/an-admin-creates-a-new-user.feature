Feature: An admin creates a new user

  Rule: A user must have a valid email address

    Example: Creating a user with an invalid email
      Given a user with an invalid email
      When the admin attempts to create the user
      Then a user-management error should occur with the message "Invalid user email address"

    Example: Creating a user with a valid email
      Given a user with a valid email
      When the admin attempts to create the user
      Then the user should be successfully created

  Rule: A users must have a first and last name

    Example: Creating a user with empty first name
      Given a user with an empty first name
      When the admin attempts to create the user
      Then a user-management error should occur with the message "First name cannot be empty"

    Example: Creating a user with empty last name
      Given a user with an empty last name
      When the admin attempts to create the user
      Then a user-management error should occur with the message "Last name cannot be empty"

  Rule: A user cannot be created if another user with the same email address exists

    Example: Creating a user with an existing email address
      Given an existing user
      When the admin attempts to create a new user with the same email as the existing user
      Then a user-management error should occur with the message "User with this email already exists"
