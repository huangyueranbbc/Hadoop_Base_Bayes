package com.hyr.ml.basebayes.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @category 统计文档个数
 * @author huangyueran
 *
 */
public class FileCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> iterable,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable i : iterable) {
			sum = sum + i.get();
		}
		
		context.write(key, new IntWritable(sum));
	}

}
