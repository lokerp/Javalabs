package Task6;

import java.util.Map;
import java.util.TreeMap;

public class Terminal {
    private TreeMap<Banknote, Integer> availableMoney;

    public Terminal(Map<Banknote, Integer> availableMoney){
        this.availableMoney = new TreeMap<>(availableMoney);
    }

    public OperationInfo returnMoney(int moneyCount) {
        Map<Banknote, Integer> issuedMoney = new TreeMap<>(availableMoney);
        issuedMoney.entrySet().forEach((x) -> x.setValue(0));

        int returnedMoneyCount = 0;
        for (Map.Entry<Banknote, Integer> item : availableMoney.descendingMap().entrySet()){

            int numToUse = (moneyCount - returnedMoneyCount) / item.getKey().denomination();
            int returnByCurrNominal = numToUse * item.getKey().denomination();

            returnedMoneyCount += returnByCurrNominal;

            item.setValue(item.getValue() - numToUse);
            issuedMoney.put(item.getKey(), numToUse);
        }

        if (moneyCount != returnedMoneyCount)
            return new OperationInfo(false, null);
        else
            return new OperationInfo(true, issuedMoney);
    }

    public Map<Banknote, Integer> getAvailableMoney() {
        return availableMoney;
    }
}
