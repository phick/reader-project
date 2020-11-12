Steps to set up your project

1. Clone the application
	
	git clone https://github.com/phick/reader-project.git
	cd reader-project

2. Create MySQL database and tables
	
	Run file src/db/create-schema.sql (Running in IDE may required datasource attaching)

3. Change MySQL username and password.

	Open file src/main/resources/config.properties
	Change „datasource-username” and „datasource-password” to valid values.

4. Run the app.