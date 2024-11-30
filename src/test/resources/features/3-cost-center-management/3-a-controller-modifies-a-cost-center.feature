Feature: A controller modifies a cost center

  Rule: The controller can change the name, manager and status (active/inactive)

    Example: Changing the name of a cost center
      Given a cost center named "Marketing" exists
      When the controller changes the cost center name to "Digital Marketing"
      Then the cost center's name should be updated to "Digital Marketing"

    Example: Changing the manager of a cost center
      Given a cost center with manager "john@example.com" exists
      When the controller changes its manager to "jane@example.com"
      Then the cost center's manager should be updated to "jane@example.com"

    Example: Changing the status of a cost center
      Given an active cost center exists
      When the controller changes the cost center status to inactive
      Then the cost center's status should be updated to inactive

  @not-implemented
  Rule: The controller cannot change the name of a cost center to an already existing cost center name

    Example: Changing the name of a cost center
      Given a cost center named "Marketing" exists
      Given a cost center named "Digital Marketing" exists
      When the controller changes the name of cost center "Marketing" to "Digital Marketing"
      Then a cost-center-management error should occur with the message "Cost center name already exists"
