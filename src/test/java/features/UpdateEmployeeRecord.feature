Feature: check update an employee record 

Background: 
	Given url baseUrl  
	When header Content-Type = 'application/json' 
	
	# Update an employee
	@tagdemo
	Scenario: Update an existing employee. 
	Given path '/update/157061'
    And request {"name":"Hasan Sas", "salary":"34000", "age":"29"}
    When method put
    Then status 200
    And def updateData = response
    And match $ contains {"name":'#(updateData.name)',"salary":'#(updateData.salary)',"age": '#(updateData.age)'}