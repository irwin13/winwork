Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: AppSetting update
Given a AppSetting for update with id 6FF11123090345659A6DFD8C8C8652G4
When update AppSetting is executed with stringValue change to update_value
Then AppSetting after update stringValue should be equals update_value