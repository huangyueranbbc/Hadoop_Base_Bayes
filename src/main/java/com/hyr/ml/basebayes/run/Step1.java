package com.hyr.ml.basebayes.run;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hyr.ml.basebayes.mr.FileCountMapper;
import com.hyr.ml.basebayes.mr.FileCountReducer;

/**
 * @category 统计文档总个数
 * @author root
 */
public class Step1 {

	public static boolean run(Configuration config, Map<String, String> paths) {
		try {
			FileSystem fs = FileSystem.get(config);
			Job job = Job.getInstance(config);
			
			job.setJobName("step1");

//			config.set("mapred.jar", "D:\\MR\\item.jar");

			job.setJarByClass(Step1.class);
			job.setMapperClass(FileCountMapper.class);
			job.setReducerClass(FileCountReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			FileInputFormat.addInputPath(job, new Path(paths.get("Step1Input")));
			
			Path outpath = new Path(paths.get("Step1Output"));
			if (fs.exists(outpath)) {
				fs.delete(outpath, true);
			}
			FileOutputFormat.setOutputPath(job, outpath);

			boolean f = job.waitForCompletion(true);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

