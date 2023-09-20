import java.util.HashMap;
import java.util.Map;

interface ExchangeRateProvider {
    double getExchangeRate(Currency sourceCurrency, Currency targetCurrency);
}

class SimpleExchangeRateProvider implements ExchangeRateProvider {
    private Map<String, Double> exchangeRates;

    public SimpleExchangeRateProvider() {
        exchangeRates = new HashMap<>();

        initializeExchangeRates();
    }

    private void initializeExchangeRates() {
        double usdToEur = 0.94;
        double usdToJpy = 147.74;
        double usdToBdt = 109.53;
        double usdToInr = 83.24;
        double usdToSar = 3.75;
        double usdToAed = 3.67;
        double usdToGbp = 0.81;
        double usdToCad = 1.35;

        exchangeRates.put("USD->EUR", usdToEur);
        exchangeRates.put("USD->JPY", usdToJpy);
        exchangeRates.put("USD->BDT", usdToBdt);
        exchangeRates.put("USD->INR", usdToInr);
        exchangeRates.put("USD->SAR", usdToSar);
        exchangeRates.put("USD->AED", usdToAed);
        exchangeRates.put("USD->GBP", usdToGbp);
        exchangeRates.put("USD->CAD", usdToCad);

        exchangeRates.put("EUR->USD", 1.0 / usdToEur);
        exchangeRates.put("JPY->USD", 1.0 / usdToJpy);
        exchangeRates.put("BDT->USD", 1.0 / usdToBdt);
        exchangeRates.put("INR->USD", 1.0 / usdToInr);
        exchangeRates.put("SAR->USD", 1.0 / usdToSar);
        exchangeRates.put("AED->USD", 1.0 / usdToAed);
        exchangeRates.put("GBP->USD", 1.0 / usdToGbp);
        exchangeRates.put("CAD->USD", 1.0 / usdToCad);

        double eurToJpy = usdToJpy / usdToEur;
        double eurToBdt = usdToBdt / usdToEur;
        double eurToGbp = 0.86;
        double eurToCad = 1.44;
        double eurToInr = 88.93;
        double eurToSar = 4.01;
        double eurToAed = 3.92;


        exchangeRates.put("EUR->JPY", eurToJpy);
        exchangeRates.put("EUR->BDT", eurToBdt);
        exchangeRates.put("EUR->GBP", eurToGbp);
        exchangeRates.put("EUR->CAD", eurToCad);
        exchangeRates.put("EUR->INR", eurToInr);
        exchangeRates.put("EUR->SAR", eurToSar);
        exchangeRates.put("EUR->AED", eurToAed);

        double jpyToEur = 0.0063;
        double jpyToBdt = 0.74;
        double jpyToGbp = 0.0055;
        double jpyToCad = 0.0091;
        double jpyToInr = 0.56;
        double jpyToSar = 0.025;
        double jpyToAed = 0.025;

        exchangeRates.put("JPY->EUR", jpyToEur);
        exchangeRates.put("JPY->BDT", jpyToBdt);
        exchangeRates.put("JPY->GBP", jpyToGbp);
        exchangeRates.put("JPY->CAD", jpyToCad);
        exchangeRates.put("JPY->INR", jpyToInr);
        exchangeRates.put("JPY->SAR", jpyToSar);
        exchangeRates.put("JPY->AED", jpyToAed);

        double bdtToEur = 0.0063;
        double bdtToJpy = 0.74;
        double bdtToGbp = 0.0055;
        double bdtToCad = 0.0091;
        double bdtToInr = 0.56;
        double bdtToSar = 0.025;
        double bdtToAed = 0.025;

        exchangeRates.put("BDT->EUR", bdtToEur);
        exchangeRates.put("BDT->JPY", bdtToJpy);
        exchangeRates.put("BDT->GBP", bdtToGbp);
        exchangeRates.put("BDT->CAD", bdtToCad);
        exchangeRates.put("BDT->INR", bdtToInr);
        exchangeRates.put("BDT->SAR", bdtToSar);
        exchangeRates.put("BDT->AED", bdtToAed);

        double inrToEur = 0.011;
        double inrToJpy = 1.78;
        double inrToGbp = 0.0097;
        double inrToCad = 0.016;
        double inrToBdt = 1.32;
        double inrToSar = 0.045;
        double inrToAed = 0.044;

        exchangeRates.put("INR->EUR", inrToEur);
        exchangeRates.put("INR->JPY", inrToJpy);
        exchangeRates.put("INR->GBP", inrToGbp);
        exchangeRates.put("INR->CAD", inrToCad);
        exchangeRates.put("INR->BDT", inrToBdt);
        exchangeRates.put("INR->SAR", inrToSar);
        exchangeRates.put("INR->AED", inrToAed);

        double sarToEur = 0.25 ;
        double sarToJpy = 39.39;
        double sarToGbp = 0.22;
        double sarToCad = 0.36;
        double sarToBdt = 29.20;
        double sarToInr = 22.19;
        double sarToAed = 0.98;

        exchangeRates.put("SAR->EUR", sarToEur);
        exchangeRates.put("SAR->JPY", sarToJpy);
        exchangeRates.put("SAR->GBP", sarToGbp);
        exchangeRates.put("SAR->CAD", sarToCad);
        exchangeRates.put("SAR->BDT", sarToBdt);
        exchangeRates.put("SAR->INR", sarToInr);
        exchangeRates.put("SAR->AED", sarToAed);

        double aedToEur = 0.25 ;
        double aedToJpy = 40.23;
        double aedToGbp = 0.22;
        double aedToCad = 0.37;
        double aedToBdt = 29.82;
        double aedToInr = 22.66;
        double aedToSar = 0.98;

        exchangeRates.put("AED->EUR", aedToEur);
        exchangeRates.put("AED->JPY", aedToJpy);
        exchangeRates.put("AED->GBP", aedToGbp);
        exchangeRates.put("AED->CAD", aedToCad);
        exchangeRates.put("AED->BDT", aedToBdt);
        exchangeRates.put("AED->INR", aedToInr);
        exchangeRates.put("AED->SAR", aedToSar);

        double gbpToEur = 0.25 ;
        double gbpToJpy = 182.95;
        double gbpToAed = 4.55;
        double gbpToCad = 1.67;
        double gbpToBdt = 135.61;
        double gbpToInr = 103.07;
        double gbpToSar = 4.64;

        exchangeRates.put("GBP->EUR", gbpToEur);
        exchangeRates.put("GBP->JPY", gbpToJpy);
        exchangeRates.put("GBP->AED", gbpToAed);
        exchangeRates.put("GBP->CAD", gbpToCad);
        exchangeRates.put("GBP->BDT", gbpToBdt);
        exchangeRates.put("GBP->INR", gbpToInr);
        exchangeRates.put("GBP->SAR", gbpToSar);

        double cadToEur = 0.69 ;
        double cadToJpy = 109.64;
        double cadToGbp = 0.60;
        double cadToAed = 2.72;
        double cadToBdt = 81.27;
        double cadToInr = 61.77;
        double cadToSar = 2.78;

        exchangeRates.put("CAD->EUR", cadToEur);
        exchangeRates.put("CAD->JPY", cadToJpy);
        exchangeRates.put("CAD->GBP", cadToGbp);
        exchangeRates.put("CAD->AED", cadToAed);
        exchangeRates.put("CAD->BDT", cadToBdt);
        exchangeRates.put("CAD->INR", cadToInr);
        exchangeRates.put("CAD->SAR", cadToSar);


        String[] supportedCurrencies = {"USD", "EUR", "JPY", "BDT", "INR", "SAR", "AED", "GBP", "CAD"};
        for (String currency : supportedCurrencies) {
            exchangeRates.put(currency + "->" + currency, 1.0);
        }
    }

    public double getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        String key = sourceCurrency.getCode() + "->" + targetCurrency.getCode();
        return exchangeRates.getOrDefault(key, 1.0);
    }
}


