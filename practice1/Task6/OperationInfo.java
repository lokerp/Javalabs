package Task6;

import java.util.Map;

public record OperationInfo(boolean isSucceed, Map<Banknote, Integer> issuedMoney) { }
