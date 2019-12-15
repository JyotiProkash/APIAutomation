Feature: check returned all employees 
Background: 
	Given url baseUrl
	When header Content-Type = 'application/json'
	
	# Show list of all employees
Scenario: List all activities
	Given   path '/employees' 
	When method get 
	Then status 200
	* def data = { foo: [{"id":"1463","employee_name":"PTesting301020191572493273166","employee_salary":"60000","employee_age":"29","profile_image":""}]} 
	* match each data.foo contains {"id":'#string',"employee_name":'#string',"employee_salary":'#string',"employee_age":'#string',"profile_image":'#notnull'}
	