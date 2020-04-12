# EmployeeSalaries
EmployeeSalaries project reads CSV and JSON data and returns sum for each job title

## Functionality
Program takes as input list of file paths.  

If no file paths are entered the program suggests paths to  resource files inside jar.

Program accepts also path to files inside directory in which jar is residing.

Program based on file extension recognizes the type of algorithm it has to use: CSV or JSON.

Algorithm for CSV (CsvParser) has 1 implementation - utilizes Custom class composed of 2 Scanners

Algorithm for JSON (JsonParser) files has 2 implementations: 
1. PARSE_EMPLOYEE_ARRAY_AS_WHOLE strategy (ParseStrategyEmployeeArrayAsWhole)
    1. This implementation utilizes fully Jackson's ObjectMapper
        1. benefits: using official implementation of jackson parser, faster
        1. drawbacks: more memory usage

1. PARSE_EMPLOYEE_ONE_BY_ONE strategy (ParseStrategyEmployeeOneByOne)
    1. This implementation utilizes also Jackson but in addition to that uses Scanner and Regular Expressions
    1. This allows for reading input file in chunks
    1. Each chunk is representing single Employee 
    1. Then this chunk is parsed using Jackson's ObjectMapper
    1. And has its data extracted (data concerning this Employee's job(Title) and salary)
    1. Then only this extracted data is kept in HashArray of JobAverage(s)
        1. benefit: less memory usage, parsing employee and holding only job title and salary in memory,
        1. drawbacks: slower, not flexible (fe.: ParseStrategyEmployeeOneByOne.EMPLOYEES_ARRAY_FIELD_NAME = "employees")