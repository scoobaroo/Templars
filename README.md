This is a class project for CS265 Information Security.
In this project, we are analyzing cipher text from mystery3c.com.
We chose the Knights Templars part 3 challenge and I chose to use Java as the programming language to
solve this challenge. Basically, the cipher was like this: the plaintext was broken into 7 character chunks, that is
it was like a block cipher, in which each block was a string 7 characters long. Then, according to some key,
which was essentially a random permutation of the numbers 1,2,3,4,5,6,7, the cipher text could be rearranged
in a block-wise manner to retrieve the plain text.
After careful analysis of the first 7 words, I figured out that Today was probably the first word. Given that today
was the first word, that only left 4 choices as the key. I tried all 4 key possibilities and got the right answer on the
last try.
