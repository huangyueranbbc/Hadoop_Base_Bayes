package com.hyr.ml.basebayes.run;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;

/**
 * @category 朴素贝叶斯进行文本分类(训练模型)
 * @author huangyueran
 *
 */
public class StartRun {

	public static void main(String[] args) {
		System.setProperty("HADOOP_USER_NAME", "root"); // 设置权限用户
		
		Configuration config = new Configuration();
		config.set("fs.defaultFS", "hdfs://master:8020");
		config.set("yarn.resourcemanager.hostname", "master");
		// 所有mr的输入和输出目录定义在map集合中
		Map<String, String> paths = new HashMap<String, String>();
		paths.put("Step1Input", "/user/root/bayes/input/train_data.txt"); // 训练数据集 datasets
		paths.put("Step1Output", "/user/root/bayes/output/step1");
		paths.put("Step2Input", paths.get("Step1Input"));
		paths.put("Step2Output", "/user/root/bayes/output/step2");
		paths.put("Step3Input", paths.get("Step2Output"));
		paths.put("Step3Output", "/user/root/bayes/output/step3");
		paths.put("Step4Input1", paths.get("Step2Output"));
		paths.put("Step4Input2", paths.get("Step3Output"));
		paths.put("Step4Output", "/user/root/bayes/output/step4");
		
//		paths.put("Step5Input", paths.get("Step4Output"));
//		paths.put("Step5Output", "/user/itemcf/output/step5");
//		paths.put("Step6Input", paths.get("Step5Output"));
//		paths.put("Step6Output", "/user/itemcf/output/step6");
//		Step1.run(config, paths);	 // 统计训练集文档个数
//		Step2.run(config, paths);	 // 统计每个词在不同分类标签出现的次数
//		Step3.run(config, paths);	 // 统计所有文档中包含某个词的文档个数
		Step4.run(config, paths);	 // 合并Step2和Step3结果 生成最终的词频表
	}

}

