# Purchase Ice Cream Application

## To get started

Please install node version 8 or more on your system, clone this project and then run these commands to start the website.

* cd /{ROOT}/selenium-test-ui
* npm install
* npm start

This will start the application on port number 5000.

In the root, run Gradle command: ./gradlew clean build

Selenium and JUnit dependencies are provided in the gradle file in the selenium-regression-tests project.

## Requirements
**Please use selenium-regression-tests project for writing tests.**  



1. Verify the prices of each ice cream flavor.  
Starting from the top left:
   - Butter Pecan is $5.00
   - Chocolate is $6.00
   - Cookies and Cream is $5.50   
   - Pistachio is $5.00
   - Strawberry is $5.00
   - Vanilla is $5.00

Murali:--validate price using FindbyElements    

2. Verify that the user can only add ice cream to their cart when they pick a type of storage.  
Note: Sauces and Toppings are optional, while Storage is required.

Murali:--validate  AddtoCart Button is disabled Initially.Once select the Storage AddtoCart button is enabled. 


3. Verify that the cart is updated whenever the user adds an ice cream to their cart.  
Note: Add 3 flavors to the cart.

Murali:--validate the cart of each item added to the cart 

--
4. On the Checkout page, change the quantity of the ice cream and verify that the price is updated correctly.  
Note: Add at least 2 ice cream flavors with various toppings to the cart.

Murali--validate the price after updating the quantity in checkout page 


5. Purchase an order and verify that the information is correct on the confirmation page.  
Note: Pick a shipping address and fill out the notes text box.

Murali:--validate the order at confirmation page and display all the Items at console.


6. Run all the tests in parallel in the following browsers: Google Chrome, FireFox, Safari(if using Mac), and Microsoft Edge (if using Windows).  

Murali--I working on windows machine so parallel execution is complete for Chrome(Chrome driver),Firfox(gecko driver) and IE11(IE driver).