Feature: check create an employee 
Background: 
	Given url baseUrl  
	When header Content-Type = 'application/json' 
	
	# Show a newly created employee
	Scenario: Create a new employee. 
	Given path '/create'
    And request {"name":"Hasan Ss fjhkh", "salary":"21000", "age":"32"}
    When method post
    Then status 200
    And def createdData = response
    #And match $ contains {"name":'#(newData.name)',"salary":'#(newData.salary)',"age": '#(newData.age)', "id": '#(newData.id)'}
    And assert createdData.age == ["32"]

  