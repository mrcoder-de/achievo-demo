# Requirements

## User Management

- An admin creates a user
    - the user must have a valid email address
    - the first and last name fields may not be empty
    - the user cannot be created if another user with the same address exists
- An admin fetches a single user by email
    - The admin can should find an existing user
    - If a user does not exist, an error should occur
- An admin modifies a user
    - the admin can modify all fields of a user
    - if a user does not exist, an error occurs
- An admin fetches a list of all users
    - the admin can fetch a list of all users
    - the admin can filter the list by active/inactive
    - the admin receives an empty list if there are no users
