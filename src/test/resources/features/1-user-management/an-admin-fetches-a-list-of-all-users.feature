Feature: An admin fetches a list of all users

  Rule: The admin can fetch a list of all users

    Example: Fetching all users
      Given multiple users exist in the system
      When the admin requests a list of all users
      Then all users should be returned

  Rule: The admin can filter the list by active/inactive

    Example: Fetching only active users
      Given both active and inactive users exist
      When the admin requests a list of active users
      Then only active users should be returned

    Example: Fetching only inactive users
      Given both active and inactive users exist
      When the admin requests a list of inactive users
      Then only inactive users should be returned

  Rule: The admin receives an empty list if there are no users

    Example: Fetching users when none exist
      Given no users exist in the system
      When the admin requests a list of all users
      Then an empty list should be returned
