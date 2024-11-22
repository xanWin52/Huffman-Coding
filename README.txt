What kinds of file lead to lots of compressions. What kind of files had little or no compression?
What happens when you try and compress a huffman code file?

Txt and HTML files tend to achieve a lot of compression as shown in the Calgary and BooksAndHTML
folders which achieved 43.239 and 40.46 total percent compression on average. This is because
HTML/txt files tend to have patterns and high frequency symbols/letters that recieve shorter
Huffman codes. This leads to more compression.

The tiff files in Waterloo tended to recieve little
compression as shown by the 18.137 percent total compression on average. This is because tiff
files are typically already compressed or they contain many unique symbols, leading to less
repeated patterns and therefore longer Huffman codes.

When we recompressed the already compressed Huffman files in BooksAndHTML (which achieved high
initial compression), virtually no (1.388 percent) compression was achieved. The recompression even
lead to some files such as syllabus.htm.hf growing. The reason recompression is basically useless
for Huffman is because the patterns which create short Huffman codes have already been taken
advantage of. Also, when recompressed a new header and PEOF are added, sometimes leading to a larger
file.


DATA FOR BooksAndHTML COMPRESSING ALREADY COMPRESSED FILES ONLY

compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/syllabus.htm.hf.hf
syllabus.htm.hf from     21342 to        21841 in        0.092
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/A7_Recursion.html.hf.hf
A7_Recursion.html.hf from        26189 to        26340 in        0.057
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/revDictionary.txt.hf.hf
revDictionary.txt.hf from        611618 to       590850 in       1.007
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/melville.txt.hf.hf
melville.txt.hf from     47364 to        47551 in        0.094
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/jnglb10.txt.hf.hf
jnglb10.txt.hf from      168618 to       167167 in       0.287
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/ThroughTheLookingGlass.txt.hf.hf
ThroughTheLookingGlass.txt.hf from       110293 to       109626 in       0.193
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/kjv10.txt.hf.hf
kjv10.txt.hf from        2489768 to      2452980 in      4.698
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/quotes.htm.hf.hf
quotes.htm.hf from       38423 to        39036 in        0.129
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/rawMovieGross.txt.hf.hf
rawMovieGross.txt.hf from        53833 to        51821 in        0.180
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/CiaFactBook2000.txt.hf.hf
CiaFactBook2000.txt.hf from      2260664 to      2240008 in      3.740
--------
total bytes read: 5828112
total compressed bytes 5747220
total percent compression 1.388
compression time: 10.477


DATA FOR WATERLOO

compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/sail.tif.hf
sail.tif from    1179784 to      1085501 in      1.871
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/monarch.tif.hf
monarch.tif from         1179784 to      1109973 in      1.854
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/clegg.tif.hf
clegg.tif from   2149096 to      2034595 in      3.246
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/lena.tif.hf
lena.tif from    786568 to       766146 in       1.243
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/serrano.tif.hf
serrano.tif from         1498414 to      1127645 in      1.839
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/peppers.tif.hf
peppers.tif from         786568 to       756968 in       1.204
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/tulips.tif.hf
tulips.tif from  1179784 to      1135861 in      1.817
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/waterloo/frymire.tif.hf
frymire.tif from         3706306 to      2188593 in      3.550
--------
total bytes read: 12466304
total compressed bytes 10205282
total percent compression 18.137
compression time: 16.624


DATA FOR CALGARY

compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper6.hf
paper6 from      38105 to        25057 in        0.084
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper1.hf
paper1 from      53161 to        34371 in        0.068
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/obj1.hf
obj1 from        21504 to        17085 in        0.046
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/geo.hf
geo from         102400 to       73592 in        0.129
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/progc.hf
progc from       39611 to        26948 in        0.052
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/progl.hf
progl from       71646 to        44017 in        0.077
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/book1.hf
book1 from       768771 to       439409 in       0.727
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/progp.hf
progp from       49379 to        31248 in        0.056
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/pic.hf
pic from         513216 to       107586 in       0.183
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/news.hf
news from        377109 to       247428 in       0.421
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper4.hf
paper4 from      13286 to        8894 in         0.020
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/bib.hf
bib from         111261 to       73795 in        0.121
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper3.hf
paper3 from      46526 to        28309 in        0.050
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper2.hf
paper2 from      82199 to        48649 in        0.091
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/paper5.hf
paper5 from      11954 to        8465 in         0.040
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/obj2.hf
obj2 from        246814 to       195131 in       0.474
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/trans.hf
trans from       93695 to        66252 in        0.121
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/calgary/book2.hf
book2 from       610856 to       369335 in       0.632
--------
total bytes read: 3251493
total compressed bytes 1845571
total percent compression 43.239
compression time: 3.392

DATA FOR BOOKSANDHTML

compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/melville.txt.hf
melville.txt from        82140 to        47364 in        0.183
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/A7_Recursion.html.hf
A7_Recursion.html from   41163 to        26189 in        0.058
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/jnglb10.txt.hf
jnglb10.txt from         292059 to       168618 in       0.315
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/ThroughTheLookingGlass.txt.hf
ThroughTheLookingGlass.txt from  188199 to       110293 in       0.193
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/syllabus.htm.hf
syllabus.htm from        33273 to        21342 in        0.041
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/revDictionary.txt.hf
revDictionary.txt from   1130523 to      611618 in       1.039
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/CiaFactBook2000.txt.hf
CiaFactBook2000.txt from         3497369 to      2260664 in      4.192
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/kjv10.txt.hf
kjv10.txt from   4345020 to      2489768 in      4.677
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/rawMovieGross.txt.hf
rawMovieGross.txt from   117272 to       53833 in        0.105
compressing to: /Users/hpicemobile/Desktop/dev/Scott314/Huffman-Coding/BooksAndHTML/quotes.htm.hf
quotes.htm from  61563 to        38423 in        0.074
--------
total bytes read: 9788581
total compressed bytes 5828112
total percent compression 40.460
compression time: 10.877
