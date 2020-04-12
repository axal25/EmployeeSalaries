package file.ops.parsers.impl.json.strategy;

public enum ParseStrategyType {
    /**
     * benefits: using official implementation of jackson parser, faster
     * drawbacks: more memory usage
     **/
    PARSE_EMPLOYEE_ARRAY_AS_WHOLE,

    /**
     * benefit: less memory usage, parsing employee and holding only job title and salary in memory,
     * drawbacks: slower, not flexible (fe.: ParseStrategyEmployeeOneByOne.EMPLOYEES_ARRAY_FIELD_NAME = "employees")
     */
    PARSE_EMPLOYEE_ONE_BY_ONE;
}
