package file.ops.parsers.impl.json.strategy;

public enum ParseStrategyType {
    /**
     * benefits: using official implementation of jackson parser, faster
     * drawbacks: more memory usage
     **/
    PARSE_EMPLOYEE_ARRAY_AS_WHOLE,

    /**
     * benefit: less memory usage, parsing employee and holding only job title and salary in memory,
     * drawbacks: slower, not flexible (fe.: EMPLOYEES_ARRAY_FIELD_NAME = "employees")
     * todo: implement chunk file reading, now it reads whole file which goes against the whole idea of this strategy
     */
    PARSE_EMPLOYEE_ONE_BY_ONE;
}
