Feature: A controller creates a cost center

  Rule: A cost center must have a name and a manager

    Example: Creating a valid cost center
      Given a new cost center with name "Marketing" and manager "john@example.com"
      When the controller attempts to create the cost center
      Then the cost center should be created successfully

    Example: Creating a cost center without a name
      Given a new cost center with manager "john@example.com" and no name
      When the controller attempts to create the cost center
      Then a cost-center-management error should occur with the message "Cost center name is required"

    Example: Creating a cost center without a manager
      Given a controller wants to create a new cost center
      When they provide only a name "Marketing" but no manager
      Then a cost-center-management error should occur with the message "Cost center manager is required"

  Rule: A cost center is active by default

    Example: Verifying default active status
      Given a new cost center with name "Sales" with manager "jane@example.com"
      When the cost center is created
      Then the cost center's status should be set to "active"
