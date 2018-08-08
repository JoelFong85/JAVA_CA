CAB - CLUB APPLICATION FOR BOOKING
BY TEAM 3


================== SETUP ==================
0. Import the project into workspace 
1. Execute the SQL Scripts in MySQL first
2. Update the Maven Libraries
3. Compile and Run on Pivotal Server
4. Launch on browser and test the application

The program starts by default in this URL:
http://localhost:8080/cab/


================== SYSTEM RUN THROUGH ==================


=== LOGIN - TEST ACCOUNTS ===
0. For Member Role:
Use user name 'mad' password 'mad'
Use user name 'alice' password 'alice'

1. For Admin Role:
Use user name 'tink' password 'tink'

<<Related files>>
UserSession.java
CommonController.java
login.jsp


== LOGIN - MEMBER REGISTRATION === 
0. Validators for phone & password - must be a minimum length and right characters
1. Validators for filling in all fields
2. Upon sign up, auto login to Member Page. 
3. Wrong credentials - display message.

<<Related files>>
MemberController.java
UserValidator.java
member-register.jsp


=== MEMBER - FACILITY BOOKING ===
0. Default member landing page - Facility Booking (Only facilities of status 0 in database will be displayed)
- Select facility to make booking
- List of unavailable dates will be displayed
- Only 'Date for Booking' field can be selected. Click on text box to open Datepicker. Only dates from today forward can be selected. Other dates have been disabled.
- Once booking is made, will redirect to Facility Booking page. Success message will be displayed. 
- If the Facility has been booked on the selected date, will redirect to Facility Booking page. noSuccess message will be displayed.

1. Search function at top of page to search facilities by name

<<Related pages>>
MemberController.java
facility-book.jsp
book-make.jsp


=== MEMBER - CURRENT BOOKINGS ===
0. See future bookings - all bookings current date onwards. Full list displayed

1. Option to delete booking

2. Option to edit booking
- Leads to another page.
- Only date field is editable. If selected date for booking update is not free, upon confirmation, will throw error message.
- Member can't select dates before current date.  

<<Related pages>>
MemberController.java
member-booking-current.jsp
member-booking-edit.jsp


=== MEMBER - BOOKING HISTORY ===
0. See past bookings - all bookings before current date. Full list displayed

1. Search between certain dates possible by using datepicker. 

2. Validators for empty date fields, or if start date later than end date. 

<<Related pages>>
MemberController.java
member-booking-history.jsp


=== MEMBER - EDIT PROFILE ===
0. Allows member to edit their personal info

1. Validators that ensure password & phone number must be a minimum length and right characters.  

<<Related pages>>
MemberController.java
member-profile.jsp
member-profile-edit.jsp


=== ADMIN - FACILITY BOOKING ===
0. Default admin landing page - Facility Booking
- Select facility to make booking
- List of unavailable dates will be displayed
- Only 'Date for Booking' field can be selected. Click on text box to open Datepicker. Only dates from today forward can be selected. Other dates have been disabled.
- Once booking is made, will redirect to Facility Booking page. Success message will be displayed. 
- If the Facility has been booked on the selected date, will redirect to Facility Booking page. noSuccess message will be displayed.

1. Search function at top of page to search facilities by name

<<Related pages>>
AdminBookingController.java
facility-book.jsp
book-make.jsp


=== ADMIN - CURRENT MAINTENANCE (BOOKINGS MADE BY ADMIN) ===
0. See future bookings - all bookings current date onwards. Full list displayed

1. Option to delete booking

2. Option to edit booking
- Leads to another page.
- Only date field is editable. If selected date for booking update is not free, upon confirmation, will throw error message.
- Member can't select date before current date.  

<<Related pages>>
AdminBookingController.java
maintenance-current.jsp
maintenance-edit.jsp


=== ADMIN - MAINTENANCE HISTORY ===
0. See past bookings - all bookings before current date. Full list displayed

1. Search between certain dates possible by using datepicker. 

2. Validators for empty date fields, or if start date later than end date. 

<<Related pages>>
AdminBookingController.java
maintenance-history.jsp


=== ADMIN - MANAGE ADMIN USERS ===
0. Can add new admin. Validators 
- Check if username exists
- Password must be between 6 and 15 char
- Phone must contain only numbers
- Email must follow email expression
- All fields can't be empty

1. Can edit other admin
- Can edit self
- Validator checks new username doesn't exist in database, plus above validators. 

2. Admin users can delete other admin users
- Admin users can't delete themselves

<<Related pages>>
AdminUserController.java
admin-list.jsp
admin-new.jsp
admin-edit.jsp


=== ADMIN - MANAGE MEMBER USERS ===
0. Can add new member users. Validators

1. Can edit members
- Validator checks new username doesn't exist in database, plus above validators. 

2. Admin users can delete members

<<Related pages>>
AdminMemberController.java
member-list.jsp
member-edit.jsp
member-new.jsp


=== ADMIN - MANAGE FACILITIES ===
0. Admin can create new facilities

1. Edit & delete facilities.

<<Related pages>>
AdminFacilityController.java
facility-edit.jsp
facility-list.jsp
facility-new.jsp


=== ADMIN - EDIT PROFILE ===
0. Allows admin to edit their personal info

1. Validators that ensure password & phone number must be a minimum length and right characters.  

<<Related pages>>
AdminUserController.java
admin-profile.jsp
admin-profile-edit.jsp

===Testing===
Used Selenium IDE testing to check the project
Log files was attached in another file
