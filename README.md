# Petals Haven - Belu Mara Luciana 406
"Petals Haven" is an online floral retail platform that specializes in providing a diverse selection of fresh and artfully arranged flowers to customers worldwide. As an e-commerce platform, offers a seamless and user-friendly interface, allowing customers to browse through an extensive assortment of floral arrangements, bouquets, and individual stems from the comfort of their homes.

## Business Requirements:
1. Allow users to create accounts, log in, and manage their profiles.  
2. Display a variety of flowers with details such as names, descriptions, prices, and available quantities.  
3. Enable users to add flowers to a cart, review their selections, and proceed to checkout.  
4. Provide users the ability to view their order history and status.
5. Implement a search bar and filters for users to easily find specific flowers based on categories like type, color, occasion, etc.
6. Allow customers to leave reviews and ratings for products they've purchased.
7. Ensure the platform is mobile-friendly for easy access and use on different devices.
8. Provide an admin interface to manage products, view orders, and handle user inquiries.
9. Send confirmation emails for successful orders and updates on order status.
10. Collect data on sales and popular products for future business analysis.

## MVP Features:
1. Allow users to register, log in, and manage their profiles.
2. Show a limited catalog of flowers with basic details.
3. Enable users to add items to a cart and proceed to a simple checkout process.
4. Users can view their past orders and their current status.
5. Implement a basic search bar to find flowers based on names or categories.

## Entities:
### Cart
id (Primary Key) - UUID  
user_id (Foreign Key) - UUID  
orders - List of Order entities  
cartItems - List of CartItem entities  
totalPrice - int  
isCartOpen - boolean  

### Category
id (Primary Key) - UUID  
name - String  
description - String  
flowers - List of Flower entities  

### CartItem
id (Primary Key) - UUID   
cart_id (Foreign Key) - UUID   
flower_id (Foreign Key) - UUID  
quantity - int   

### Flower
id (Primary Key) - UUID   
name - String   
description - String  
price - int   
imageUrl - String   
available - boolean    
category_id (Foreign Key) - UUID   
cartItems - List of CartItem entities    

### Order
id (Primary Key) - UUID    
user_id (Foreign Key) - UUID   
cart_id (Foreign Key) - UUID   
orderDate - Date   
totalAmount - double    
orderStatus - Enums.OrderStatus    


### User
id (Primary Key) - UUID   
email (Unique) - String  
password - String   
first_name - String   
last_name - String   
phone_number - String   
carts - List of Cart entities   
orders - List of Order entities    

## Endpoints (http://localhost:8080)

### Path: /users/register  
Method: POST  
Body example :    
{    
  "email": "testtt@example.com",   
  "password": "parola",   
  "firstName": "Mara",   
  "lastName": "Belu",   
  "phoneNumber": "+1234567890"   
}   
Response:    
200 OK: Returns the registered user if successful.   
400 Bad Request: Indicates a failure in the registration process. Check input validity.   
   
### Path: /users/login   
Method: POST  
Body example :   
{  
  "email": "test@example.com",   
  "password": "parola"  
}   
Response:   
200 OK: Returns the authenticated user if login is successful.   
401 Unauthorized: Indicates a failed login attempt due to invalid email or password.  
   
### Path: /users   
Method: GET    
Response:   
200 OK: Returns a list of users if available.    
204 No Content: Indicates no users are currently registered.   
     
### Path: /users/{userId}    
Method: DELETE    
Response:    
204 No Content: Indicates successful deletion of the specified user.   

### Path: /orders/placeOrder    
Method: POST    
Body example:     
{    
  "userId": "ddb5b94e-b3ae-40a6-a2a7-05954b9f6711",    
  "cartId": "6ce99494-061c-4a48-9809-b9fc4459a6aa"   
}   
Response:      
200 OK: Returns "Order placed successfully."     
400 Bad Request: Indicates failure to place an order. Check if the cart is not open or not found.       

### Path: /orders/user/{userId}    
Method: GET   
Response:     
200 OK: Returns a list of orders associated with the specified user.   
204 No Content: Indicates that the user has no orders.    

### Path: /orders/{orderId}     
Method: DELETE   
Response:   
204 No Content: Indicates successful deletion of the specified order.       

### Path: /flowers   
Method: POST  
Body example:    
{    
  "name": "Rose",   
  "description": "Beautiful red rose",   
  "price": 4,    
  "imageUrl": "https://example.com/rose.jpg",   
  "available": true,    
  "categoryId": "6ce99494-061c-4a48-9809-b9fc4459a6aa"     
}    
Response:     
201 Created: Returns the added flower if successful.    
400 Bad Request: Indicates failure to add a flower. Check input validity.    

### Path: /flowers    
Method: GET    
Response:      
200 OK: Returns a list of all flowers if available.   
204 No Content: Indicates that there are no flowers available.     

### Path: /flowers/{flowerId}/availability         
Method: PUT     
Response:        
200 OK: Returns the updated flower with changed availability if successful.       
404 Not Found: Indicates that the specified flower ID was not found.     

### Path: /categories    
Method: POST   
Body example:     
{    
  "name": "Exotic",      
  "description": "Beautiful flowers in various colors"         
}       
Response:     
201 Created: Returns the added category if successful.     
400 Bad Request: Indicates failure to add a category. Check input validity.    

### Path: /categories      
Method: GET    
Response:    
200 OK: Returns a list of all categories if available.     
204 No Content: Indicates that there are no categories available.     

### Path: /categories/{categoryId}/flowers       
Method: GET       
Response:    
200 OK: Returns a list of flowers in the specified category if available.   
404 Not Found: Indicates that the specified category ID was not found.     
