package file.ops.parsers.impl.json.strategy;

import java.util.Random;

public class ParseStrategyFactory {
    public static ParseStrategyType DEFAULT_TYPE_OF_PARSE_IMPLEMENTATION = ParseStrategyType.PARSE_EMPLOYEE_ARRAY_AS_WHOLE;

    public static IParse getParseStrategyImplementation(ParseStrategyType typeOfParseImplementation) {
        if(typeOfParseImplementation == ParseStrategyType.PARSE_EMPLOYEE_ARRAY_AS_WHOLE)
            return new ParseStrategyEmployeeArrayAsWhole();
        else if(typeOfParseImplementation == ParseStrategyType.PARSE_EMPLOYEE_ONE_BY_ONE)
            return new ParseStrategyEmployeeOneByOne();
        else throw new UnsupportedOperationException("Unsupported ParseStrategyType for JsonParser");
    }

    public static ParseStrategyType getRandomParseStrategyType() {
        Random random = new Random();
        ParseStrategyType typeOfParseImplementation = null;

        if(random.nextDouble() > 0.5) {
            typeOfParseImplementation = ParseStrategyType.PARSE_EMPLOYEE_ARRAY_AS_WHOLE;
        } else typeOfParseImplementation = ParseStrategyType.PARSE_EMPLOYEE_ONE_BY_ONE;

        return typeOfParseImplementation;
    }
}
