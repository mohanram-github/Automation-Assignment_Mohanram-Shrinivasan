Feature: Handling Windows in Flipkart

Scenario: Adding and Removing items from cart
 Given User opens the edge browser
 When User enters URL
 When User enters email and password
 And User clicks login
 Then User searches for HP laptop and adds anyone item to the cart
 And Again searches for any mobile and adds anyone item to the cart
 Then User removes the old item from the cart
 Then User validates if the last item is present
 And User navigate to the parent window and logout
 And User quits the chrome browser
	