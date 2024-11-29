Feature: A controller creates a cost center activity

  Rule: An activity must have a name

    Example: Creating a valid activity
      Given an existing cost center
      Given a new activity with name "Team Building" for the cost center
      When the controller attempts to create the activity
      Then the activity should be created successfully

    Example: Creating an activity without a name
      Given an existing cost center
      Given a new activity with no name
      When the controller attempts to create the activity
      Then a cost-center-management error should occur with the message "Activity name is required"

  Rule: An activity is active by default

    Example: Verifying default active status of a new activity
      Given an existing cost center
      Given a controller creates a new activity "Training" for the cost center
      When the activity is created
      Then the activity's status should be set to "active"
