Feature: check delete a employee 
Background: 
	Given url baseUrl  
	When header Content-Type = 'application/json' 
	
	#A specific employee record deleted 
Scenario: Delete a specific employee
	Given   path '/delete/153100' 
	When method delete 
	Then status 200
	And match response == {"success": {"text": "successfully! deleted Records"}}
	
	
	
    