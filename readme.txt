Quoc Thai Nguyen Truong cs61bl-on
Carlos Flores cs61bl-oj
Stephen Sweeney cs61bl-ok


Test explanations:

DOT TEST
test Constructor:
1) to make sure "my color" is between 1 and 5 inclusive
2) to make sure if it takes an argument as integer, my color is equals to that integer

test getColor:
1) to make sure it's return the right color as integer that we assign 

test isSameColor:
1) create 2 dots with same color as an integer and make sure isSameColor return true
2) create 2 dots with different color as integer and make sure isSameColor return false

test isColor:
1) create a dot with an integer 2, and call isColor that take integer 2, call isColor to make sure it return true;
2) create a dot with an integer 2, and call isColor that take integer 1, call isColor to make sure it return false;


BOARD TEST
test CanMakeMove: 
1)to test if the program knows if it can't make a move
2)tests that you can make a move in the UPPER LEFT
3)tests that you can make a move in the UPPER RIGHT
4)tests that you can make a move in the BOTTOM RIGHT
5)tests that you can make a move in the BOTTOM LEFT
6)tests that you can make a move on the LEFT SIDE
7)testes that you can make a move on the BOTTOM
8)tests that you can make a move on the TOP
9)tests that you can make a move on the RIGHT SIDE
10)tests that you can make a move in the MIDDLE

test constructor:
1)test to make sure if the size is between 4 and 10 inclusive
2) test to see if too small
3) test to see if too big
4) make sure that whole board can become one color corresponding to it's int value


test selection:
1)make sure that you can select, deselect, and that the size corresponds to the ones selected

test closedShape:
1)make sure that the program can tell when a closed shape is formed


test RemoveDropFill:
1) make sure all the selection dots are legal, and remove, drop fill method are working good with the score is also updated.
2) make sure it also remove the edge case for more than 1 dot at the corner are selected, the score is also updated.

test bestsquare:
1) tests to ensure that the BestSquare with the most points available will be chosen.

test getScore:
1) to make sure the score is updated whenever the remove button is successfully called.


Summary of contributions to the project:
We used the methods of paired programming throughout the entire process of the project. We switched off who was at the keyboard while the other two would double check the logic and code written by the individual at the keyboard. We all contributed to the coding and development to the first two parts. Thai was able to figure out the logic on part three, and was pivotal in coding that portion. Carlos and Stephen wrote the test files for the project. At the projects completion, we edited our code to make it as effecient, again all parties participated in this.