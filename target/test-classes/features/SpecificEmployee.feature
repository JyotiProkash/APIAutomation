Feature: check returned a specific employee 
Background: 
	Given url baseUrl 
	When header Content-Type = 'application/json' 
	
	#Show a specific employee 
Scenario: Show a specific employee
	Given   path '/employee/1' 
	When method get 
	Then status 200
	#And match response.employee_name == "russians_were_here"
	#And def data = { foo: [{"id":"1463","employee_name":"PTesting301020191572493273166","employee_salary":"60000","employee_age":"29","profile_image":""}]} 
	#And match each data.foo contains {"id":'#string',"employee_name":'#string',"employee_salary":'#string',"employee_age":'#string',"profile_image":'#notnull'}
    