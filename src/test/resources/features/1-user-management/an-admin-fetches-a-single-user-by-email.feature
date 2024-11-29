Feature: An admin fetches a single user by email

  Rule: The admin can should find an existing user

    Example: Finding an existing user
      Given an existing user
      When the admin searches for the user with the users email address
      Then the user is found

  Rule: If a user does not exist, an error should occur

    Example: Finding a non-existent user
      Given an email address not assigned to any user
      When the admin searches for the user with the users email address
      Then a user-management error should occur with the message "User with provided email not found"
