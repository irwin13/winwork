Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: AppSetting select
Given AppSetting for select with code findThis
When select AppSetting is executed
Then AppSettingList size after select should be equals 1