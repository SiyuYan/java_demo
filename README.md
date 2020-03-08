## Instruction 
- For unit test :`mvn test`
- For api test :`mvn integration-test`

## Test cases
- file1 and file2 match normal cases with different urls inside
- file3 match special case which no url inside
- file4 and file5 match the case which thousands of url inside

## Architecture description

### We are trying to compare the json response pair of urls simultaneously.

There some key points we need to solve from technically. 

1. Design pattern which can easily extend comparator (e.g. json, xml)
2. The HttpClient which used to handle remote restful url call 
3. The multi thread capability which can support multi urls compare efficiently
4. The FileUtils which can handle the file reader separately.


### Based on these key points I have split into below structure

  - comparator (Handle the compare function of different data structure)
    - IComparator (interface of all the comparators)
    - JsonComparator (used to compare Json) 
  - http (Handle http connection, and related parameter checks)
    - HttpClient (send http request)
    - UrlChecker (validator for Url)
  - multithread (multi-thread capability to handle HTTP request)
    - MultiUrlsCompare (use multi-thread capability to compare multiply url results)
    - UrlCompareTask (The task to do single pair of url compare which implement Callable )
  - utils (all the utility package)
    - FileUtils (used to handle File related functions)
    
## Test file folder description

- api folder contains all the integration(api) test suites
- com folder contains all the unit test suites
