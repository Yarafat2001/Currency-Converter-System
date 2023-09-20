class CurrencyConverter {
    private ExchangeRateProvider exchangeRateProvider;

    public CurrencyConverter(ExchangeRateProvider exchangeRateProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public double convert(double amount, Currency sourceCurrency, Currency targetCurrency) {
        double exchangeRate = exchangeRateProvider.getExchangeRate(sourceCurrency, targetCurrency);
        return amount * exchangeRate;
    }
}
