package calculation.rate;

public class Rate {

    private static final int LIMIT_TIME_FOR_UNLIMITED = 300;
    private static final int LIMIT_TIME_FOR_BASIC = 100;

    private static final double UNLIMITED_PRICE = 100;
    private static final double PER_MINUTE_PRICE = 1.5;
    private static final double BASIC_PRICE = 0.5;

    public double unlimited(double seconds) {
        double minutes = Math.ceil(seconds);

        if(minutes <= LIMIT_TIME_FOR_UNLIMITED)
            return 100;

        return minutes - LIMIT_TIME_FOR_UNLIMITED + UNLIMITED_PRICE;
    }

    public double perMinute(double seconds) {
        return Math.ceil(seconds) * PER_MINUTE_PRICE;
    }

    public double basic(double seconds, String callType) {
        if(callType.equals("02"))
            return 0;

        double limitToSeconds = LIMIT_TIME_FOR_BASIC * 60;

        if(seconds <= limitToSeconds)
            return Math.ceil(seconds / 60) * BASIC_PRICE;

        return LIMIT_TIME_FOR_BASIC * BASIC_PRICE + perMinute(seconds - limitToSeconds);
    }
}
