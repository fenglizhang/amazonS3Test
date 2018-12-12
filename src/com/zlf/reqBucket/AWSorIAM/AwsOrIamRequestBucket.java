package com.zlf.reqBucket.AWSorIAM;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
/**
 * 使用 AWS 账户或 IAM [用户证书]进行请求
 * @author Administrator
 *
 */
public class AwsOrIamRequestBucket {
	
	private final static String bucketName="com.taikang.voiceRecord.bucket";
	
	public static void main(String[] args) {
		//创建 AmazonS3Client 类的实例。
		AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());        
		//执行 AmazonS3Client 方法之一，以向 Amazon S3 发送请求。客户端将从您的证书生成所需的签名值并将其包含在发送至 Amazon S3 的请求中。
		ObjectListing objectListing = s3client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
	}
}
