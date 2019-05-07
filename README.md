# charter-coding-challenge

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

·         Make up a data set to best demonstrate your solution
·         Check solution into GitHub


The project is deployed on the Heroku based service so the endpoints can be consumed as follows:

Loading the data: https://dry-coast-84938.herokuapp.com/getrewards/loaddata

To get reward points for all users: https://dry-coast-84938.herokuapp.com/getrewards/allrecords

To get individual records: https://dry-coast-84938.herokuapp.com/getrewards/getrecord/{userId}

Or you can run it locally using JAVA application.
