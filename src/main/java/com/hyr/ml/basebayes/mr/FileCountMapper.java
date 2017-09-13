package com.hyr.ml.basebayes.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @category 统计训练集中文档总个数
 * @author huangyueran
 *
 */
public class FileCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text line, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
//		System.out.println(line);
		// 得到一行记录
		// 0,polo1.4 自动 舒适 标准价 121000元 现价 111600元 降幅 9400元 预计 再降 400元 桑塔纳 3000 手动 标准型 标准价 118000元 现价 107000元 降幅 11000元 预计 再降 1000元
		// 类型,分好的词
		
		//		String classLabel=line.toString().substring(0, 1); // 分类标签
		//		String content=line.toString().substring(2); // 分好词的文本内容
		//		String[] words = content.split("\t");	// 文档中分好的词
		
		//*****************************************************************************
		context.write(new Text("DocOfNums"), new IntWritable(1));
	}

}
