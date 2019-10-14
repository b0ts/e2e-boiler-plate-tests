# Import the modules
import sys
import random
    
argument = sys.argv[1:]

if argument == ['forceFailure']:
		sys.exit("Failure forced through argument in mball")   

if argument == ['forceYes']:
		answers = 1
else:
   answers = random.randint(1,8) 

if answers == 1:
    print "Move 3 spaces forward"
    
elif answers == 2:
    print "Move 3 spaces back"
    
elif answers == 3:
    print "Go directly to Gail, do not pass Go"
    
elif answers == 4:
    print "Move 8 spaces forward"
    
elif answers == 5:
    print "Move 8 spaces back"
    
elif answers == 6:
    print "Loose turn - stay in same place"
    
elif answers == 7:
    print "Pick up a card"
    
elif answers == 8:
    print "Give one of your cards to another player"
