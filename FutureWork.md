# Future Work

Here we outline areas of future work for the project.

## Algorithm Comparison and Big O
You have successfully developed one algorithm to generate the dice. In the file ![dice.ipynb](./dice.ipynb) I have an second algorithm for comparsion. The general idea of the procedure is as follows:

Given *n* as the total number of sides on the dice and *k >= n* as the total number of pips we 
1) form the starting dice of the form [k-n+1, 1, 1, 1, ..., 1]
2) create a list of all dice beginning with only the previous start dice ```all_dice = [start_dice]```
3) create a queue of all dice to process with only the start dice ```process_dice = [start_dice]```
5) while the process queue is not empty
   a) deque the next dice to process
   b) Find two consecutive sides of the dice such that the first side has 2 or more pips than the next side
   b) Move one of the pips from the first side to the next side
   c) check if the new dice has already been found; if not, add it to the list of all dice AND add it to the process queue
   

The code is as follows
```python
def findAllLoop(n,k):
    start_dice = [k-n+1]   # start dice is [k-n+1,1,1, ..., 1]
    for i in range(n-1):
        start_dice.append(1)
    
    process_dice=[start_dice] # queue for all the dice to process
    all_dice = [start_dice]   # return list for all found dice
    while len(process_dice)>0:
        d = process_dice.pop(0) # grab the next dice to process
        for i in range(n-1):
            if d[i] >= d[i+1] + 2:  # check for sides with difference of 2
                new_dice = d.copy() # make a new dice and move one of the pips
                new_dice[i] = new_dice[i] - 1
                new_dice[i+1] = new_dice[i+1] + 1
                if not( new_dice in all_dice): 
                    all_dice.append(new_dice)
                    process_dice.append(new_dice)
    return all_dice
```

### Questions to Explore
1) Is this algorithm correct? Compare the output to your algorithm and your hand-worked-out example.
2) What is the big O for your algorithm and for this algorithm? The answer will probably be in terms of both *n* and *k*

## Transitivity
We have defined a relationship between the dice. We say that one dice **beats** or **is better than** another dice when a dice beats another dice *on average*. That is, assuming each side of each dice has a uniform probability of being rolled, then the probability that the first dice wins is higher.

### Questions to Explore
1) Is this property transitive? That is if Dice 1 **beats** Dice 2 and Dice 2 **beats** Dice 3, then does **Dice 1** beat **Dice 3**?
2) Are there situations where transitivity olds and where it doesn't (i.e. for a certain number of sides or pips)?

## Number of Dice 
Another interesting question is the number of dice that can be created given a value of *n* and *k*.
1) Can we develop a closed-form formula using *n* and *k*?
2) Or, can we develop a reference equation describing the relationship?
3) Or, can we do either of the above when only one variable is considered and the other is fixed?

For example, let $C(n,k)$ represent the function for the number of dice that can be created with the given $n$ and $k$. We know a few things right off:
1) C(n,k) = 0 if k < n
2) C(n,n) = 1 
3) C(n,n+1) = 1
4) C(n,n+2) = 2
5) C(n,n+3) = 2

Does this pattern continue? Can we prove it? Can we say C(n,k) <= k-n? 
