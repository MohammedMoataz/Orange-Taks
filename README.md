# Orange Task Repository

This repository contains three distinct pieces of code: a simple Java program (simple chatbot) and two SQL files. Below is a brief overview of each component.

## Contents

1. **Java File**
2. **SQL Files**

## 1. Java File

### `main.java`

```java
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
```

## 2. SQL Files

### `first query.sql`

```sql
SELECT *
FROM CALL_RECORDS
WHERE SYSDATE - CALL_DATE <= 30
ORDER BY CALL_DATE DESC;
```

### `second query.sql`

```sql
SELECT 
    b.BASE_STATION_ID,
    b.LOCATION,
    r.REGION_NAME,
    st.STATION_TYPE,
    AVG(c.DURATION) AS AVG_DURATION,
    CASE
        WHEN AVG(c.DURATION) >= 120 THEN 'Excellent'
        WHEN AVG(c.DURATION) BETWEEN 60 AND 119 THEN 'Good'
        WHEN AVG(c.DURATION) BETWEEN 30 AND 59 THEN 'Underperforming'
        WHEN AVG(c.DURATION) < 30 THEN 'Critical'
    END AS PERFORMANCE_CATEGORY
FROM
    base_stations b
        JOIN
    regions r ON b.REGION_ID = r.REGION_ID
        JOIN
    base_stations_types st ON b.BASE_STATION_ID = st.BASE_STATION_ID
        JOIN
    call_records c ON b.BASE_STATION_ID = c.BASE_STATION_ID
GROUP BY 
    b.BASE_STATION_ID, b.LOCATION, r.REGION_NAME, st.STATION_TYPE;
```

#### Thank you for visiting this repository! Happy coding! 🚀
