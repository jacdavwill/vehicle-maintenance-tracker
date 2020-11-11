# vehicle-maintenance-tracker
Front end can be found at https://github.com/jacdavwill/vehicle-maintenance-tracker-ui

### Running DAO tests locally
In order to be able to successfullly run DAO tests locally you need to make sure the 
application-test.properties correctly point to a postgres database that is running locally. I would
recommend setting up a vehicle_maintenance_test database that is separate from your local 
vehicle_maintenance database (just so you don't mess up any data in the other one by running the 
tests).

Each DAO test class connects to the database defined by the application-test.properties file, drops the
tables, creates them again, and then populates the database with dummy data that can be found in
dummyData.txt (additional dummyData files can be created and pointed at as we see fit). The DAO 
classes are marked as Transactional, so the changes made to the database in each test will be rolled
back before the next test.