# N-Gram-of-Markov-Models-on-Turkish-Novel-Dataset
 1,2 and 3-grams of Markov Model algorithm implementation in Java and Python
 # Requirements
The files must be named as 1.txt 2.txt 3.txt 4.txt and 5.txt and must be in the same directory with .exe files.

Valid txt files are in the Input_Files directory.

driver.ee is Java

main.ee is Python

# Java Results
Here is the first 5 N-Grams of the novels. I added the full file into repo.

-Unigrams-

1 bir -> 3,2071645260

2 ve -> 1,6333054304

3 de -> 0,8975383639

4 bu -> 0,8234744072

5 da -> 0,8195762634

-Bigrams-

1 uzun uzun -> 0,0000718829

2 değildir uzun -> 0,0000718829

3 güzel güzel -> 0,0000702399

4 hemen üzerine -> 0,0000693661

5 hemen ilk -> 0,0000677896

-Trigrams-

1 Sonra ağır ağır -> 0,0000000328

2 değildir Fakat tam -> 0,0000000309

3 belki kızkardeşi küçük -> 0,0000000298

4 hem kendisi hem -> 0,0000000294

5 yoktur hemen beni -> 0,0000000290


RUNTIME RESULTS

File read and Unigram generation time is : 144 ms.

Bigram and Trigram generation time is : 77 ms.

Word Count : 102614

Unigram Count : 31267

Bigram Count : 88813

Trigram Count : 101001

Total time is : 11502 ms.
# Python Results
-Unigrams-

1 bir -> 3.2071959693

2 ve -> 1.6333213141

3 de -> 0.8975470944

4 bu -> 0.8244569402

5 da -> 0.8195842632

-Bigrams-

1 yerde değildir -> 0.0000727105

2 yerde hem -> 0.0000727105

3 uzun uzun -> 0.0000718842

4 değildir uzun -> 0.0000718842

5 güzel güzel -> 0.0000702412

-Trigrams-

1 yerde kendine iş -> 0.0000000403

2 Sonra ağır ağır -> 0.0000000328

3 değildir Fakat tam -> 0.0000000309

4 belki kızkardeşi küçük -> 0.0000000298

5 hem kendisi hem -> 0.0000000294

File read and Unigram generation time is : 1023 ms.

Bigram and Trigram generation time is : 1814 ms.

Word Count : 102613

Unigram Count : 31197

Bigram Count : 88786

Trigram Count : 100993

Total time is : 28062 ms.

# COMPARISON JAVA vs PYTHON
As expected, Java is faster than Python.
(Java/Python)

File read and Unigram generation time is : 144 ms./ 1023 ms.

Bigram and Trigram generation time is : 77 ms./ 1814 ms.

Word Count : 102614/102613

Unigram Count : 31267/31197

Bigram Count : 88813/88786

Trigram Count : 101001/100993

Top 100 Unigram ordering time is : 920 ms./ 7032 ms.

Top 100 Bigram ordering time is : 4202 ms./ 10691 ms.

Top 100 Trigram ordering time is : 6159 ms./ 16492 ms.

Total time is : 11502 ms./ 28062 ms.


