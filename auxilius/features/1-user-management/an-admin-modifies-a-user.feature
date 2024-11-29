Feature: An admin modifies a user

  Rule: The admin can modify all fields of a user

    Example: Modifying an existing user
      Given an existing user
      When the admin modifies all the user fields
      Then the changes should be saved successfully

  Rule: If a user does not exist, an error should occur

    Example: Modifying a non-existent user
      Given an email address not assigned to any user
      When the admin attempts to modify the user
      Then a user-management error should occur with the message "User with provided email not found"
