## README
This project defines an h2 file based database that is created on the first test run under directory data in root project directory.
The project shows that record saved during h2 tests, while available within the test, are not actually saved to file system
so that a second test run does not find them.
Please note that some side-effect is available as the identity is incremented during test run and the new value is seen
by a following test run.
I added some debug printing in the tests to show my case.

You may run test from command line with the following command

gradlew :test --tests "com.example.testh2.MyTableRepoSpec"

