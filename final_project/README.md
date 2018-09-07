# Meeple4People

This is my final Project for PerScholas. The aim is to implement a web site where users can sign up and rent board games.
## Installation

Oracle 12.2 - download the m4pexport and install into your directory. Run this.
Java 1.8 - download the war, extract war (zip) modify WEB-INF\classes\db.propreties to align with your databases details (username and password)

## Technologies

### SQL & PL/SQL
I used Oracle SQL as the database for my application. My application, in its current state uses 17 tables:

	-	Games: a table dedicated to storing information about each game, including name and description
		-	id int: integer representing the id of the game
		-	Name varchar(50): The name of the game
		-	Description varchar(500): A brief description of the game
		-	Year_Published number(4): The year in which the game is published
		-	Cost_of_Game number: The cost of the game (is a double in Java)
		-	Average_Rating number: The average rating of all comments - calculated by a trigger
	-	Designers: a table dedicated to various game designers and their details
		-	id int: integer representing the id of the designer
		-	First_Name varchar(20): The designer's first name
		-	Last_Name varchar(25): The designer's middle name
		-	website varchar(40): The url to the designer's website
	-	Game_Designers: a table linking games to their respective designer(s)
		-	Game_ID int: integer representing the id of the game
		-	Designer_ID int: integer representing the id of the designer
	-	Mechanics: A table dedicated to various game mechanics
		-	id int: integer representing the id of the mechanic
		-	Name varchar(40): name of the mechanic
		-	Description varchar(280): A brief description of the mechanic
	-	Game_Mechanics: a table linking games to their respective mechanics
		-	Game_ID int: integer representing the id of the game
		-	Mechanic_ID int: integer representing the id of the mechanic
	-	Publishers:	a table dedicated to various game publishers
		-	id int: integer representing the id of the publisher
		-	Name varchar(45): name of the publisher
		-	Website varchar(40): The url to the publisher's website
	-	Game_Publishers: a table linking games to their respective publishers
		-	Game_ID int: integer representing the id of the game
		-	Publisher_ID int: integer representing the id of the publisher
	-	Game_Pictures: a table for a game's pictures
		-	id int: integer representing the id of the picture
		-	Game_ID int: integer representing the id of the game
		-	Picture_Size int: size of the picture, (1-3), practically only 2 is used
		-	URI varchar(40): The URI linking to the picture's location on disk (just the suffix, e.g. viticulture-medium.jpg) currently only jps supported
	-	Zipcodes: zipcodes stored as a table
		-	Zipcode number(6): six digit representation of the zipcode
		-	City varchar(20): the city in which the zipcode is located
		-	State varchar(25): The state in which the zipcode is located
		-	Country varchar(25): The country in which the zipcode is located (practically only USA)
	-	Customers: a table dedicated to storing customer information
		-	id int: integer representing the id of the customer
		-	Last_Name varchar(25): customer's last name
		-	First_Name varchar(20): customer's first name
		-	Username varchar(20) unique not null: customer's username
		-	E_mail varchar(20) unique not null: customer's email
		-	Password varchar(64) not null: customer's password, hashed 256-bit
		-	Salt varchar(32) not null: Cryptographic salt for the password, 16 bytes
		-	Address_Line_1 varchar(20): customer's address - line 1 (e.g. 10 personette dr.)
		-	Address_Line_2 varchar(20): customer's address - line 2 (e.g. apt 2b)
		- 	Zipcode number(6): six digit representation of the zipcode
		-	Phone number(10): customer's 10-digit phone #
		-	Member_Status varchar(15): customer's status, Active or Suspended
		-	Join_Date Date: date the customer joined the site
		-	Balance number: amount of money owed (late fees and other charges)
	-	Game_Comments: a table for customer comments and ratings on games
		-	Game_ID int: integer representing the id of the game
		-	Customer_ID int: integer representing the id of the customer
		-	Comment_Text varchar(280): customer's (brief) comment (if they can fit it in a twitter comment, they can fit it here)
		-	Rating number(5,2): customer's numerical rating of the game
		-	Comment_Date Date: date the comment was made
	-	Statuses: a small table for storing possible item statuses (e.g. In Stock, Rented)
		-	Status_ID int: integer representing the id of the status
		-	Name varchar(15): name of the status
	-	Conditions: a small table for storing possible item conditions (e.g. New, Good, Poor)
		-	Condition_ID int: integer representing the id of the condition
		-	Name varchar(15): name of the status
	-	Stock:	a table for storing all the individual items that can be rented
		-	Item_ID int: integer representing the id of the item
		-	Game_ID int: integer representing the id of the game
		-	Status_ID int: integer representing the id of the status
		-	Serial_Number char(10) unique not null: serial number of the item
		-	Acquisition_Date Date: date the item was acquired
		-	Condition_ID int: integer representing the id of the condition
		-	Last_Examined Date: date the item was last examined
	-	Shopping_Cart: a table to store customer's shopping carts
		-	id int: integer representing the row of the shopping cart
		-	Customer_ID int: integer representing the id of the customer
		-	Item_ID int unique: integer representing the id of the stock item
	-	Rentals: a table to store what games (stock items) customers have rented
		-	id NUMERIC: integer representing the row of the rental
		-	Customer_ID int: integer representing the id of the customer
		-	Item_ID int unique: integer representing the id of the stock item
		-	Date_Rented date: date that the rental was rented
		-	Due_Date date: date the rental is due back (default is 5 weeks from date_rented)
	-	Orders: a table to store the order itself
		-	Order_ID int: integer representing the id of the order
		-	Item_ID int: integer representing the id of the stock item
		-	Customer_ID int: integer representing the id of the customer
		-	Date_Shipped Date: date the order was shipped out, defaults to null (not used practically atm)
		
In addition to tables, I implemented a few stored functions, procedures, and triggers:

	-	calc_average_rating (trigger): calculates a games average rating each time a new comment (with rating) is added or updated
	-	weeksSince (function): This function is used to calculate the number of weeks between two dates
	-	checkRentalWeeks (procedure): this procedure checks the number of weeks between the current date and the rental's due date. It then applies a balance - if applicable - to the customer based on the number of weeks (1$ / week, but the full cost of the game if it's past the 10 week mark);
	-	suspendCustomers (procedure): This function is used to check the number of overdue games, and if it's more than 5 to suspend the customer - preventing them from renting more games until they've paid their balance (paying balance is currently not implemented)
	
	both checkRentalWeeks and suspendCustomers are scheduled to run on a regular basis
	
Finally, I have a couple of views implemented, consolidating some complex queries:

	-	getMechanics: this view provides the mechanics joined on the game_mechanics table, giving a succinct way to reference which games (ids) have which mechanics
	-	topRated: this view provides a list of all games sorted by rating (descending), with a custom column, allowing for easy selection of a sub-set of recommended games

### Java

All of the code itself for this project was written in Java using an assortment of classes. For the sake of brevity, I won't go through every class I wrote up, but I do want to highlight the types of classes I used:

	-	Models: I used a number of models (beans), mostly POJO, to store and pass data throughout the program. For the most part, they map onto tables from my database, but not all tables made it in.
	-	DAOs:	I used a number of classes to access my Oracle Database, using JDBC, to populate the appropriate Models and return data.
	-	Encryption: I created a package with a class dedicated to the functions I needed for encrypting and hashing passwords
	-	Exceptions: I created a few custom exceptions
	-	Message (Ajax): I created a message class to handle pass Ajax data
	-	Controllers: The brunt of url mapping, handling get and post http requests, along with calling the relevant services were handled by a variety of controllers
	-	Services: I created a number of classes to provide a service layer between the DAOs and the Controllers calling them. In a few cases, this helped to consolidate a number of DAO calls into a single transaction.
	-	SpringMain: the main class for Spring boot to run the application

### JDBC

As mentioned, I used JDBC to connect my Java code to the Oracle database, allowing me to make necessary queries.

### JSP

All of my views for my web site were written in JSP (Java Servlet Page). This enabled me to have various dynamic pages. The following is a quick run-down of all the pages I created:

	-	about.jsp: a brief filler page to tell the customer what the site is about: its mission and overview
	-	checkoutBilling.jsp: this page prompts the user for their billing information during the checkout process
	-	checkoutOrderPage.jsp: this page displays a review of the user's order after checkout, including a link to the order page
	-	checkoutReview.jsp:  this page presents a review of the user's checkout details before final submission and order
	-	checkoutShipping.jsp: this page prompts the user for their shipping information during the chekcout process
	-	error.jsp: this page displays errors caught by exceptions
	-	faq.jsp: this is a brief filler page, answering common customer questions and detailing business policies
	-	footer.jsp: (TODO) this is a template for the footer of all pages, including copyright and attributions
	-	gameDetail.jsp: this page displays the information for a particular game, generated based on the game id
	-	header.jsp:	this is a template for the header of all pages, including the navigation bar, the search bar, the user login/logout icon
	-	index.jsp: this is the home page, displaying a set of six recommended games at a time
	-	login.jsp: this page handles user login
	-	mechanicDetail.jsp: this page displays information about a particular mechanic, generated based on id
	-	orderDetail.jsp: this page displays all the games in a particular order, generated based on id
	-	rentals.jsp: this page displays a customer's currently rented games and allows them to return the games
	-	searchResultPage.jsp: this is the dynamic result page from a given user search.
	-	signUp.jsp: this page allows customers to register for the site
	-	userProfile.jsp: this page displays a user's profile data and allows for users to modify the data
	-	welcome.jsp: this page isn't used - it's primarily a testing ground for some ideas
	
### Spring MVC / Spring Boot

All of the technology to handle url mapping, display resolution, and response data is using Spring MVC. DAOs are marked as @Repository, Services as @Service, Controllers as @Component with @GetMapping and @PostMapping. @RequestParam is used a lot. 

### JUnit

The DAOs are all tested via a suite of JUnit 5 classes. These provide full functionality tests to ensure all the appropriate data is being stored, retrieved, and updated where necessary.

### JavaScript

JavaScript (using some JQuery) is used to validate the input of most forms. In addition, I use JavaScript (jQuery's ajax) to query for zipcode changes on a few pages. Other uses of JavaScript include auto population/clearing of data on shipping and billing pages during checkout.

## Use Cases
**Login/Registration:**
As a user I should be able to sign up for an account so that my payment details and shopping cart will be remembered.

As a user I should be able to have an 8 to 20 character username that may include numbers and symbols so that I can have a standard and unique username.

As a user I should be able to login with an 8 to 20 character password that includes at least one number and one symbol so that I can have a secure password.

As a user I should be able to register my email so that I can get notifications.

**Home Page:**
As a user I should be able to see a list of games with their picture, name, and average rating so that I can choose my next board game to rent.

**Search:**
As a user I should be able to search the games by name so that I can find exactly the game I’m looking for.

As a user I should be able to search and filter the games by designer

As a user I should be able to search and filter the games by publisher

As a user I should be able to search and order the games by year published so that I can view older or newer games

As a user I should be able to search and filter the games by game mechanics so that I can find games with a similar feel

As a user I should be able to search and order the games by average rating so that I can find popular games

**Shopping Cart:**
As a user I should be able to add board games to a shopping cart so that I can rent multiple games at the same time.

As a user I should be able to view the list of board games in my shopping cart so that I can review my potential rentals

As a user I should be able to remove board games from the shopping cart so that I don’t have to rent them.

As a user I should be able to reorder the items in my shopping cart so that I can prioritize the ones that I want to rent.

As a user I should see the total cost that the items in my shopping cart will be to rent so that I can make a more informed spending decision.

**Check out**
As a user I should be able to securely enter in my credit card (16 digits with a 3 digit CVv and expiration date) so that I can pay for my rental items.

As a user I should be able to securely enter in my shipping address so that I can receive my rental items.

As a user I should be able to securely enter in my billing address so that I can be billed appropriately.

As a user I should be able to review the order before confirming so that I have a final chance to make changes.

As a user I should be able to place an order of the games in my shopping cart so that I can receive them.

**Orders**
As a user I should be able to track an order is so that I can anticipate its arrival

As a user I should be able to see a list of the games I’m currently renting and when they’re due so that I know what and when to return them.

**Returns**
As a user I should be able to print out a shipping label so that I can easily and inexpensively return the game.

As a user I should be able to leave a rating and review after returning a board game.

As a user I should be able to request a refund if a game is missing pieces so that I can recoup the cost of my missed gaming experience.

**Game Page**

As a user I should be able to view a game’s name, picture, description, year of publication, list of mechanics, designer(s), ratings, and reviews so that I can make an informed rental decision.

**Business Rules**

As a store, users should be able to rent up to a maximum of five games at a time so that games are always in stock.

As a business, users should be able to rent games up to a maximum of 5 weeks so that games can be managed

As a business I want to charge users 1$ per week for every week that customers are late returning their game up to a maximum of 10$ at which point they will be charged the rest of the cost of the game.

As a business users who have not returned their last five games should not be able to rent more games so that games will not be lost, wasted or need replacement.

As a site no two users can have the same email so that I can prevent duplicate users. 
