package org.example;

import java.util.*;

public class Main {
    /**
     * Billing:
     * Overcharges
     * Missing Payments
     * Network Issues:
     * Slow Connection
     * No Connection
     * User Queries:
     * "I have a billing issue."
     * "The internet is very slow."
     * "I want to check overcharges."
     * <p>
     * Predefined Responses
     * "Billing": "You can ask about overcharges or missing payments."
     * "Overcharges": "Please provide your account number for investigation."
     * "Missing Payments": "Please check your payment history or contact support."
     * "Network Issues": "You can ask about slow connection or no connection."
     * "Slow Connection": "Restart your router and try again. If the issue persists, contact support."
     * "No Connection": "Check your cables and modem, then contact support."
     */
    private static final String DEFAULT_RESPONSE = "I'm sorry, I didn't understand that. Please try again.";

    private static final Map<String, String[]> CATEGORIES = new HashMap<>();

    static {
        CATEGORIES.put("Billing", new String[]{"Overcharges", "Missing Payments"});
        CATEGORIES.put("Network Issues", new String[]{"Slow Connection", "No Connection"});
        CATEGORIES.put("User Query", new String[]{"Slow", "Connect", "Charge", "Pay", "Net", "Bill"});
    }

    private static final Map<String, String> RESPONSES = new HashMap<>();

    static {
        RESPONSES.put("Billing", "You can ask about overcharges or missing payments.");
        RESPONSES.put("Overcharges", "Please provide your account number for investigation.");
        RESPONSES.put("Missing Payments", "Please check your payment history or contact support.");
        RESPONSES.put("Network Issues", "You can ask about slow connection or no connection.");
        RESPONSES.put("Slow Connection", "Restart your router and try again.");
        RESPONSES.put("No Connection", "Check your cables and modem, then contact support.");

        RESPONSES.put("User Query", DEFAULT_RESPONSE);
        RESPONSES.put("Bill", "You can ask about overcharges or missing payments.");
        RESPONSES.put("Charge", "Please provide your account number for investigation.");
        RESPONSES.put("Pay", "Please check your payment history or contact support.");
        RESPONSES.put("Net", "You can ask about slow connection or no connection.");
        RESPONSES.put("Slow", "Restart your router and try again.");
        RESPONSES.put("Connect", "Check your cables and modem, then contact support.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Orange Customer Service Chatbot! How can I assist you today?");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("exit")) {
                System.out.println("Chatbot: Thank you for using Orange Customer Service. Goodbye!");
                break;
            }

            String response = processInput(userInput);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }

    private static String processInput(String userInput) {
        for (String category : CATEGORIES.keySet()) {
            if (userInput.contains(category.toLowerCase())) {
                return RESPONSES.get(category);
            }
        }

        for (Map.Entry<String, String[]> entry : CATEGORIES.entrySet()) {
            for (String subcategory : entry.getValue()) {
                if (userInput.contains(subcategory.toLowerCase())) {
                    return RESPONSES.get(subcategory);
                }
            }
        }

        return DEFAULT_RESPONSE;
    }
}
