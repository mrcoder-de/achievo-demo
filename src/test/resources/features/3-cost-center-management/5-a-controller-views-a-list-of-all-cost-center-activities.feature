Feature: A controller views a list of all cost center activities

  Rule: The controller can fetch a list of all activities for a cost center

    Example: Fetching all activities for a cost center
      Given a cost center "Marketing" has multiple activities
      When the controller requests the list of all activities for "Marketing"
      Then the system should return a list containing all activities for that cost center

  @not-implemented
  Rule: The controller receives an empty list if there are no activities

    Example: Fetching activities when none exist for a cost center
      Given a cost center "Sales" has no activities
      When the controller requests the list of all activities for "Sales"
      Then the system should return an empty list of activities
