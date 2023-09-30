public class Bus {
    private final static float goodProfit = 11000;
    private static int busNextID = 1;
    private final int ID;
    private final int totalSeats;
    private int availableSeats;
    private float pricePerSeat;
    public Bus(int totalSeats, int pricePerSear){
        this.ID = getNextID();
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.pricePerSeat = pricePerSear;
    }

    public Bus(){
        this.ID = getNextID();
        this.availableSeats = 0;
        this.pricePerSeat = 0;
        this.totalSeats = 0;
    }

    public Bus(Bus bus) {
        this.ID = getNextID();
        this.totalSeats = bus.totalSeats;
        this.availableSeats = bus.availableSeats;
        this.pricePerSeat = bus.pricePerSeat;
    }

    public int getID() {
        return ID;
    }

    private int getNextID() {
        return busNextID++;
    }


    public void takePassengers(int passCount) throws Exception {
        if (passCount > availableSeats)
            throw new Exception("Нет доступных мест в автобусе");
        availableSeats -= passCount;
    }

    public float getRevenue() {
        return getCorruptedSeats() * pricePerSeat;
    }

    public boolean isProfitable() {
        return getRevenue() >= goodProfit;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    public int getCorruptedSeats() {
        return totalSeats - availableSeats;
    }
}
