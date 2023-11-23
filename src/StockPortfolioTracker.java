import java.util.HashMap;
import java.util.Map; // Import the Map interface
import java.util.Scanner;

public class StockPortfolioTracker {
    // Map from stock symbol to stock price (in dollars)
    private static final Map<String, Double> stockPrices = new HashMap<>();

    // Map from stock symbol to number of shares owned by user
    private final Map<String, Integer> portfolio = new HashMap<>();

    public StockPortfolioTracker() {
        initializeStockPrices(); // Initialize stockPrices map
    }

    // Initialize stockPrices map with some stock symbols and prices
    private void initializeStockPrices() {
        stockPrices.put("APPLE", 150.0);
        stockPrices.put("GOOGLE", 2800.0);
        stockPrices.put("AMAZON", 3400.0);
    }

    // Display menu options
    private void displayMenu() {
        System.out.println("1. Display Portfolio");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. Quit");
    }

    // Display portfolio contents (stock symbol, quantity, current price, total value)
    private void displayPortfolio() {
        System.out.println("Portfolio:");

        // Iterate through a portfolio map using a for-each loop
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            String stockSymbol = entry.getKey(); // Get stock symbol
            int quantity = entry.getValue(); // Get number of shares owned

            double stockPrice = stockPrices.get(stockSymbol); // Get current stock price

            // Calculate the total value of shares owned
            double totalValue = quantity * stockPrice;

            System.out.println(stockSymbol + ": " + quantity
                    + " shares | Current Price: $" + stockPrice
                    + " | Total Value: $" + totalValue);
        }
        System.out.println();
    }

    // Buy stock
    private void buyStock() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter stock symbol to buy:");
        String stockSymbol = userInput.next().toUpperCase();

        // Check if stock symbol is valid (i.e. exists in stockPrices map)
        if (!stockPrices.containsKey(stockSymbol)) {
            System.out.println("Invalid stock symbol");
            return;
        }

        System.out.println("Enter quantity to buy:");
        int quantity = userInput.nextInt();

        // Check if the quantity is valid (i.e., positive) and check if user owns enough shares
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }

        double stockPrice = stockPrices.get(stockSymbol); // Get current stock price
        double totalCost = quantity * stockPrice; // Calculate the total cost of shares

        // Update a portfolio map with new stock symbol and quantity
        portfolio.put(stockSymbol, portfolio.getOrDefault(stockSymbol, 0) + quantity);

        System.out.println("Bought " + quantity + " shares of " + stockSymbol + " for $" + totalCost);
    }

    // Sell stock (similar to buyStock method)
    private void sellStock() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter stock symbol to sell:");
        String stockSymbol = userInput.next().toUpperCase();

        // Check if stock symbol is valid (i.e. exists in stockPrices map)
        if (!portfolio.containsKey(stockSymbol) || portfolio.get(stockSymbol) == 0) {
            System.out.println("You don't own any shares of " + stockSymbol);
            return;
        }

        System.out.println("Enter quantity to sell:");
        int quantity = userInput.nextInt();

        // Check if the quantity is valid (i.e., positive) and check if user owns enough shares
        if (quantity <= 0 || quantity > portfolio.get(stockSymbol)) {
            System.out.println("Invalid quantity");
            return;
        }

        double stockPrice = stockPrices.get(stockSymbol); // Get current stock price
        double totalValue = quantity * stockPrice; // Calculate the total value of shares

        // Update a portfolio map with new stock symbol and quantity
        portfolio.put(stockSymbol, portfolio.get(stockSymbol) - quantity);

        System.out.println("Sold " + quantity + " shares of " + stockSymbol + " for $" + totalValue);
    }

    // Start the program by displaying the menu and getting user input
    public void start() {
        Scanner userInput = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.println("Enter your choice:");
            choice = userInput.nextInt();

            switch (choice) {
                case 1: // For Display portfolio
                    displayPortfolio();
                    break;
                case 2: // For Buy stock
                    buyStock();
                    break;
                case 3: // For sell stock
                    sellStock();
                    break;
                case 4: // For Quit
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default: // For invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4); // Exit the loop when user enters 4
    }

    public static void main(String[] args) {
        // Create a new StockPortfolioTracker object and start the program
        StockPortfolioTracker tracker = new StockPortfolioTracker();
        tracker.start(); // Start the program by displaying the menu and getting user input
    }
}
