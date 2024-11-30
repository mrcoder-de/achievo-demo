Feature: A controller views a list of all cost centers

  Rule: The controller can fetch a list of all cost centers

    Example: Fetching all cost centers
      Given there are multiple cost centers in the system
      When the controller requests the list of all cost centers
      Then the system should return a list containing all cost centers

  Rule: The controller receives an empty list if there are no cost centers

    Example: Fetching cost centers when none exist
      Given there are no cost centers in the system
      When the controller requests the list of all cost centers
      Then the system should return an empty list of cost centers

  @not-implemented
  Rule: The controller can filter the list by providing part of the cost center name

    Example: Filtering cost centers by partial name
      Given there are cost centers named "Marketing", "Sales", and "Human Resources"
      When the controller filters the list with the partial name "Mar"
      Then the system should return a list containing only the "Marketing" cost center

  @not-implemented
  Rule: The controller can filter the list by active/inactive status

    Example: Filtering active cost centers
      Given there are both active and inactive cost centers in the system
      When the controller filters the list for active cost centers
      Then the system should return a list containing only the active cost centers

    Example: Filtering inactive cost centers
      Given there are both active and inactive cost centers in the system
      When the controller filters the list for inactive cost centers
      Then the system should return a list containing only the inactive cost centers
