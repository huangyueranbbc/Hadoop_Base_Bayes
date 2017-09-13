package com.hyr.ml.basebayes.mr;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @category 统计文档中有该词的文档总个数
 * 不错 1
 * 词:出现该词的文档个数
 */
public class WordDocNumsOfWordInDocMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text line, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//		驾轻就熟:7	2
		//		驾轻就熟:8	7
		//		驾轻就熟:9	2
		//		驾过:0	19
		//		驾驭:0	117
		//		驾驭:1	1
		//		驾驭:2	1
		
		String content = line.toString(); // 驾驭:0	117
		String[] splits = content.split("\t"); 
		String[] keys = splits[0].split(":");
		String word=keys[0];
		Integer word_nums=Integer.parseInt(splits[1]);
		
		context.write(new Text(word), new IntWritable(word_nums));	 // 词 : 词出现的次数
	}

}
