package com.hyr.ml.basebayes.mr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @category 合并Step2和Step3的结果 生成最终的词频表 根据map输出的已经统一化的key,进行合并.
 */
public class Merge2And3ToWordFrequencyListReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> iterable, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Map<String, String> mapA = new HashMap<String, String>();
		Map<String, Integer> mapB = new HashMap<String, Integer>();
		
		// 市工商局 A,2:4  OR  市工商局 B,13 
		for(Text t:iterable){
			String val = t.toString();
			if (val.startsWith("A,")) {// 词在不同分类标签出现的次数
				String[] sp = val.substring(2).split(":");
				mapA.put(key.toString()+":"+sp[0], sp[1]); // 市工商局:2   4
			}else if (val.startsWith("B,")) { // 词在不同文档出现的总次数
				Integer wordCountOfDocs =Integer.parseInt(val.substring(2));
				mapB.put(key.toString(),wordCountOfDocs);
			}
		}
		
		// 转换为 	市工商局:2 		4,13
		Iterator<String> iteratorA = mapA.keySet().iterator();
		
		while (iteratorA.hasNext()) {
			String mapAkey = iteratorA.next();// key  市工商局:2
			String[] spA = mapAkey.split(":");
			Integer  wordCountOfDocs = mapB.get(spA[0]).intValue();
			context.write(new Text(mapAkey), new Text(mapA.get(mapAkey)+","+wordCountOfDocs));
		}
		
	}
}
