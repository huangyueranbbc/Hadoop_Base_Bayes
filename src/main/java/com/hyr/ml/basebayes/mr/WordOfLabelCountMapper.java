package com.hyr.ml.basebayes.mr;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @category 统计词在不同分类出现的次数
 * 不错:0 1
 * 词:词所在文档的分类标签  次数
 */
public class WordOfLabelCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text line, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// 得到一行记录
		// 0,polo1.4 自动 舒适 标准价 121000元 现价 111600元 降幅 9400元 预计 再降 400元 桑塔纳 3000 手动 标准型 标准价 118000元 现价 107000元 降幅 11000元 预计 再降 1000元
		// 类型,分好的词
		
		String classLabel = line.toString().substring(0, 1); // 分类标签
		String content = line.toString().substring(2); // 分好词的文本内容
		String[] words = content.split(" ");	// 文档中分好的词
		
		// 去重 去掉文档中重复的词
		HashSet<String> set=new HashSet<>();
		for(String s:words){
			// 过滤掉包含数字和字母的词语
			String regex = "^[\u4e00-\u9fa5]+$";
			if (!s.matches(regex)) { // 如果不是只包含汉字
				continue;
			}
			
			set.add(s);
		}
		
		for (String w : set) {
			context.write(new Text(w+":"+classLabel), new IntWritable(1));	// 不错:0 1
		}
		
	}

}
