#朴素贝叶斯 文本分类  
  
>1 统计文档个数:  
  
>2 统计词频表:  
词 分类标签 该分类标签出现该的次数,词要去重(4) 文档中有该词的文档总个数(20)		4/20 概率是 这个分类  
	1.统计词在不同分类标签出现的次数  
	2.统计词在不同文档中出现的次数  
	3.合并两次计算的结果,生成最终的词频表  
		a.将两个文件的key统一化  
		市工商局:2	4  
		市工商局	13  
		最终:A,B是标志位  
		市工商局 A,2:4	  
		市工商局 B,13	  
		然后进行合并计算  
		市工商局:2 4,13  
  
>3 建立模型:  	
	词 在每个分类中的概率(该分类文档出现该词的次数/文档中有该词的文档总个数)	词在所有文档中出现的总次数  
  
>4 根据模型对文本进行分类(对文本先进行分词):  
	文档名称	文本中所有的词和词的分类概率	文档出现该词的文档个数  
	DOC1			分类:   美女  汽车  体育           
			不错		4/20  6/20  10/20	20  
			很好		7/10  2/10  1/10	10  
			吃饭		2/5   1/5   2/5		5  
  
>5 计算单个文本中每个分类的概率:	  
		美女的概率=4/20X7/10X2/5	=0.055999999999999994  
		汽车的概率=6/20X2/10X1/5	=0.012  
		体育的概率=10/20X1/10X2/5	=0.020000000000000004  
  
>6 对每种分类的概率进行排序,选出概率最大的分类:  
		美女:0.055999999999999994  
		体育:0.020000000000000004  
		汽车:0.012  
  
>7 该文档分类标签就是:美女  
  
# Naive Bayesian text classification  
>1 Statistics document number:  
>2 word frequency statistics table: word classification label for the classification and labelling of the number of words to the total number of documents in the document of the word (20) is 4/20 times the probability of the statistical classification of 1. words appear in different classification label 2. statistical word appears in different documents in 3. with the two calculation table A., to generate the final frequency of the two files key Industrial and Commercial Bureau unified city: 24, 13: A Industrial and Commercial Bureau eventually, B is a sign of a city Industrial and Commercial Bureau A, 2:4, Industrial and Commercial Bureau B 13, and then combined calculation, Industrial and Commercial Bureau: 2 4,13  
>3 establish a model: the probability of the word in each category (the number of words in the document that appears in the category document / the total number of words in the document) and the total number of words that appear in all documents  
>4 classify the text according to the model (text segmentation first): document name, all the words in the text and the word classification probability document, the number of documents that appear in the word. DOC1 classification: Beauty car sports
Yes, 4/20 6/20 10/20 20 is good. 7/10 2/10 1/10 10 eats 2/5 1/5 2/5 5  
5 calculate the probability of each category in a single text: the probability of beauty, the probability of the =4/207/102/5 =0.055999999999999994, the probability of the car, the probability of =6/202/101/5 =0.012, and the probability of =10/201/102/5 =0.020000000000000004  
>6 sort the probabilities of each category, and choose the largest category: Beauty: 0.055999999999999994 Sports: 0.020000000000000004 cars: 0.012  
>7 the document classification label is: Beauty  
