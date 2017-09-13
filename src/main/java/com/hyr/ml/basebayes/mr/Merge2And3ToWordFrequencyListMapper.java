package com.hyr.ml.basebayes.mr;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * @category 合并Step2和Step3的结果 生成最终的词频表
 */
public class Merge2And3ToWordFrequencyListMapper extends Mapper<LongWritable, Text, Text, Text> {
	private String flag;// A:词在不同分类标签出现的次数 or B:词在不同文档出现的总次数

	// 每个maptask，初始化时调用一次
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		FileSplit split = (FileSplit) context.getInputSplit();
		flag = split.getPath().getParent().getName();// 判断读的数据集 Determine the
														// data set for reading

		System.out.println(flag + "**********************");
	}

	/**
	 * @category 将两个输入数据集的key进行统一
	 */
	@Override
	protected void map(LongWritable key, Text line, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		if (flag.equals("step2")) {// 词在不同分类标签出现的次数
			// 市工商局:2	4 --> 市工商局 A,2:4
			String[] sp = line.toString().split("\t");
			String[] keys = sp[0].split(":");
			String word_key=keys[0];
			String word_value="A,"+keys[1]+":"+sp[1];
			context.write(new Text(word_key),new Text(word_value));
		}else if (flag.equals("step3")) {// 词在不同文档出现的总次数
			// 市工商局	13 --> 市工商局 B,13
			String[] sp=line.toString().split("\t");
			String word_key=sp[0];
			String word_value="B,"+sp[1];
			context.write(new Text(word_key),new Text(word_value));
		}
	}
}
