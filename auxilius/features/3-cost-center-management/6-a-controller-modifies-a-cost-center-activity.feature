Feature: A controller modifies a cost center activity

  Rule: The controller can change the name and status (active/inactive) of an activity

    Example: Changing the name of an activity
      Given a cost center exists
      Given an activity named "Team Building" exists for the cost center
      When the controller changes the activity name to "Team Outing"
      Then the activity's name should be updated to "Team Outing"

    Example: Changing the status of an activity
      Given a cost center exists
      Given an active activity exists for the cost center
      When the controller changes the activity status to inactive
      Then the activity's status should be updated to inactive
