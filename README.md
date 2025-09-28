# interview-test-app API automation - Spribe

## Overview
Automated tests for the Player Controller API with covering positive & negative scenarios, response validation, and critical bug checks.  


## Stack
- Java 11+/17, TestNG, RestAssured
## Name Convention


- SP(Spribe) - the test case key in the TMS structure.  
- SPBUG - the issue (bug report) in the BTS structure

## Configuration
`config.properties`:
```properties
base.url=http://3.68.165.45
thread.count=3

Features
Data-driven tests via TestNG DataProvider

Parallel execution (3 threads)

Validation of player IDs, screenNames, age, gender etc.

Logs requests/responses for debugging

Author
Dmytro.
