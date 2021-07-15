import datetime
from re import split as re_split


def top_100(f_dict, uni_list, uni_dict):
    for count in range(1, 101):
        word, probability = find_max(f_dict, uni_list, uni_dict)
        f_line = "{0} {1} -> {2:.10f}\n".format(count, word, probability * 100)
        f.write(f_line)
        f_dict[word] = 0


def find_max(f_dict, uni_list, uni_dict):
    max_key_value = 0
    max_key = ""

    for key in f_dict:
        probability = probability_mm(key, uni_list, uni_dict)
        if f_dict[key] != 0 and probability > max_key_value:
            max_key_value = probability
            max_key = key

    return max_key, max_key_value


def probability_mm(word, uni_list, uni_dict):
    f_tokens = word.split()
    probability = 0
    size = len(uni_list)
    if len(f_tokens) == 1:
        probability = uni_dict[f_tokens[0]] / size
    elif len(f_tokens) == 2:
        probability = (uni_dict[f_tokens[0]] / size) * (uni_dict[f_tokens[1]] / size)
    else:
        probability = (uni_dict[f_tokens[0]] / size) * (uni_dict[f_tokens[1]] / size) * (uni_dict[f_tokens[2]] / size)

    return probability


Unigrams = list()

UnigramDict = dict()
BigramDict = dict()
TrigramDict = dict()

file_names = ("Unigram.txt", "Bigram.txt", "Trigram.txt")
try:
    for i in range(1, 6):
        file_name = str(i) + ".txt"
        f = open(file_name, "r")
    print("Started")
except FileNotFoundError:
    print("Error")
    exit(0)

total_a = datetime.datetime.now()
a = datetime.datetime.now()

for i in range(1, 6):
    file_name = str(i) + ".txt"
    with open(file_name) as file:
        line = file.read()
        splitted = re_split('\\s|\\.|,|\'|"|!|\\?|-|\\(|\\)|:|;|\\*|/|«', line)
        tokens = list()
        for w in splitted:
            if w != '':
                tokens.append(w)
        Unigrams = Unigrams + tokens

        for token in tokens:
            if token in UnigramDict:
                UnigramDict[token] = UnigramDict[token] + 1
            else:
                UnigramDict[token] = 1

b = datetime.datetime.now()
c = b - a
f = open("N-Grams.txt", "w")

Line = "File read and Unigram generation time is : {0:.0f} ms.\n".format(((c.seconds * 1000) + (c.microseconds / 100)))
f.write(Line)

a = datetime.datetime.now()
for i in range(len(Unigrams)):

    if i + 1 < len(Unigrams):
        Bigram = Unigrams[i] + " " + Unigrams[i + 1]
        if Bigram in BigramDict:
            BigramDict[Bigram] = BigramDict[Bigram] + 1
        else:
            BigramDict[Bigram] = 1

    if i + 2 < len(Unigrams):
        Trigram = Unigrams[i] + " " + Unigrams[i + 1] + " " + Unigrams[i + 2]
        if Trigram in TrigramDict:
            TrigramDict[Trigram] = TrigramDict[Trigram] + 1
        else:
            TrigramDict[Trigram] = 1

b = datetime.datetime.now()
c = b - a

Line = "Bigram and Trigram generation time is : {0:.0f} ms.\n\n# Word   %\n\n -Unigrams-\n\n".format(
    ((c.seconds * 1000) +
     (c.microseconds / 100)))
f.write(Line)

a = datetime.datetime.now()
top_100(UnigramDict, Unigrams, UnigramDict)
b = datetime.datetime.now()
c = b - a
Line = "--> Top 100 Unigram ordering time is : {0:.0f} ms.\n\n -Bigrams-\n\n".format(
    ((c.seconds * 1000) + (c.microseconds / 100)))
f.write(Line)

a = datetime.datetime.now()
top_100(BigramDict, Unigrams, UnigramDict)
b = datetime.datetime.now()
c = b - a
Line = "--> Top 100 Bigram ordering time is : {0:.0f} ms.\n\n -Trigrams-\n\n".format(
    ((c.seconds * 1000) + (c.microseconds / 100)))
f.write(Line)

a = datetime.datetime.now()
top_100(TrigramDict, Unigrams, UnigramDict)
b = datetime.datetime.now()
c = b - a
Line = "--> Top 100 Trigram ordering time is : {0:.0f} ms.\n\n".format(((c.seconds * 1000) + (c.microseconds / 100)))
f.write(Line)

total_b = datetime.datetime.now()
total_c = total_b - total_a
Line = "Word Count : {0}\nUnigram Count : {1}\nBigram Count : {2}\nTrigram Count : {3}\nTotal time is : {4:.0f} ms.". \
    format(len(Unigrams), len(UnigramDict), len(BigramDict), len(TrigramDict), ((total_c.seconds * 1000) +
                                                                                (total_c.microseconds / 100)))
f.write(Line)
f.close()
print("Finished")
