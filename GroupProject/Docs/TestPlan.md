# Test Plan

**Author**: Team11

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

Our testing strategy will be as follows:
* Classes should be developed in such a manner that unit tests CAN (and should) be written. This means striving for low dependency across classes and creating modular interfaces. Any classes that can be unit tested will be. Unit tests should be automatable.
* Collection classes or groups of classes that compose specific subsystems will be subject to integration tests. Integration tests will not be implemented for purely contrived subsystems. This means that we will primarily focust on testing subsystems that will exist in the 'Wheel of Fortune' application and no more. Integration tests should be automatable. 
* We have decided to neglect writing a full system test in an automative form. All full system level tests will be done in the manual QA testing process. This does not mean that these tests will not be recorded. Specific step by step instructions will be provided for testing specific use cases. 

To some degree, all team members will be responsible for testing different aspects of the project. It is recommended that a different engineer be responsible for testing a newly authored code. This rule does not have to be strictly followed, but should be viewed as a best practice. 

### 1.2 Test Selection

Both our unit tests and our integration tests will be written in the 'white' box format. In both cases the developer writing the test case should consider creating workflows (diagrams or otherwise) to visualize which code paths are followed and when. Upon understanding these various code paths, it is the developer's responsibility to get a reasonabls amout of code coverage. 100% code coverage in automated testing is an admiral goal, but sometimes a non-functional one. 

Our system level test will be done more from the black box perspective. There will be a listing of tests (i.e. use cases) that should be regularly checked with new builds of the project. Additionally the team will be responsible for creating new tests as the project develops. This means tesing expected behavior of the application.  If, at any point, the tester discovers a particular workflow that does not produce an expected result (aka a bug), that workflow will be added to the use case listing.

### 1.3 Adequacy Criterion

To verify that the tests we create meet the minimum standard of quality for this application we will use a combination of both functional and structural test coverage. At the unit test level we will keep track of the structual coverage at the minimum. Additionally, the test writer may additionally write functional tests at the unit test level, if the test makes sense. 

At the integration test level, we will focus primarily on the functional coverage of the subsystems. Since the unit tests will cover the structural coverage of the code, we do not need to create tests for that at this level. However, if the test writer decides structural coverage is needed at this level as well, adding tests in that domain is satisfactory. 

### 1.4 Bug Tracking

Bugs and enhancement requests will be handled using GitHub Issues for the project ([Issues][076fe412]). We will track any issues found in the app or app enhancement needs here.

### 1.5 Technology

Currently, the only planned testing technology is JUnit. If the project proves to need additional UI testing Espresso tests will be implemented.

## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*




[076fe412]: https://github.gatech.edu/gt-omscs-se-2018spring/6300Spring18Team11/issues "GitHub Issues"
