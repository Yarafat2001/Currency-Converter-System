
import java.util.*;

public class CurrencyConversionContext {
    private User currentUser;
    private Map<String, User> users;
    private ExchangeRateProvider exchangeRateProvider;
    private CurrencyConverter currencyConverter;
    private List<String> conversionHistory;
    private Menu currentMenu;

    public CurrencyConversionContext() {
        users = new HashMap<>();
        exchangeRateProvider = new SimpleExchangeRateProvider();
        currencyConverter = new CurrencyConverter(exchangeRateProvider);
        conversionHistory = new ArrayList<>();
        currentMenu = new MainMenu();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            currentMenu.display();
            String choice = scanner.nextLine();
            running = currentMenu.handleInput(choice, scanner);
        }
    }

    private interface Menu {
        void display();
        boolean handleInput(String choice, Scanner scanner);
    }

    private class MainMenu implements Menu {

        public void display() {
            System.out.println("Currency Converter App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Display Conversion History");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
        }


        public boolean handleInput(String choice, Scanner scanner) {
            Map<String, Runnable> options = new HashMap<>();
            options.put("1", () -> registerUser(scanner));
            options.put("2", () -> loginUser(scanner));
            options.put("3", () -> displayConversionHistory());
            options.put("4", () -> System.exit(0));

            Runnable action = options.get(choice);
            if (action != null) {
                action.run();
                return true;
            } else {
                System.out.println("Invalid option. Try again.");
                return true;
            }
        }
    }

    private class LoggedInMenu implements Menu {

        public void display() {
            System.out.println("Logged in as " + currentUser.getUsername());
            System.out.println("1. Currency Conversion");
            System.out.println("2. Logout");
            System.out.println("3. Display Conversion History");
            System.out.print("Select an option: ");
        }


        public boolean handleInput(String choice, Scanner scanner) {
            Map<String, Runnable> options = new HashMap<>();
            options.put("1", () -> performCurrencyConversion(scanner));
            options.put("2", () -> currentUser = null);
            options.put("3", () -> displayConversionHistory());

            Runnable action = options.get(choice);
            if (action != null) {
                action.run();
                return true;
            } else {
                System.out.println("Invalid option. Try again.");
                return true;
            }
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful.");
    }

    private void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful.");
            currentMenu = new LoggedInMenu(); // Set the menu to LoggedInMenu
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }


    private void performCurrencyConversion(Scanner scanner) {
        System.out.println("Available Currency Codes:");
        System.out.println("USD - United States Dollar");
        System.out.println("EUR - Euro");
        System.out.println("JPY - Japanese Yen");
        System.out.println("BDT - Bangladeshi Taka");
        System.out.println("INR - Indian Rupee");
        System.out.println("SAR - Saudi Riyal");
        System.out.println("AED - United Arab Emirates Dirham");
        System.out.println("GBP - British Pound Sterling");
        System.out.println("CAD - Canadian Dollar");

        System.out.print("enter the source currency code ");
        String sourceCode = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency code: ");
        String targetCode = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Currency sourceCurrency = new Currency(sourceCode, "", "");
        Currency targetCurrency = new Currency(targetCode, "", "");

        double exchangeRate = exchangeRateProvider.getExchangeRate(sourceCurrency, targetCurrency);
        if (exchangeRate == -1.0) {
            System.out.println("Unsupported currency pair. Conversion not possible.");
            return;
        }

        double convertedAmount = currencyConverter.convert(amount, sourceCurrency, targetCurrency);

        System.out.println(amount + " " + sourceCurrency.getSymbol() + " (" + sourceCurrency.getCode() + ") is equivalent to " +
                convertedAmount + " " + targetCurrency.getSymbol() + " (" + targetCurrency.getCode() + ")");
        String conversionRecord =
                amount + " " + sourceCurrency.getSymbol() + " (" + sourceCurrency.getCode() + ") converted to " +
                        convertedAmount + " " + targetCurrency.getSymbol() + " (" + targetCurrency.getCode() + ")";
        conversionHistory.add(conversionRecord);

        System.out.println(conversionRecord);
    }

    public void displayConversionHistory() {
        if (conversionHistory.isEmpty()) {
            System.out.println("No conversion history available.");
        } else {
            System.out.println("Conversion History:");
            for (String record : conversionHistory) {
                System.out.println(record);
            }
        }
    }

    public static void main(String[] args) {
        CurrencyConversionContext context = new CurrencyConversionContext();
        context.execute();
    }
}
/*import java.util.*;

public class CurrencyConversionContext {
    private User currentUser;
    private Map<String, User> users;
    private ExchangeRateProvider exchangeRateProvider;
    private CurrencyConverter currencyConverter;
    private List<String> conversionHistory;

    public CurrencyConversionContext() {
        users = new HashMap<>();
        exchangeRateProvider = new SimpleExchangeRateProvider();
        currencyConverter = new CurrencyConverter(exchangeRateProvider);
        conversionHistory = new ArrayList<>();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                showMainMenu(scanner);
            } else {
                showLoggedInMenu(scanner);
            }
        }
    }

    private void showMainMenu(Scanner scanner) {
        System.out.println("Currency Converter App");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Display Conversion History");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                registerUser(scanner);
                break;
            case 2:
                loginUser(scanner);
                break;
            case 3:
                displayConversionHistory();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private void showLoggedInMenu(Scanner scanner) {
        System.out.println("Logged in as " + currentUser.getUsername());
        System.out.println("1. Currency Conversion");
        System.out.println("2. Logout");
        System.out.println("3. Display Conversion History");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                performCurrencyConversion(scanner);
                break;
            case 2:
                currentUser = null;
                break;
            case 3:
                displayConversionHistory();
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful.");
    }

    private void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private void performCurrencyConversion(Scanner scanner) {

        System.out.println("Available Currency Codes:");
        System.out.println("USD - United States Dollar");
        System.out.println("EUR - Euro");
        System.out.println("JPY - Japanese Yen");
        System.out.println("BDT - Bangladeshi Taka");
        System.out.println("INR - Indian Rupee");
        System.out.println("SAR - Saudi Riyal");
        System.out.println("AED - United Arab Emirates Dirham");
        System.out.println("GBP - British Pound Sterling");
        System.out.println("CAD - Canadian Dollar");

        System.out.print("Enter the source currency code: ");
        String sourceCode = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency code: ");
        String targetCode = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Currency sourceCurrency = new Currency(sourceCode, "", "");
        Currency targetCurrency = new Currency(targetCode, "", "");

        double convertedAmount = currencyConverter.convert(amount, sourceCurrency, targetCurrency);

        System.out.println(amount + " " + sourceCurrency.getSymbol() + " (" + sourceCurrency.getCode() + ") is equivalent to " +
                convertedAmount + " " + targetCurrency.getSymbol() + " (" + targetCurrency.getCode() + ")");
        String conversionRecord =
                amount + " " + sourceCurrency.getSymbol() + " (" + sourceCurrency.getCode() + ") converted to " +
                        convertedAmount + " " + targetCurrency.getSymbol() + " (" + targetCurrency.getCode() + ")";
        conversionHistory.add(conversionRecord);

        System.out.println(conversionRecord);
    }

    public void displayConversionHistory() {
        if (conversionHistory.isEmpty()) {
            System.out.println("No conversion history available.");
        } else {
            System.out.println("Conversion History:");
            for (String record : conversionHistory) {
                System.out.println(record);
            }
        }
    }
}*/

