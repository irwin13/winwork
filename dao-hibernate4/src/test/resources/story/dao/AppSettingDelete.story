Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: AppSetting delete
Given a AppSetting for delete with id 6FF11123090345659A6DFD8C8C8652G5
When delete AppSetting is executed
Then AppSetting not exists after delete should be true